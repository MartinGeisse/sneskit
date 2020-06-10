package name.martingeisse.sneskit.deconstruct.disasm;

import name.martingeisse.sneskit.util.AddressUtil;
import name.martingeisse.sneskit.util.KitException;
import org.apache.commons.lang3.tuple.Pair;

import java.io.PrintWriter;
import java.util.*;

public class Disassembler {

//region general

    // Placed as disassembled code in the addresses after a multi-byte instruction, so they don't get emitted as data.
    private static final String[] SHADOWED = new String[0];

    private final byte[] rom;
    private final Map<Integer, String[]> codeFragments = new HashMap<>(); // keys are physical addresses
    private final Map<Integer, InstructionFormat> assumedInstructionFormats = new HashMap<>(); // keys are physical addresses
    private final Queue<Pair<Integer, InstructionFormat>> virtualAddressTodoQueue = new ArrayDeque<>();

    public Disassembler(byte[] rom) {
        this.rom = rom;
    }

    private void setDisassembled(int physicalAddress, InstructionFormat assumedFormat, int byteCount, String... lines) {
        if (byteCount < 1) {
            throw new IllegalArgumentException("disassembled code must represent at least one byte, was: " + byteCount);
        }
        codeFragments.put(physicalAddress, lines);
        assumedInstructionFormats.put(physicalAddress, assumedFormat);
        for (int i = 1; i < byteCount; i++) {
            codeFragments.put(physicalAddress + i, SHADOWED);
        }
    }

    public String[] getDisassembledLinesForPhysicalAddress(int physicalAddress) {
        if (physicalAddress < 0 || physicalAddress >= rom.length) {
            throw new IllegalArgumentException("physical address out of bounds: " + Integer.toHexString(physicalAddress));
        }
        return codeFragments.get(physicalAddress);
    }

    public void writeEffectiveLinesForPhysicalAddress(int physicalAddress, PrintWriter out) {
        String[] lines = getDisassembledLinesForPhysicalAddress(physicalAddress);
        if (lines == null) {
            out.println(".db $" + Integer.toHexString(rom[physicalAddress] & 0xff));
        } else {
            for (String line : lines) {
                out.println(line);
            }
        }
    }

//endregion

//region instruction disassembling

    public void disassemble(int virtualAddress, InstructionFormat assumedFormat) {
        virtualAddressTodoQueue.add(Pair.of(virtualAddress, assumedFormat));
        processTodoQueue();
    }

    public void processTodoQueue() {
        while (!virtualAddressTodoQueue.isEmpty()) {

            // take an element from the to-do queue, map to physical address
            Pair<Integer, InstructionFormat> element = virtualAddressTodoQueue.poll();
            int virtualAddress = element.getLeft();
            InstructionFormat assumedFormat = element.getRight();
            int physicalAddress = AddressUtil.virtualToPhysical(virtualAddress);

            // check if this address was already disassembled, and if under the same assumptions
            if (getDisassembledLinesForPhysicalAddress(physicalAddress) != null) {
                InstructionFormat alreadyDisassembledFormat = assumedInstructionFormats.get(physicalAddress);
                assumedFormat = InstructionFormat.getCommon(assumedFormat, alreadyDisassembledFormat);
                if (assumedFormat == alreadyDisassembledFormat) {
                    // nothing to do, we already disassembled this instruction
                    return;
                }
            }

            // try/catch to show errors as comments in the disassembled result
            try {

                // decode the instruction
                int opcode = rom[physicalAddress] & 0xff;
                InstructionTable.Entry instruction = InstructionTable.TABLE[opcode];
                if (instruction == null) {
                    onDisassemblyFailed(virtualAddress, physicalAddress, assumedFormat, "unknown opcode");
                    return;
                }
                int length = instruction.getLength().getActualLength(assumedFormat);

                // generate assembler text for the instruction; at the same time, rebuild its immediate value
                String instructionLine;
                int immediateValue = 0;
                {
                    StringBuilder lineBuilder = new StringBuilder();
                    for (int i = 0; i < length; i++) {
                        int romByte = rom[physicalAddress + i] & 0xff;
                        lineBuilder.append(i == 0 ? ".db $" : ", $");
                        lineBuilder.append(Integer.toHexString(romByte));
                        if (i > 0) {
                            immediateValue += romByte << (8 * (i - 1));
                        }
                    }
                    lineBuilder.append(" ; ").append(instruction.getMnemonic());
                    instructionLine = lineBuilder.toString();
                }

                // store the disassembled instruction
                setDisassembled(physicalAddress, assumedFormat, length, augmentLine(virtualAddress, length, instructionLine));

                // go on disassembling the next and/or jumped-to instruction
                InstructionFormat nextFormat = instruction.getSpecialEffect().getNextInstructionFormat(assumedFormat, immediateValue);
                if (!instruction.isDivert()) {
                    virtualAddressTodoQueue.add(Pair.of(virtualAddress + length, nextFormat));
                }
                InstructionTable.StaticJumpAddressingMode staticJumpAddressingMode = instruction.getStaticJumpAddressingMode();
                if (staticJumpAddressingMode != null) {
                    int jumpTarget = staticJumpAddressingMode.getJumpTarget(virtualAddress, length, immediateValue);
                    virtualAddressTodoQueue.add(Pair.of(jumpTarget, nextFormat));
                }

            } catch (Exception e) {
                onDisassemblyFailed(virtualAddress, physicalAddress, assumedFormat, e.getMessage());
            }
        }
    }

    private void onDisassemblyFailed(int virtualAddress, int physicalAddress, InstructionFormat assumedFormat, String message) {

        // determine the bounds of the previous instruction
        int length = 1;
        while (true) {
            String[] fragment = codeFragments.get(physicalAddress);
            if (fragment == null || fragment.length > 0) {
                break;
            }
            physicalAddress--;
            virtualAddress--;
            length++;
        }
        while (true) {
            String[] fragment = codeFragments.get(physicalAddress + length);
            if (fragment == null || fragment.length > 0) {
                break;
            }
            length++;
        }

        // turn those bytes back to raw data and append the message as a comment
        StringBuilder lineBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int romByte = rom[physicalAddress + i] & 0xff;
            lineBuilder.append(i == 0 ? ".db $" : ", $");
            lineBuilder.append(Integer.toHexString(romByte));
        }
        lineBuilder.append(" ; DISASSEMBLER ERROR AT v$" + Integer.toHexString(virtualAddress)).append(": ").append(message);
        setDisassembled(physicalAddress, assumedFormat, length, augmentLine(virtualAddress, 1, lineBuilder.toString()));

    }

    private static String[] augmentLine(int virtualAddress, int length, String line) {
        if (isPrintAddress(virtualAddress, length)) {
            return new String[]{"; virtual address: $" + Integer.toHexString(virtualAddress), line};
        } else {
            return new String[]{line};
        }
    }

    private static boolean isPrintAddress(int address, int length) {

        // TODO remove
        if (1 == 1) return true;

        // print address for an instruction at a 16-byte boundary
        if ((address & 15) == 0) {
            return true;
        }

        // do not print address if the next instruction is at a 16-byte bounary, since that one will print it
        int address2 = address + length;
        if ((address2 & 15) == 0) {
            return false;
        }

        // otherwise, print the address when we are crossing a 16-byte boundary
        int hi0 = address & ~15;
        int hi1 = address2 & ~15;
        return hi0 != hi1;

    }

//endregion

}

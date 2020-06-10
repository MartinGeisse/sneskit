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
        if (getDisassembledLinesForPhysicalAddress(physicalAddress) != null) {
            throw new IllegalStateException("code has been disassembled already");
        }
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

            // try/catch to add information in case of errors
            try {

                // check if this address was already disassembled, and if under the same assumptions
                if (getDisassembledLinesForPhysicalAddress(physicalAddress) != null) {
                    InstructionFormat alreadyDisassembledFormat = assumedInstructionFormats.get(physicalAddress);
                    if (assumedFormat == alreadyDisassembledFormat || alreadyDisassembledFormat == InstructionFormat.UNKNOWN) {
                        // nothing to do, we already disassembled this instruction
                        return;
                    }
                    // we must disassemble this instruction again assuming UNKNOWN format since it is reachable with different formats
                    assumedFormat = InstructionFormat.UNKNOWN;
                }

                // decode the instruction
                int opcode = rom[physicalAddress] & 0xff;
                InstructionTable.Entry instruction = InstructionTable.TABLE[opcode];
                if (instruction == null) {
                    String line = ".db $" + Integer.toHexString(rom[physicalAddress] & 0xff) + " ; unknown opcode";
                    setDisassembled(physicalAddress, assumedFormat, 1, augmentLine(virtualAddress, 1, line));
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
                    int jumpTarget = staticJumpAddressingMode.getJumpTarget(virtualAddress, immediateValue);
                    virtualAddressTodoQueue.add(Pair.of(jumpTarget, nextFormat));
                }

            } catch (Exception e) {
                throw new KitException("failed to disassemble instruction at v0x" + Integer.toHexString(virtualAddress) +
                        " = p0x" + Integer.toHexString(physicalAddress), e);
            }
        }
    }

    private static String[] augmentLine(int virtualAddress, int length, String line) {
        if (isPrintAddress(virtualAddress, length)) {
            return new String[]{"; virtual address: $" + Integer.toHexString(virtualAddress), line};
        } else {
            return new String[]{line};
        }
    }

    private static boolean isPrintAddress(int address, int length) {

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

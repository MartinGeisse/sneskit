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

    // virtualAddressTodoQueue

    public void disassemble(int virtualAddress, InstructionFormat assumedFormat) {
        virtualAddressTodoQueue.add(Pair.of(virtualAddress, assumedFormat));
        processTodoQueue();
    }

    public void processTodoQueue() {
        while (!virtualAddressTodoQueue.isEmpty()) {
            Pair<Integer, InstructionFormat> element = virtualAddressTodoQueue.poll();
            int virtualAddress = element.getLeft();
            InstructionFormat assumedFormat = element.getRight();
            int physicalAddress = AddressUtil.virtualToPhysical(virtualAddress);
            try {
                if (getDisassembledLinesForPhysicalAddress(physicalAddress) != null) {
                    InstructionFormat alreadyDisassembledFormat = assumedInstructionFormats.get(physicalAddress);
                    if (assumedFormat == alreadyDisassembledFormat || alreadyDisassembledFormat == InstructionFormat.UNKNOWN) {
                        // nothing to do, we already disassembled this instruction
                        return;
                    }
                    // we must disassemble this instruction again assuming UNKNOWN format since it is reachable with different formats
                    assumedFormat = InstructionFormat.UNKNOWN;
                }
                int opcode = rom[physicalAddress] & 0xff;
                InstructionTable.Entry instruction = InstructionTable.TABLE[opcode];
                String instructionLine;
                int length;
                boolean divert;
                int jumpTarget;
                if (instruction == null) {
                    // at least we tried
                    instructionLine = ".db $" + Integer.toHexString(rom[physicalAddress] & 0xff) + " ; disassembly failed";
                    length = 1;
                    divert = true;
                    jumpTarget = -1;
                } else {
                    StringBuilder lineBuilder = new StringBuilder();
                    length = instruction.getLength().getActualLength(assumedFormat);
                    int immediateValue = 0;
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
                    divert = instruction.isDivert();
                    InstructionTable.StaticJumpAddressingMode staticJumpAddressingMode = instruction.getStaticJumpAddressingMode();
                    if (staticJumpAddressingMode == null) {
                        jumpTarget = -1;
                    } else {
                        jumpTarget = staticJumpAddressingMode.getJumpTarget(virtualAddress, immediateValue);
                    }
                }
                String[] lines = isPrintAddress(virtualAddress, length) ?
                        new String[]{"; virtual address: $" + Integer.toHexString(virtualAddress), instructionLine} :
                        new String[]{instructionLine};
                setDisassembled(physicalAddress, assumedFormat, length, lines);
                if (!divert) {
                    virtualAddressTodoQueue.add(Pair.of(virtualAddress + length, assumedFormat));
                }
                if (jumpTarget != -1) {
                    virtualAddressTodoQueue.add(Pair.of(jumpTarget, assumedFormat));
                }
            } catch (Exception e) {
                throw new KitException("failed to disassemble instruction at v0x" + Integer.toHexString(virtualAddress) +
                        " = p0x" + Integer.toHexString(physicalAddress), e);
            }
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

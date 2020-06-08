package name.martingeisse.sneskit.deconstruct.disasm;

import name.martingeisse.sneskit.util.AddressUtil;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Disassembler {

    // Placed as disassembled code in the addresses after a multi-byte instruction, so they don't get emitted as data.
    private static final String[] SHADOWED = new String[0];

    private final byte[] rom;
    private final Map<Integer, String[]> codeFragments = new HashMap<>(); // keys are physical addresses

    public Disassembler(byte[] rom) {
        this.rom = rom;
    }

    private void setDisassembled(int physicalAddress, int byteCount, String... lines) {
        if (byteCount < 1) {
            throw new IllegalArgumentException("disassembled code must represent at least one byte, was: " + byteCount);
        }
        codeFragments.put(physicalAddress, lines);
        for (int i = 1; i < byteCount; i++) {
            codeFragments.put(physicalAddress + i, SHADOWED);
        }
    }

    public void disassemble(int virtualAddress) {
        // dummy
        int physicalAddress = AddressUtil.virtualToPhysical(virtualAddress);
        codeFragments.put(physicalAddress, new String[]{
                "  .db $" + Integer.toHexString(rom[physicalAddress] & 0xff) + " ; disassembled"
        });
    }

    public String[] getDisassembledLinesForPhysicalAddress(int physicalAddress) {
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

}

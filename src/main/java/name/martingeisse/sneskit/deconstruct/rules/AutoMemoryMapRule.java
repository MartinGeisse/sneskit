package name.martingeisse.sneskit.deconstruct.rules;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.Rule;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AutoMemoryMapRule implements Rule {

    private static int CHUNK_SIZE = 4096;

    @Override
    public void run(JsonObject ruleConfiguration, byte[] rom, File partsFolder) throws IOException {
        File mapFile = new File(partsFolder, "auto-memory-map.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(mapFile)) {
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)) {
                try (PrintWriter w = new PrintWriter(outputStreamWriter)) {
                    for (int address = 0; address < rom.length; address += CHUNK_SIZE) {
                        describeAs(address, CHUNK_SIZE, w, isZero(rom, address, CHUNK_SIZE));
                    }
                }
            }
        }
    }

    private void describeAs(int address, int length, PrintWriter w, boolean zero) {
        w.println(Integer.toHexString(address) + " - " + Integer.toHexString(address + length - 1) + ": " +
                (zero ? "zero" : "nonzero"));
    }

    private boolean isZero(byte[] rom, int address, int length) {
        for (int i = 0; i < length; i++) {
            if (rom[address + i] != 0) {
                return false;
            }
        }
        return true;
    }

}

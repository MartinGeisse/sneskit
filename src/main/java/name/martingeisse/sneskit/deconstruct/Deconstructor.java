package name.martingeisse.sneskit.deconstruct;

import com.google.gson.JsonObject;

import java.io.File;

public class Deconstructor {

    private final JsonObject configuration;
    private final byte[] rom;
    private final File partsFolder;

    public Deconstructor(JsonObject configuration, byte[] rom, File partsFolder) {
        this.configuration = configuration;
        this.rom = rom;
        this.partsFolder = partsFolder;
    }

    public void run() {
        System.out.println("ROM size: " + (rom.length >> 20) + " MB");
    }

}

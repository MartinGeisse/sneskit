package name.martingeisse.sneskit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.Deconstructor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Deconstruct {

    private static final Gson GSON = new GsonBuilder().create();

    public static void main(String[] args) throws Exception {

        // read configuration
        File deconstructJson = new File("deconstruct.json");
        JsonObject configuration;
        if (deconstructJson.isFile()) {
            try (FileInputStream fileInputStream = new FileInputStream(deconstructJson)) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
                    configuration = GSON.fromJson(inputStreamReader, JsonObject.class);
                }
            }
        } else if (deconstructJson.exists()) {
            throw new IOException("'deconstruct.json' exists but is not a file");
        } else {
            configuration = new JsonObject();
        }

        // create a clean parts folder
        File partsFolder = new File("parts");
        if (partsFolder.isDirectory()) {
            FileUtils.deleteDirectory(partsFolder);
        } else if (partsFolder.exists()) {
            throw new IOException("'parts' exists but is not a folder");
        }
        if (!partsFolder.mkdir()) {
            throw new IOException("could not create an empty 'parts' folder");
        }

        // read the ROM (note: .sfc is typically unheadered, .smc is typically headered)
        File romFile = new File("original.sfc");
        byte[] rom = FileUtils.readFileToByteArray(romFile);
        if (rom.length % (1024 * 1024) != 0) {
            throw new IOException("ROM file must be unheadered");
        }

        new Deconstructor(configuration, rom, partsFolder).run();
    }

}

package name.martingeisse.sneskit;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.reconstruct.Reconstructor;
import name.martingeisse.sneskit.util.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Reconstruct {

    public static void main(String[] args) throws Exception {

        // read configuration
        File reconstructJson = new File("parts/reconstruct.json");
        JsonObject configuration;
        try (FileInputStream fileInputStream = new FileInputStream(reconstructJson)) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
                configuration = JsonUtil.GSON.fromJson(inputStreamReader, JsonObject.class);
            }
        }

        // create a clean build folder
        File buildFolder = new File("build");
        if (buildFolder.isDirectory()) {
            FileUtils.deleteDirectory(buildFolder);
        } else if (buildFolder.exists()) {
            throw new IOException("'build' exists but is not a folder");
        }
        if (!buildFolder.mkdir()) {
            throw new IOException("could not create an empty 'build' folder");
        }

        // remove previous output file
        File outputFile = new File("modified.sfc");
        if (outputFile.exists()) {
            if (!outputFile.delete()) {
                throw new IOException("could not remove previous output file");
            }
        }

        // reconstruct the ROM file
        File partsFolder = new File("parts");
        new Reconstructor(configuration, partsFolder, buildFolder, outputFile).run();
    }

}

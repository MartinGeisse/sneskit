package name.martingeisse.sneskit.reconstruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import name.martingeisse.sneskit.util.JsonUtil;
import name.martingeisse.sneskit.util.KitException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Reconstructor {

    private final JsonObject configuration;
    private final File partsFolder;
    private final File buildFolder;
    private final File outputFile;

    public Reconstructor(JsonObject configuration, File partsFolder, File buildFolder, File outputFile) {
        this.configuration = configuration;
        this.partsFolder = partsFolder;
        this.buildFolder = buildFolder;
        this.outputFile = outputFile;
    }

    public void run() throws IOException {

        // instantiate the rule set
        JsonElement ruleSetElement = configuration.get("ruleSet");
        if (ruleSetElement == null) {
            throw new KitException("no ruleSet defined");
        }
        String ruleSetClassName = ruleSetElement.getAsString();
        RuleSet ruleSet;
        try {
            Class<?> ruleSetClass = Class.forName(ruleSetClassName);
            ruleSet = (RuleSet) ruleSetClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new KitException("could not create ruleSet: " + ruleSetClassName, e);
        }

        // apply all rules from the "rules" field
        RuleContext ruleContext = new RuleContext(configuration, ruleSet, partsFolder, buildFolder);
        JsonArray rules = configuration.getAsJsonArray("rules");
        if (rules == null) {
            throw new KitException("no rules defined");
        }
        for (JsonElement element : rules) {
            ruleContext.applyRule(element.getAsJsonObject());
        }

        // generate a header.inc (currently fixed for a LoROM with 48 banks)
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(buildFolder, "header.inc"))) {
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.US_ASCII)) {
                try (PrintWriter out = new PrintWriter(outputStreamWriter)) {
                    out.println(".MEMORYMAP");
                    out.println("  SLOTSIZE $8000");
                    out.println("  DEFAULTSLOT 0");
                    out.println("  SLOT 0 $8000");
                    out.println(".ENDME");
                    out.println(".ROMBANKSIZE $8000");
                    out.println(".ROMBANKS 48");
                }
            }
        }

        // assemble
        List<File> objectFiles = new ArrayList<>();
        {
            File[] buildFolderFiles = buildFolder.listFiles();
            for (File buildFolderFile : buildFolderFiles) {
                String filename = buildFolderFile.getName();
                if (filename.endsWith(".asm")) {
                    File objectFile = new File(buildFolder, filename.substring(0, filename.length() - 3) + 'o');
                    objectFiles.add(objectFile);
                    execBuildTool("wla-65816", "-o", objectFile.getName(), filename);
                }
            }
        }

        // generate linkfile
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(buildFolder, "linkfile"))) {
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.US_ASCII)) {
                try (PrintWriter out = new PrintWriter(outputStreamWriter)) {
                    out.println("[objects]");
                    for (File file : objectFiles) {
                        out.println(file.getName());
                    }
                }
            }
        }

        // run the linker
        execBuildTool("wlalink", "-v", "linkfile", outputFile.getAbsolutePath());

    }

    private void execBuildTool(String... commandLine) {
        try {
            commandLine[0] = new File(FileUtils.getUserDirectory(), "git-repos/wla-dx/build/binaries/" + commandLine[0]).getAbsolutePath();
            Process process = Runtime.getRuntime().exec(commandLine, null, buildFolder);
            int status = process.waitFor();
            if (status != 0) {
                throw new KitException("build tool returned status code " + status);
            }
        } catch (IOException|InterruptedException e) {
            throw new KitException("could not invoke build tool", e);
        }
    }
}

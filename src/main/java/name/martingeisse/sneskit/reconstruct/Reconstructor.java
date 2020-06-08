package name.martingeisse.sneskit.reconstruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import name.martingeisse.sneskit.util.JsonUtil;
import name.martingeisse.sneskit.util.KitException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

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

        // TODO generate linkfile
        // TODO run assembler
        // TODO run linker

    }

}

package name.martingeisse.sneskit.deconstruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import name.martingeisse.sneskit.util.JsonUtil;
import name.martingeisse.sneskit.util.KitException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Deconstructor {

    private final JsonObject configuration;
    private final byte[] rom;
    private final File partsFolder;

    public Deconstructor(JsonObject configuration, byte[] rom, File partsFolder) {
        this.configuration = configuration;
        this.rom = rom;
        this.partsFolder = partsFolder;
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
        RuleContext ruleContext = new RuleContext(configuration, rom, partsFolder, ruleSet);
        JsonArray rules = configuration.getAsJsonArray("rules");
        if (rules == null) {
            throw new KitException("no rules defined");
        }
        for (JsonElement element : rules) {
            ruleContext.applyRule(element.getAsJsonObject());
        }

        // generate a reconstruct.json
        JsonObject reconstructJson = new JsonObject();
        reconstructJson.add("ruleSet", configuration.get("reconstructRuleSet"));
        reconstructJson.add("rules", ruleContext.getReconstructRules());
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(partsFolder, "reconstruct.json"))) {
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8)) {
                JsonUtil.GSON.toJson(reconstructJson, outputStreamWriter);
            }
        }

    }

}

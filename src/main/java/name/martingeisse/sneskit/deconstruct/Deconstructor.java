package name.martingeisse.sneskit.deconstruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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

        JsonElement ruleSetElement = configuration.get("ruleSet");
        if (ruleSetElement == null) {
            throw new DeconstructException("no ruleSet defined");
        }
        String ruleSetClassName = ruleSetElement.getAsString();
        RuleSet ruleSet;
        try {
            Class<?> ruleSetClass = Class.forName(ruleSetClassName);
            ruleSet = (RuleSet)ruleSetClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new DeconstructException("could not create ruleSet: " + ruleSetClassName, e);
        }

        JsonArray rules = configuration.getAsJsonArray("rules");
        if (rules == null) {
            throw new DeconstructException("no rules defined");
        }
        for (JsonElement element : rules) {
            JsonObject ruleObject = element.getAsJsonObject();
            JsonElement typeElement = ruleObject.get("type");
            if (typeElement == null) {
                throw new DeconstructException("found rule without type");
            }
            String ruleType = typeElement.getAsString();
            Rule rule = ruleSet.createRule(ruleType);
            rule.run(ruleObject, rom, partsFolder);
        }
    }

}

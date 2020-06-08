package name.martingeisse.sneskit.reconstruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import name.martingeisse.sneskit.util.KitException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public final class RuleContext {

    private final JsonObject reconstructJson;
    private final RuleSet ruleSet;
    private final File partsFolder;
    private final File asmFolder;

    public RuleContext(JsonObject reconstructJson, RuleSet ruleSet, File partsFolder, File asmFolder) {
        this.reconstructJson = reconstructJson;
        this.ruleSet = ruleSet;
        this.partsFolder = partsFolder;
        this.asmFolder = asmFolder;
    }

    public JsonObject getReconstructJson() {
        return reconstructJson;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public File getPartsFolder() {
        return partsFolder;
    }

    public File getAsmFolder() {
        return asmFolder;
    }

    public void applyRule(JsonObject ruleObject) throws IOException {
        JsonElement typeElement = ruleObject.get("type");
        if (typeElement == null) {
            throw new KitException("found rule without type");
        }
        String ruleType = typeElement.getAsString();
        Rule rule = ruleSet.createRule(ruleType);
        rule.run(ruleObject, this);
    }

    public PrintWriter createAsmFile(String name) throws IOException {
        File file = new File(asmFolder, name);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.US_ASCII);
        return new PrintWriter(outputStreamWriter);
    }

}

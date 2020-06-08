package name.martingeisse.sneskit.deconstruct;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.disasm.Disassembler;
import name.martingeisse.sneskit.util.KitException;

import java.io.File;
import java.io.IOException;

public final class RuleContext {

    private final JsonObject deconstructJson;
    private final byte[] rom;
    private final File partsFolder;
    private final RuleSet ruleSet;
    private final JsonArray reconstructRules;
    private final Disassembler disassembler;

    public RuleContext(JsonObject deconstructJson, byte[] rom, File partsFolder, RuleSet ruleSet) {
        this.deconstructJson = deconstructJson;
        this.rom = rom;
        this.partsFolder = partsFolder;
        this.ruleSet = ruleSet;
        this.reconstructRules = new JsonArray();
        this.disassembler = new Disassembler(rom);
    }

    public JsonObject getDeconstructJson() {
        return deconstructJson;
    }

    public byte[] getRom() {
        return rom;
    }

    public File getPartsFolder() {
        return partsFolder;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void addReconstructRule(JsonObject rule) {
        reconstructRules.add(rule);
    }

    JsonArray getReconstructRules() {
        return reconstructRules;
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

    public Disassembler getDisassembler() {
        return disassembler;
    }

}

package name.martingeisse.sneskit.deconstruct.rules;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.Rule;
import name.martingeisse.sneskit.util.JsonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;

/**
 * Rules may extend this base class if the rule can determine the size of the extracted chunk automatically, without a
 * length field in the deconstruct.json file.
 * <p>
 * Note that in the even more abstract case of an extraction for which the length must be determined while extracting,
 * i.e. cannot determined immediately, it is more useful to define an unrelated class than extend a provided class.
 */
public abstract class ExtractRuleBase implements Rule {

    @Override
    public void run(JsonObject ruleConfiguration, byte[] rom, File partsFolder) throws IOException {
        int address = JsonUtil.getNumber(ruleConfiguration, "address");
        int length = determineLength(ruleConfiguration, rom);
        File file = new File(partsFolder, JsonUtil.getString(ruleConfiguration, "file"));
        byte[] chunk = ArrayUtils.subarray(rom, address, address + length);
        chunk = decode(ruleConfiguration, chunk, partsFolder);
        FileUtils.writeByteArrayToFile(file, chunk);
    }

    protected abstract int determineLength(JsonObject ruleConfiguration, byte[] rom);

    protected byte[] decode(JsonObject ruleConfiguration, byte[] data, File partsFolder) {
        return data;
    }

}

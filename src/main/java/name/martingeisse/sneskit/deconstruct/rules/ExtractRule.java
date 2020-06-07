package name.martingeisse.sneskit.deconstruct.rules;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.Rule;
import name.martingeisse.sneskit.util.JsonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;

/**
 * Extracts a contiguous chunk of bytes from the ROM to a file. The chunk must be specified by start address and length;
 * the file name must be specified. Subclasses can define a decoding method to convert the data before storing it in
 * the file.
 */
public class ExtractRule implements Rule {

    @Override
    public void run(JsonObject ruleConfiguration, byte[] rom, File partsFolder) throws IOException {
        int address = JsonUtil.getNumber(ruleConfiguration, "address");
        int length = JsonUtil.getNumber(ruleConfiguration, "length");
        File file = new File(partsFolder, JsonUtil.getString(ruleConfiguration, "file"));
        byte[] chunk = ArrayUtils.subarray(rom, address, address + length);
        chunk = decode(chunk);
        FileUtils.writeByteArrayToFile(file, chunk);
    }

    protected byte[] decode(byte[] data) {
        return data;
    }

}

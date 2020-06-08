package name.martingeisse.sneskit.deconstruct.rules;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.RuleContext;
import name.martingeisse.sneskit.util.JsonUtil;

/**
 * Extracts a contiguous chunk of bytes from the ROM to a file. The chunk must be specified by start address and length;
 * the file name must be specified. Subclasses can define a decoding method to convert the data before storing it in
 * the file.
 */
public class ExtractRule extends ExtractRuleBase {

    @Override
    protected int determineLength(JsonObject ruleConfiguration, RuleContext context) {
        return JsonUtil.getNumber(ruleConfiguration, "length");
    }

}

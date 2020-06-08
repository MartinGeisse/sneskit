package name.martingeisse.sneskit.deconstruct.rules;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.Rule;
import name.martingeisse.sneskit.deconstruct.RuleContext;
import name.martingeisse.sneskit.util.JsonUtil;

import java.io.IOException;

/**
 * Has a "template" field that is used like normal rules, but gets patched before it is applied. The "applications"
 * field contains an array of objects that are used as "patches".
 */
public class SimilarRule implements Rule {

    @Override
    public void run(JsonObject ruleConfiguration, RuleContext context) throws IOException {
        JsonObject template = ruleConfiguration.getAsJsonObject("template");
        JsonArray applications = ruleConfiguration.getAsJsonArray("applications");
        for (JsonElement application : applications) {
            JsonObject patched = JsonUtil.getShallowMerged(template, application.getAsJsonObject());
            context.applyRule(patched);
        }
    }

}

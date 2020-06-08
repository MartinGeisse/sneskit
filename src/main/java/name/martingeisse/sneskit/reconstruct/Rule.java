package name.martingeisse.sneskit.reconstruct;

import com.google.gson.JsonObject;

import java.io.IOException;

public interface Rule {

    void run(JsonObject ruleConfiguration, RuleContext context) throws IOException;

}

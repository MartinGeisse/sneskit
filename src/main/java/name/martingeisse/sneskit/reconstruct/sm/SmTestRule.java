package name.martingeisse.sneskit.reconstruct.sm;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.reconstruct.Rule;
import name.martingeisse.sneskit.reconstruct.RuleContext;

import java.io.IOException;
import java.io.PrintWriter;

public class SmTestRule implements Rule {

    @Override
    public void run(JsonObject ruleConfiguration, RuleContext context) throws IOException {
        try (PrintWriter w = context.createAsmFile("foo.asm")) {
            w.println("hello world");
        }
    }

}

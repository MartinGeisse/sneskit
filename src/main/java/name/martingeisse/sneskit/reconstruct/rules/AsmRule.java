package name.martingeisse.sneskit.reconstruct.rules;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.reconstruct.Rule;
import name.martingeisse.sneskit.reconstruct.RuleContext;

import java.io.File;
import java.io.IOException;

/**
 * Copies an ASM file verbatim to the asm input folder.
 */
public class AsmRule implements Rule {

    @Override
    public void run(JsonObject ruleConfiguration, RuleContext context) throws IOException {
        String filename = ruleConfiguration.get("file").getAsString();
        File sourceFile = new File(context.getPartsFolder(), filename);
        context.copyAsmFile(filename, sourceFile);
    }

}

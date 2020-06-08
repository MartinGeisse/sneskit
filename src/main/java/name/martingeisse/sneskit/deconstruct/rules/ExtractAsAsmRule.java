package name.martingeisse.sneskit.deconstruct.rules;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.RuleContext;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Extracts a chunk of data as an ASM file containing .db directives. A plain ExtractRule isn't that useful for
 * reconstruction: Because we *can* convert data to ASM this way, and ASM is more readable and editable, we avoid
 * having a binary-to-ASM converter on the reconstruction side, so a binary file made with ExtractRule itself cannot be
 * used for reconstruction.
 */
public class ExtractAsAsmRule extends ExtractTextRule {

    @Override
    protected void decode(JsonObject ruleConfiguration, byte[] data, RuleContext context, PrintWriter out) throws IOException {
        for (byte b : data) {
            out.println(".db $" + Integer.toHexString(b & 0xff));
        }
    }

}

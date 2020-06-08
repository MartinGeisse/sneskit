package name.martingeisse.sneskit.deconstruct.rules;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.RuleContext;
import name.martingeisse.sneskit.util.AddressKind;
import name.martingeisse.sneskit.util.JsonUtil;

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

        out.println(".include \"header.inc\"\n");

        // The assembler has a weird way of splitting physical addresses into "banks", despite banks being a
        // virtual address concept. I only tried this for LoROM, and for that the scheme is fortunately simple:
        // the number of physical and virtual ROM banks is the same, with a physical ROM bank being 32kB in size.
        int address = JsonUtil.getAddress(ruleConfiguration, "address", AddressKind.PHYSICAL);
        out.println(".bank $" + Integer.toHexString(address >> 15) + " slot 0");
        out.println(".org $" + Integer.toHexString(address & 0x7fff));
        out.println();

        for (byte b : data) {
            out.println(".db $" + Integer.toHexString(b & 0xff));
        }

        JsonObject reconstructRule = new JsonObject();
        reconstructRule.addProperty("type", "asm");
        reconstructRule.add("file", ruleConfiguration.get("file"));
        context.addReconstructRule(reconstructRule);

    }

}

package name.martingeisse.sneskit.deconstruct.rules;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.RuleContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Base class for extraction rules that decode() to a text file.
 */
public abstract class ExtractTextRule extends ExtractRule {

    @Override
    protected byte[] decode(JsonObject ruleConfiguration, byte[] data, RuleContext context) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(baos, getCharset())) {
            try (PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
                decode(ruleConfiguration, data, context, printWriter);
            }
        }
        return baos.toByteArray();
    }

    protected abstract void decode(JsonObject ruleConfiguration, byte[] data, RuleContext context, PrintWriter out) throws IOException;

    protected Charset getCharset() throws IOException {
        return StandardCharsets.US_ASCII;
    }

}

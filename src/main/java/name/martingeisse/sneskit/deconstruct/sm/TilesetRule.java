package name.martingeisse.sneskit.deconstruct.sm;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.RuleContext;
import name.martingeisse.sneskit.deconstruct.rules.ExtractRule;
import name.martingeisse.sneskit.deconstruct.rules.ExtractTextRule;
import name.martingeisse.sneskit.util.KitException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class TilesetRule extends ExtractTextRule {

    @Override
    protected void decode(JsonObject ruleConfiguration, byte[] data, RuleContext context, PrintWriter out) throws IOException {
        int numberOfTiles = data.length / 32;
        for (int i = 0; i < numberOfTiles; i++) {
            for (int y = 0; y < 8; y++) {
                int byte0 = data[i * 32 + y * 2] & 0xff;
                int byte1 = data[i * 32 + y * 2 + 1] & 0xff;
                int byte2 = data[i * 32 + y * 2 + 16] & 0xff;
                int byte3 = data[i * 32 + y * 2 + 17] & 0xff;
                for (int x = 0; x < 8; x++) {
                    int bitMask = 1 << (7 - x);
                    int bit0 = (byte0 & bitMask) != 0 ? 8 : 0;
                    int bit1 = (byte1 & bitMask) != 0 ? 4 : 0;
                    int bit2 = (byte2 & bitMask) != 0 ? 2 : 0;
                    int bit3 = (byte3 & bitMask) != 0 ? 1 : 0;
                    int colorCode = bit0 + bit1 + bit2 + bit3;
                    out.write(colorCode == 0 ? '.' : colorCode < 10 ? (colorCode + '0') : (colorCode - 10 + 'a'));
                }
                out.write('\n');
            }
            out.write('\n');
        }
    }

}

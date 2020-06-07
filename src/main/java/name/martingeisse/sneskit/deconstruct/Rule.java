package name.martingeisse.sneskit.deconstruct;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;

public interface Rule {

    void run(JsonObject ruleConfiguration, byte[] rom, File partsFolder) throws IOException;

}

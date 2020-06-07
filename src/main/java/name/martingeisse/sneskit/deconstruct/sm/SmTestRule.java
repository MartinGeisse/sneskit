package name.martingeisse.sneskit.deconstruct.sm;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.Rule;

import java.io.File;

public class SmTestRule implements Rule {

    @Override
    public void run(JsonObject ruleConfiguration, byte[] rom, File partsFolder) {
        System.out.println("TEST");
    }

}

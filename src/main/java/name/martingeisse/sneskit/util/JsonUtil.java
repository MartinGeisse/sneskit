package name.martingeisse.sneskit.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Map;

public final class JsonUtil {

    private JsonUtil() {
    }

    public static String getString(JsonObject container, String name) {
        JsonElement element = container.get(name);
        if (element == null) {
            throw new KitException("missing " + name);
        } else if (element.isJsonPrimitive()) {
            return element.getAsString();
        }
        throw new KitException(name + " is not a string: " + element);
    }

    public static int getNumber(JsonObject container, String name) {
        JsonElement element = container.get(name);
        if (element == null) {
            throw new KitException("missing " + name);
        } else if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = (JsonPrimitive)element;
            if (primitive.isNumber()) {
                return primitive.getAsInt();
            } else if (primitive.isString()) {
                String s = primitive.getAsString();
                try {
                    if (s.startsWith("0x")) {
                        return Integer.parseInt(s.substring(2), 16);
                    } else {
                        return Integer.parseInt(s);
                    }
                } catch (NumberFormatException e) {
                    // fall through to throw statement below
                }
            }
        }
        throw new KitException(name + " is not a number: " + element);
    }

    public static JsonObject getShallowMerged(JsonObject... objects) {
        JsonObject result = new JsonObject();
        for (JsonObject o : objects) {
            for (Map.Entry<String, JsonElement> entry : o.entrySet()) {
                result.add(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

}

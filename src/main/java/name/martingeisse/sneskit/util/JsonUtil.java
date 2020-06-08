package name.martingeisse.sneskit.util;

import com.google.gson.*;

import java.util.Map;

public final class JsonUtil {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtil() {
    }

    public static String getString(JsonObject container, String name) {
        JsonElement element = container.get(name);
        if (element == null) {
            throw new KitException("missing property '" + name + "'");
        } else if (element.isJsonPrimitive()) {
            return element.getAsString();
        }
        throw new KitException("property '" + name + "' is not a string: " + element);
    }

    public static int getNumber(JsonObject container, String name) {
        JsonElement element = container.get(name);
        if (element == null) {
            throw new KitException("missing property '" + name + "'");
        } else if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = (JsonPrimitive) element;
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
        throw new KitException("property '" + name + "' is not a number: " + element);
    }

    public static int getAddress(JsonObject container, String name, AddressKind requestedKind) {
        JsonElement element = container.get(name);
        if (element == null) {
            throw new KitException("missing property '" + name + "'");
        } else if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = (JsonPrimitive) element;
            if (primitive.isString()) {
                String s = primitive.getAsString();
                AddressKind jsonKind;
                if (s.startsWith("v")) {
                    jsonKind = AddressKind.VIRTUAL;
                } else if (s.startsWith("p")) {
                    jsonKind = AddressKind.PHYSICAL;
                } else {
                    jsonKind = null;
                }
                if (jsonKind != null) {
                    s = s.substring(1);
                    int value;
                    try {
                        if (s.startsWith("0x")) {
                            value = Integer.parseInt(s.substring(2), 16);
                        } else {
                            value = Integer.parseInt(s);
                        }
                        return AddressKind.convert(jsonKind, requestedKind, value);
                    } catch (NumberFormatException e) {
                        // fall through to throw statement below
                    }
                }
            }
        }
        throw new KitException("property '" + name + "' is not an address: " + element);
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

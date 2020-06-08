package name.martingeisse.sneskit.util;

/**
 * Currently implements LoROM mapping only.
 */
public final class AddressUtil {

    private AddressUtil() {
    }

    public static int virtualToPhysical(int virtual) {
        return ((virtual & 0x7f_0000) >> 1) | (virtual & 0x7fff);
    }

    public static int physicalToVirtual(int physical) {
        return 0x80_8000 | ((physical << 1) & 0x7f_0000) | (physical & 0x7fff);
    }

}

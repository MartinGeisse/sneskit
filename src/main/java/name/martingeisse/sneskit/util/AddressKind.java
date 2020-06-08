package name.martingeisse.sneskit.util;

public enum AddressKind {
    VIRTUAL,
    PHYSICAL;

    public static int convert(AddressKind fromKind, AddressKind toKind, int address) {
        if (fromKind == toKind) {
            return address;
        } else if (fromKind == VIRTUAL) {
            return AddressUtil.virtualToPhysical(address);
        } else {
            return AddressUtil.physicalToVirtual(address);
        }
    }

}

package name.martingeisse.sneskit.tools;

public class VirtualToPhysical {

    public static void main(String[] args) {
        int virtual = 0xae_8000;
        int physical = ((virtual & 0x7f_0000) >> 1) | (virtual & 0x7fff);
        System.out.println("virtual " + Integer.toHexString(virtual) + " = physical " + Integer.toHexString(physical));
    }
}

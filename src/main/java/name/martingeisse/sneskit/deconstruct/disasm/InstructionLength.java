package name.martingeisse.sneskit.deconstruct.disasm;

public enum InstructionLength {
    FIXED1(1),
    FIXED2(2),
    FIXED3(3),
    FIXED4(4),
    A23(1, true, false),
    X23(1, false, true);

    private final int baseLength;
    private final boolean addA;
    private final boolean addX;

    InstructionLength(int length) {
        this(length, false, false);
    }

    InstructionLength(int baseLength, boolean addA, boolean addX) {
        this.baseLength = baseLength;
        this.addA = addA;
        this.addX = addX;
    }

    public int getActualLength(InstructionFormat format) {
        return baseLength + (addA ? format.getAccumulatorBytes() : 0) + (addX ? format.getIndexBytes() : 0);
    }

    public static InstructionLength getFixed(int length) {
        switch (length) {

            case 1:
                return FIXED1;

            case 2:
                return FIXED2;

            case 3:
                return FIXED3;

            case 4:
                return FIXED4;

            default:
                throw new IllegalArgumentException("no such fixed instruction length: " + length);

        }
    }

}

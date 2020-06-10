package name.martingeisse.sneskit.deconstruct.disasm;

public enum AddressingMode {

    ABSOLUTE(InstructionLength.FIXED3),

    ABSOLUTE_X(InstructionLength.FIXED3),

    ABSOLUTE_Y(InstructionLength.FIXED3),

    ABSOLUTE_POINTER(InstructionLength.FIXED3),

    ABSOLUTE_LONGPOINTER(InstructionLength.FIXED3),

    ABSOLUTE_X_POINTER(InstructionLength.FIXED3),

    DIRECT(InstructionLength.FIXED2),

    DIRECT_X(InstructionLength.FIXED2),

    DIRECT_Y(InstructionLength.FIXED2),

    DIRECT_POINTER(InstructionLength.FIXED2),

    DIRECT_LONGPOINTER(InstructionLength.FIXED2),

    DIRECT_X_POINTER(InstructionLength.FIXED2),

    DIRECT_POINTER_Y(InstructionLength.FIXED2),

    DIRECT_LONGPOINTER_Y(InstructionLength.FIXED2),

    IMMEDIATE_8(InstructionLength.FIXED2),

    IMMEDIATE_16(InstructionLength.FIXED3),

    IMMEDIATE_A(InstructionLength.A23),

    IMMEDIATE_X(InstructionLength.X23),

    // includes accumulator addressing, as the difference is only in assembler syntax
    IMPLIED(InstructionLength.FIXED1),

    LONG(InstructionLength.FIXED4),

    LONG_X(InstructionLength.FIXED4),

    RELATIVE_8(InstructionLength.FIXED2),

    RELATIVE_16(InstructionLength.FIXED3),

    SOURCE_DESTINATION(InstructionLength.FIXED3),

    STACK_S(InstructionLength.FIXED2),

    STACK_S_POINTER_Y(InstructionLength.FIXED2);

    private final InstructionLength defaultLength;

    AddressingMode(InstructionLength defaultLength) {
        this.defaultLength = defaultLength;
    }

    public InstructionLength getDefaultLength() {
        return defaultLength;
    }

}

package name.martingeisse.sneskit.deconstruct.disasm;

import name.martingeisse.sneskit.util.KitException;

/**
 * Instructions are disassembled under the assumption of a specific format. If the same instruction address gets
 * disassembled again, we verify that the format is the same. Code that gets executed using multiple formats depending
 * on the caller is currently rejected (specific cases might be supported in the future, e.g. when a small subroutine
 * behaves the same for multiple formats).
 * <p>
 * Note that the decimal-mode flag is not considered part of the instruction format because decoding works the same
 * for decimal-mode, only the result of some operations is different.
 */
enum InstructionFormat {
    EMULATION(false, false),
    NATIVE_A8_X8(false, false),
    NATIVE_A8_X16(false, true),
    NATIVE_A16_X8(true, false),
    NATIVE_A16_X16(true, true),
    UNKNOWN(false, false);

    private final boolean wideAccumulator;
    private final boolean wideIndex;

    InstructionFormat(boolean wideAccumulator, boolean wideIndex) {
        this.wideAccumulator = wideAccumulator;
        this.wideIndex = wideIndex;
    }

    public boolean isWideAccumulator() {
        if (this == UNKNOWN) {
            throw new KitException("trying to get the A size for an unknown instruction format");
        }
        return wideAccumulator;
    }

    public int getAccumulatorBytes() {
        return isWideAccumulator() ? 2 : 1;
    }

    public boolean isWideIndex() {
        if (this == UNKNOWN) {
            throw new KitException("trying to get the X size for an unknown instruction format");
        }
        return wideIndex;
    }

    public int getIndexBytes() {
        return isWideIndex() ? 2 : 1;
    }

}

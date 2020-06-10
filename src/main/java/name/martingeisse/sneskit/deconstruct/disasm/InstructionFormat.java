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
    EMULATION(true, false, false),
    NATIVE_A8_X8(false, false, false),
    NATIVE_A8_X16(false, false, true),
    NATIVE_A8_XU(false, false, null),
    NATIVE_A16_X8(false, true, false),
    NATIVE_A16_X16(false, true, true),
    NATIVE_A16_XU(false, true, null),
    NATIVE_AU_X8(false, null, false),
    NATIVE_AU_X16(false, null, true),
    NATIVE_AU_XU(false, null, null),
    UNKNOWN(null, null, null);

    private final Boolean emulated;
    private final Boolean wideAccumulator;
    private final Boolean wideIndex;

    InstructionFormat(Boolean emulated, Boolean wideAccumulator, Boolean wideIndex) {
        this.emulated = emulated;
        this.wideAccumulator = wideAccumulator;
        this.wideIndex = wideIndex;
    }

    public boolean isEmulated() {
        if (emulated == null) {
            throw new KitException("trying to get the emulated-ness for an instruction format that does not know it: " + this);
        }
        return emulated;
    }

    public boolean isWideAccumulator() {
        if (wideAccumulator == null) {
            throw new KitException("trying to get the A size for an instruction format that does not know it: " + this);
        }
        return wideAccumulator;
    }

    public int getAccumulatorBytes() {
        return isWideAccumulator() ? 2 : 1;
    }

    public boolean isWideIndex() {
        if (wideIndex == null) {
            throw new KitException("trying to get the X size for an instruction format that does not know it: " + this);
        }
        return wideIndex;
    }

    public int getIndexBytes() {
        return isWideIndex() ? 2 : 1;
    }

    public static InstructionFormat getCommon(InstructionFormat a, InstructionFormat b) {
        if (a == b) {
            return a;
        }
        if (a == UNKNOWN || b == UNKNOWN) {
            return UNKNOWN;
        }
        if (a == EMULATION || b == EMULATION) {
            return UNKNOWN;
        }
        Boolean wideAccumulator = getCommon(a.wideAccumulator, b.wideAccumulator);
        Boolean wideIndex = getCommon(a.wideIndex, b.wideIndex);
        return getNativeFormat(wideAccumulator, wideIndex);
    }

    private static Boolean getCommon(Boolean a, Boolean b) {
        return (a == null || b == null) ? null : a.booleanValue() == b.booleanValue() ? a : null;
    }

    private static InstructionFormat getNativeFormat(Boolean wideAccumulator, Boolean wideIndex) {
        if (wideAccumulator == null) {
            return wideIndex == null ? NATIVE_AU_XU : wideIndex ? NATIVE_AU_X16 : NATIVE_AU_X8;
        } else if (wideAccumulator) {
            return wideIndex == null ? NATIVE_A16_XU : wideIndex ? NATIVE_A16_X16 : NATIVE_A16_X8;
        } else {
            return wideIndex == null ? NATIVE_A8_XU : wideIndex ? NATIVE_A8_X16 : NATIVE_A8_X8;
        }
    }

    public InstructionFormat getWithWideAccumulatorSet(boolean wideAccumulator) {
        if (this == UNKNOWN || this == EMULATION) {
            return this;
        }
        return getNativeFormat(wideAccumulator, wideIndex);
    }

    public InstructionFormat getWithWideIndexSet(boolean wideIndex) {
        if (this == UNKNOWN || this == EMULATION) {
            return this;
        }
        return getNativeFormat(wideAccumulator, wideIndex);
    }

}

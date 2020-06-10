package name.martingeisse.sneskit.deconstruct.disasm;

/**
 * This decouples the {@link Disassembler} class from knowing specific opcodes, so the {@link InstructionTable}
 * fully describes all instructions.
 */
public enum InstructionSpecialEffect {

    NONE,

    REP {
        @Override
        public InstructionFormat getNextInstructionFormat(InstructionFormat current, int immediateValue) {
            if ((immediateValue & 32) != 0) {
                current = current.getWithWideAccumulatorSet(false);
            }
            if ((immediateValue & 16) != 0) {
                current = current.getWithWideIndexSet(false);
            }
            return current;
        }
    },

    SEP {
        @Override
        public InstructionFormat getNextInstructionFormat(InstructionFormat current, int immediateValue) {
            if ((immediateValue & 32) != 0) {
                current = current.getWithWideAccumulatorSet(true);
            }
            if ((immediateValue & 16) != 0) {
                current = current.getWithWideIndexSet(true);
            }
            return current;
        }
    },

    XCE {
        @Override
        public InstructionFormat getNextInstructionFormat(InstructionFormat current, int immediateValue) {
            return InstructionFormat.UNKNOWN;
        }
    };

    public InstructionFormat getNextInstructionFormat(InstructionFormat current, int immediateValue) {
        return current;
    }

}

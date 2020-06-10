package name.martingeisse.sneskit.deconstruct.disasm;

public enum AddressingMode {

    ABSOLUTE(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue);
        }
    },

    ABSOLUTE_X(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue) + ",X";
        }
    },

    ABSOLUTE_Y(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue) + ",Y";
        }
    },

    ABSOLUTE_POINTER(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            return "(" + toHex(immediateValue) + ")";
        }
    },

    ABSOLUTE_LONGPOINTER(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            return "[" + toHex(immediateValue) + "]";
        }
    },

    ABSOLUTE_X_POINTER(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            return "(" + toHex(immediateValue) + ",X)";
        }
    },

    DIRECT(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue);
        }
    },

    DIRECT_X(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue) + ",X";
        }
    },

    DIRECT_Y(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue) + ",Y";
        }
    },

    DIRECT_POINTER(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return "(" + toHex(immediateValue) + ")";
        }
    },

    DIRECT_LONGPOINTER(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return "[" + toHex(immediateValue) + "]";
        }
    },

    DIRECT_X_POINTER(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return "(" + toHex(immediateValue) + ",X)";
        }
    },

    DIRECT_POINTER_Y(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return "(" + toHex(immediateValue) + "),Y";
        }
    },

    DIRECT_LONGPOINTER_Y(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return "[" + toHex(immediateValue) + "],Y";
        }
    },

    IMMEDIATE_8(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return "#" + toHex(immediateValue);
        }
    },

    IMMEDIATE_16(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            return "#" + toHex(immediateValue);
        }
    },

    IMMEDIATE_A(InstructionLength.A23) {
        @Override
        public String argumentToString(int immediateValue) {
            return "#" + toHex(immediateValue);
        }
    },

    IMMEDIATE_X(InstructionLength.X23) {
        @Override
        public String argumentToString(int immediateValue) {
            return "#" + toHex(immediateValue);
        }
    },

    // includes accumulator addressing, as the difference is only in assembler syntax
    IMPLIED(InstructionLength.FIXED1) {
        @Override
        public String argumentToString(int immediateValue) {
            return "";
        }
    },

    LONG(InstructionLength.FIXED4) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue);
        }
    },

    LONG_X(InstructionLength.FIXED4) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue) + ",X";
        }
    },

    RELATIVE_8(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue);
        }
    },

    RELATIVE_16(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue);
        }
    },

    SOURCE_DESTINATION(InstructionLength.FIXED3) {
        @Override
        public String argumentToString(int immediateValue) {
            int source = immediateValue & 0xff;
            int destination = (immediateValue >> 8) & 0xff;
            return "#" + toHex(source) + ",#" + toHex(destination);
        }
    },

    STACK_S(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return toHex(immediateValue) + ",S";
        }
    },

    STACK_S_POINTER_Y(InstructionLength.FIXED2) {
        @Override
        public String argumentToString(int immediateValue) {
            return "(" + toHex(immediateValue) + ",S),Y";
        }
    };

    private final InstructionLength defaultLength;

    AddressingMode(InstructionLength defaultLength) {
        this.defaultLength = defaultLength;
    }

    public InstructionLength getDefaultLength() {
        return defaultLength;
    }

    public abstract String argumentToString(int immediateValue);

    private static String toHex(int value) {
        return "$" + Integer.toHexString(value);
    }

}

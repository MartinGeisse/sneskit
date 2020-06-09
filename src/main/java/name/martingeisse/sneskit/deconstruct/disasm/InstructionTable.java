package name.martingeisse.sneskit.deconstruct.disasm;

public final class InstructionTable {

    public static final Entry[] TABLE = new Entry[]{

            // 00-07
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 08-0f
            new Entry("PHP", 1),
            null,
            null,
            new Entry("PHD", 1),
            null,
            null,
            null,
            null,


            // 10-17
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 18-1f
            new Entry("CLC", 1),
            null,
            null,
            new Entry("TCS", 1),
            null,
            null,
            null,
            null,

            // 20-27
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 28-2f
            new Entry("PLP", 1),
            null,
            null,
            new Entry("PLD", 1),
            null,
            null,
            null,
            null,

            // 30-37
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 38-3f
            new Entry("SEC", 1),
            null,
            null,
            new Entry("TSC", 1),
            null,
            null,
            null,
            null,

            // 40-47
            new Entry("RTI", 1).diverting(),
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 48-4f
            new Entry("PHA", 1),
            null,
            null,
            new Entry("PHK", 1),
            null,
            null,
            null,
            null,

            // 50-57
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 58-5f
            new Entry("CLI", 1),
            null,
            new Entry("PHY", 1),
            new Entry("TCD", 1),
            new Entry("JMP (long) to %l", 4).jumping(StaticJumpAddressingMode.LONG).diverting(),
            null,
            null,
            null,

            // 60-67
            new Entry("RTS", 1),
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 68-6f
            new Entry("PLA", 1),
            null,
            null,
            new Entry("RTL", 1),
            null,
            null,
            null,
            null,

            // 70-77
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 78-7f
            new Entry("SEI", 1),
            null,
            new Entry("PLY", 1),
            new Entry("TDC", 1),
            null,
            null,
            null,
            null,

            // 80-87
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 88-8f
            null,
            null,
            new Entry("TXA", 1),
            new Entry("PHB", 1),
            null,
            null,
            null,
            null,

            // 90-97
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 98-9f
            new Entry("TYA", 1),
            null,
            new Entry("TXS", 1),
            new Entry("TXY", 1),
            null,
            null,
            null,
            null,

            // a0-a7
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // a8-af
            new Entry("TAY", 1),
            null,
            new Entry("TAX", 1),
            new Entry("PLB", 1),
            null,
            null,
            null,
            null,

            // b0-b7
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // b8-bf
            new Entry("CLV", 1),
            null,
            new Entry("TSX", 1),
            new Entry("TYX", 1),
            null,
            null,
            null,
            null,

            // c0-c7
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // c8-cf
            null,
            null,
            null,
            new Entry("WAI", 1),
            null,
            null,
            null,
            null,

            // d0-d7
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // d8-df
            new Entry("CLD", 1),
            null,
            new Entry("PHX", 1),
            new Entry("STP", 1),
            null,
            null,
            null,
            null,

            // e0-e7
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // e8-ef
            null,
            null,
            new Entry("NOP", 1),
            new Entry("XBA", 1),
            null,
            null,
            null,
            null,

            // f0-f7
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // f8-ff
            new Entry("SED", 1),
            null,
            new Entry("PLX", 1),
            new Entry("XCE", 1),
            null,
            null,
            null,
            null,


    };

    public static class Entry {

        private final String mnemonic;
        private final InstructionLength length;
        private boolean divert;
        private StaticJumpAddressingMode staticJumpAddressingMode;

        private Entry(String mnemonic, InstructionLength length, boolean divert) {
            this.mnemonic = mnemonic;
            this.length = length;
            this.divert = divert;
        }

        public Entry(String mnemonic, InstructionLength length) {
            this(mnemonic, length, false);
        }

        public Entry(String mnemonic, int length) {
            this(mnemonic, InstructionLength.getFixed(length), false);
        }

        public Entry diverting() {
            divert = true;
            return this;
        }

        public Entry jumping(StaticJumpAddressingMode mode) {
            this.staticJumpAddressingMode = mode;
            return this;
        }

        public String getMnemonic() {
            return mnemonic;
        }

        public InstructionLength getLength() {
            return length;
        }

        public boolean isDivert() {
            return divert;
        }

        public StaticJumpAddressingMode getStaticJumpAddressingMode() {
            return staticJumpAddressingMode;
        }

    }

    public enum StaticJumpAddressingMode {

        K_BANK_RELATIVE {
            @Override
            public int getJumpTarget(int instructionAddress, int immediate) {
                throw new UnsupportedOperationException("not yet implemented");
            }
        },

        K_BANK_ABSOLUTE {
            @Override
            public int getJumpTarget(int instructionAddress, int immediate) {
                if (immediate < 0 || immediate > 0xffff) {
                    throw new IllegalArgumentException("invalid immediate value for K-bank absolute jump addressing: " + immediate);
                }
                return (instructionAddress & 0xff0000) | immediate;
            }
        },

        LONG {
            @Override
            public int getJumpTarget(int instructionAddress, int immediate) {
                if (immediate < 0 || immediate > 0xffffff) {
                    throw new IllegalArgumentException("invalid immediate value for long jump addressing: " + immediate);
                }
                return immediate;
            }
        };

        public abstract int getJumpTarget(int instructionAddress, int immediate);

    }

}

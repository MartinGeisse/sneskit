package name.martingeisse.sneskit.deconstruct.disasm;

public final class InstructionTable {

    public static final Entry[] TABLE = new Entry[]{

            // 00-07
            null,
            new Entry("ORA ...", 2),
            null,
            new Entry("ORA ...", 2),
            new Entry("TSB ...", 2),
            new Entry("ORA ...", 2),
            new Entry("ASL ...", 2),
            new Entry("ORA ...", 2),

            // 08-0f
            new Entry("PHP", 1),
            new Entry("ORA ...", InstructionLength.A23),
            new Entry("ASL ...", 1),
            new Entry("PHD", 1),
            new Entry("TSB ...", 3),
            new Entry("ORA ...", 3),
            new Entry("ASL ...", 3),
            new Entry("ORA ...", 4),


            // 10-17
            null,
            new Entry("ORA ...", 2),
            new Entry("ORA ...", 2),
            new Entry("ORA ...", 2),
            new Entry("TRB ...", 2),
            new Entry("ORA ...", 2),
            new Entry("ASL ...", 2),
            new Entry("ORA ...", 2),

            // 18-1f
            new Entry("CLC", 1),
            new Entry("ORA ...", 3),
            new Entry("INC ...", 1),
            new Entry("TCS", 1),
            new Entry("TRB ...", 3),
            new Entry("ORA ...", 3),
            new Entry("ASL ...", 3),
            new Entry("ORA ...", 4),

            // 20-27
            null,
            new Entry("AND ...", 2),
            null,
            new Entry("AND ...", 2),
            new Entry("BIT ...", 2),
            new Entry("AND ...", 2),
            new Entry("ROL ...", 2),
            new Entry("AND ...", 2),

            // 28-2f
            new Entry("PLP", 1),
            new Entry("AND ...", InstructionLength.A23),
            new Entry("ROL ...", 1),
            new Entry("PLD", 1),
            new Entry("BIT ...", 3),
            new Entry("AND ...", 3),
            new Entry("ROL ...", 3),
            new Entry("AND ...", 4),

            // 30-37
            null,
            new Entry("AND ...", 2),
            new Entry("AND ...", 2),
            new Entry("AND ...", 2),
            new Entry("BIT ...", 2),
            new Entry("AND ...", 2),
            new Entry("ROL ...", 2),
            new Entry("AND ...", 2),

            // 38-3f
            new Entry("SEC", 1),
            new Entry("AND ...", 3),
            new Entry("DEC ...", 1),
            new Entry("TSC", 1),
            new Entry("BIT ...", 3),
            new Entry("AND ...", 3),
            new Entry("ROL ...", 3),
            new Entry("AND ...", 4),

            // 40-47
            new Entry("RTI", 1).diverting(),
            new Entry("EOR ...", 2),
            null,
            new Entry("EOR ...", 2),
            null,
            new Entry("EOR ...", 2),
            new Entry("LSR ...", 2),
            new Entry("EOR ...", 2),

            // 48-4f
            new Entry("PHA", 1),
            new Entry("EOR ...", InstructionLength.A23),
            new Entry("LSR ...", 1),
            new Entry("PHK", 1),
            null,
            new Entry("EOR ...", 3),
            new Entry("LSR ...", 3),
            new Entry("EOR ...", 4),

            // 50-57
            null,
            new Entry("EOR ...", 2),
            new Entry("EOR ...", 2),
            new Entry("EOR ...", 2),
            null,
            new Entry("EOR ...", 2),
            new Entry("LSR ...", 2),
            new Entry("EOR ...", 2),

            // 58-5f
            new Entry("CLI", 1),
            new Entry("EOR ...", 3),
            new Entry("PHY", 1),
            new Entry("TCD", 1),
            new Entry("JMP (long) to %l", 4).jumping(StaticJumpAddressingMode.LONG).diverting(),
            new Entry("EOR ...", 3),
            new Entry("LSR ...", 3),
            new Entry("EOR ...", 4),

            // 60-67
            new Entry("RTS", 1),
            new Entry("ADC ...", 2),
            null,
            new Entry("ADC ...", 2),
            null,
            new Entry("ADC ...", 2),
            new Entry("ROR ...", 2),
            new Entry("ADC ...", 2),

            // 68-6f
            new Entry("PLA", 1),
            new Entry("ADC ...", InstructionLength.A23),
            new Entry("ROR ...", 1),
            new Entry("RTL", 1),
            null,
            new Entry("ADC ...", 3),
            new Entry("ROR ...", 3),
            new Entry("ADC ...", 4),

            // 70-77
            null,
            new Entry("ADC ...", 2),
            new Entry("ADC ...", 2),
            new Entry("ADC ...", 2),
            null,
            new Entry("ADC ...", 2),
            new Entry("ROR ...", 2),
            new Entry("ADC ...", 2),

            // 78-7f
            new Entry("SEI", 1),
            new Entry("ADC ...", 3),
            new Entry("PLY", 1),
            new Entry("TDC", 1),
            null,
            new Entry("ADC ...", 3),
            new Entry("ROR ...", 3),
            new Entry("ADC ...", 4),

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
            new Entry("DEY ...", 1),
            new Entry("BIT ...", InstructionLength.A23),
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
            new Entry("CPY ...", InstructionLength.X23),
            new Entry("CMP ...", 2),
            null,
            new Entry("CMP ...", 2),
            new Entry("CPY ...", 2),
            new Entry("CMP ...", 2),
            new Entry("DEC ...", 2),
            new Entry("CMP ...", 2),

            // c8-cf
            new Entry("INY ...", 1),
            new Entry("CMP ...", InstructionLength.A23),
            new Entry("DEX ...", 1),
            new Entry("WAI", 1),
            new Entry("CPY ...", 3),
            new Entry("CMP ...", 3),
            new Entry("DEC ...", 3),
            new Entry("CMP ...", 4),

            // d0-d7
            null,
            new Entry("CMP ...", 2),
            new Entry("CMP ...", 2),
            new Entry("CMP ...", 2),
            null,
            new Entry("CMP ...", 2),
            new Entry("DEC ...", 2),
            new Entry("CMP ...", 2),

            // d8-df
            new Entry("CLD", 1),
            new Entry("CMP ...", 3),
            new Entry("PHX", 1),
            new Entry("STP", 1),
            null,
            new Entry("CMP ...", 3),
            new Entry("DEC ...", 3),
            new Entry("CMP ...", 4),

            // e0-e7
            new Entry("CPX ...", InstructionLength.X23),
            new Entry("SBC ...", 2),
            null,
            new Entry("SBC ...", 2),
            new Entry("CPX ...", 2),
            new Entry("SBC ...", 2),
            new Entry("INC ...", 2),
            new Entry("SBC ...", 2),

            // e8-ef
            new Entry("INX ...", 1),
            new Entry("SBC ...", InstructionLength.A23),
            new Entry("NOP", 1),
            new Entry("XBA", 1),
            new Entry("CPX ...", 3),
            new Entry("SBC ...", 3),
            new Entry("INC ...", 3),
            new Entry("SBC ...", 4),

            // f0-f7
            null,
            new Entry("SBC ...", 2),
            new Entry("SBC ...", 2),
            new Entry("SBC ...", 2),
            null,
            new Entry("SBC ...", 2),
            new Entry("INC ...", 2),
            new Entry("SBC ...", 2),

            // f8-ff
            new Entry("SED", 1),
            new Entry("SBC ...", 3),
            new Entry("PLX", 1),
            new Entry("XCE", 1),
            null,
            new Entry("SBC ...", 3),
            new Entry("INC ...", 3),
            new Entry("SBC ...", 4),


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

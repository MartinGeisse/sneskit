package name.martingeisse.sneskit.deconstruct.disasm;

public final class InstructionTable {

    public static final Entry[] TABLE = new Entry[]{

            // 00-07
            new Entry("BRK ...", 2), // defined as 1-byte but effectively 2-byte
            new Entry("ORA ...", 2),
            new Entry("COP ...", 2),
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
            new Entry("BPL ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
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
            new Entry("JSR ...", 3).jumping(StaticJumpAddressingMode.K_BANK_ABSOLUTE),
            new Entry("AND ...", 2),
            new Entry("JSL ...", 4).jumping(StaticJumpAddressingMode.LONG),
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
            new Entry("BMI ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
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
            new Entry("WDM ...", 2),
            new Entry("EOR ...", 2),
            new Entry("MVP ...", 3),
            new Entry("EOR ...", 2),
            new Entry("LSR ...", 2),
            new Entry("EOR ...", 2),

            // 48-4f
            new Entry("PHA", 1),
            new Entry("EOR ...", InstructionLength.A23),
            new Entry("LSR ...", 1),
            new Entry("PHK", 1),
            new Entry("JMP ...", 3).jumping(StaticJumpAddressingMode.K_BANK_ABSOLUTE).diverting(),
            new Entry("EOR ...", 3),
            new Entry("LSR ...", 3),
            new Entry("EOR ...", 4),

            // 50-57
            new Entry("BVC ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("EOR ...", 2),
            new Entry("EOR ...", 2),
            new Entry("EOR ...", 2),
            new Entry("MVN ...", 3),
            new Entry("EOR ...", 2),
            new Entry("LSR ...", 2),
            new Entry("EOR ...", 2),

            // 58-5f
            new Entry("CLI", 1),
            new Entry("EOR ...", 3),
            new Entry("PHY", 1),
            new Entry("TCD", 1),
            new Entry("JML ...", 4).jumping(StaticJumpAddressingMode.LONG).diverting(),
            new Entry("EOR ...", 3),
            new Entry("LSR ...", 3),
            new Entry("EOR ...", 4),

            // 60-67
            new Entry("RTS", 1),
            new Entry("ADC ...", 2),
            new Entry("PER ...", 2),
            new Entry("ADC ...", 2),
            new Entry("STZ ...", 2),
            new Entry("ADC ...", 2),
            new Entry("ROR ...", 2),
            new Entry("ADC ...", 2),

            // 68-6f
            new Entry("PLA", 1),
            new Entry("ADC ...", InstructionLength.A23),
            new Entry("ROR ...", 1),
            new Entry("RTL", 1),
            new Entry("JMP ...", 3).diverting(),
            new Entry("ADC ...", 3),
            new Entry("ROR ...", 3),
            new Entry("ADC ...", 4),

            // 70-77
            new Entry("BVS ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("ADC ...", 2),
            new Entry("ADC ...", 2),
            new Entry("ADC ...", 2),
            new Entry("STZ ...", 2),
            new Entry("ADC ...", 2),
            new Entry("ROR ...", 2),
            new Entry("ADC ...", 2),

            // 78-7f
            new Entry("SEI", 1),
            new Entry("ADC ...", 3),
            new Entry("PLY", 1),
            new Entry("TDC", 1),
            new Entry("JMP ...", 3).diverting(),
            new Entry("ADC ...", 3),
            new Entry("ROR ...", 3),
            new Entry("ADC ...", 4),

            // 80-87
            new Entry("BRA ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8).diverting(),
            new Entry("STA ...", 2),
            new Entry("BRL ...", 3).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_16).diverting(),
            new Entry("STA ...", 2),
            new Entry("STY ...", 2),
            new Entry("STA ...", 2),
            new Entry("STX ...", 2),
            new Entry("STA ...", 2),

            // 88-8f
            new Entry("DEY ...", 1),
            new Entry("BIT ...", InstructionLength.A23),
            new Entry("TXA", 1),
            new Entry("PHB", 1),
            new Entry("STY ...", 3),
            new Entry("STA ...", 3),
            new Entry("STX ...", 3),
            new Entry("STA ...", 4),

            // 90-97
            new Entry("BCC ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("STA ...", 2),
            new Entry("STA ...", 2),
            new Entry("STA ...", 2),
            new Entry("STY ...", 2),
            new Entry("STA ...", 2),
            new Entry("STX ...", 2),
            new Entry("STA ...", 2),

            // 98-9f
            new Entry("TYA", 1),
            new Entry("STA ...", 3),
            new Entry("TXS", 1),
            new Entry("TXY", 1),
            new Entry("STZ ...", 3),
            new Entry("STA ...", 3),
            new Entry("STZ ...", 3),
            new Entry("STA ...", 4),

            // a0-a7
            new Entry("LDY ...", InstructionLength.X23),
            new Entry("LDA ...", 2),
            new Entry("LDX ...", InstructionLength.X23),
            new Entry("LDA ...", 2),
            new Entry("LDY ...", 2),
            new Entry("LDA ...", 2),
            new Entry("LDX ...", 2),
            new Entry("LDA ...", 2),

            // a8-af
            new Entry("TAY", 1),
            new Entry("LDA ...", InstructionLength.A23),
            new Entry("TAX", 1),
            new Entry("PLB", 1),
            new Entry("LDY ...", 3),
            new Entry("LDA ...", 3),
            new Entry("LDX ...", 3),
            new Entry("LDA ...", 4),

            // b0-b7
            new Entry("BCS ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("LDA ...", 2),
            new Entry("LDA ...", 2),
            new Entry("LDA ...", 2),
            new Entry("LDY ...", 2),
            new Entry("LDA ...", 2),
            new Entry("LDX ...", 2),
            new Entry("LDA ...", 2),

            // b8-bf
            new Entry("CLV", 1),
            new Entry("LDA ...", 3),
            new Entry("TSX", 1),
            new Entry("TYX", 1),
            new Entry("LDY ...", 3),
            new Entry("LDA ...", 3),
            new Entry("LDX ...", 3),
            new Entry("LDA ...", 4),

            // c0-c7
            new Entry("CPY ...", InstructionLength.X23),
            new Entry("CMP ...", 2),
            new Entry("REP ...", 2).withSpecialEffect(InstructionSpecialEffect.REP),
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
            new Entry("BNE ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("CMP ...", 2),
            new Entry("CMP ...", 2),
            new Entry("CMP ...", 2),
            new Entry("PEI ...", 2),
            new Entry("CMP ...", 2),
            new Entry("DEC ...", 2),
            new Entry("CMP ...", 2),

            // d8-df
            new Entry("CLD", 1),
            new Entry("CMP ...", 3),
            new Entry("PHX", 1),
            new Entry("STP", 1),
            new Entry("JMP ...", 3).diverting(),
            new Entry("CMP ...", 3),
            new Entry("DEC ...", 3),
            new Entry("CMP ...", 4),

            // e0-e7
            new Entry("CPX ...", InstructionLength.X23),
            new Entry("SBC ...", 2),
            new Entry("SEP ...", 2).withSpecialEffect(InstructionSpecialEffect.SEP),
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
            new Entry("BEQ ...", 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("SBC ...", 2),
            new Entry("SBC ...", 2),
            new Entry("SBC ...", 2),
            new Entry("PEA ...", 3),
            new Entry("SBC ...", 2),
            new Entry("INC ...", 2),
            new Entry("SBC ...", 2),

            // f8-ff
            new Entry("SED", 1),
            new Entry("SBC ...", 3),
            new Entry("PLX", 1),
            new Entry("XCE", 1),
            new Entry("JSR ...", 3),
            new Entry("SBC ...", 3),
            new Entry("INC ...", 3),
            new Entry("SBC ...", 4),

    };

    public static class Entry {

        private final String mnemonic;
        private final InstructionLength length;
        private boolean divert;
        private StaticJumpAddressingMode staticJumpAddressingMode;
        private InstructionSpecialEffect specialEffect = InstructionSpecialEffect.NONE;

        private Entry(String mnemonic, InstructionLength length) {
            this.mnemonic = mnemonic;
            this.length = length;
        }

        private Entry(String mnemonic, int length) {
            this(mnemonic, InstructionLength.getFixed(length));
        }

        private Entry diverting() {
            divert = true;
            return this;
        }

        private Entry jumping(StaticJumpAddressingMode mode) {
            this.staticJumpAddressingMode = mode;
            return this;
        }

        private Entry withSpecialEffect(InstructionSpecialEffect specialEffect) {
            this.specialEffect = specialEffect;
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

        public InstructionSpecialEffect getSpecialEffect() {
            return specialEffect;
        }

    }

    public enum StaticJumpAddressingMode {

        K_BANK_ABSOLUTE {
            @Override
            public int getJumpTarget(int instructionAddress, int instructionLength, int immediate) {
                if (immediate < 0 || immediate > 0xffff) {
                    throw new IllegalArgumentException("invalid immediate value for K-bank absolute jump addressing: " + immediate);
                }
                return (instructionAddress & 0xff0000) | immediate;
            }
        },

        LONG {
            @Override
            public int getJumpTarget(int instructionAddress, int instructionLength, int immediate) {
                if (immediate < 0 || immediate > 0xffffff) {
                    throw new IllegalArgumentException("invalid immediate value for long jump addressing: " + immediate);
                }
                return immediate;
            }
        },

        K_BANK_RELATIVE_8 {
            @Override
            public int getJumpTarget(int instructionAddress, int instructionLength, int immediate) {
                return (instructionAddress & 0xff0000) |
                        ((instructionAddress + instructionLength + (byte)immediate) & 0xffff);
            }
        },

        K_BANK_RELATIVE_16 {
            @Override
            public int getJumpTarget(int instructionAddress, int instructionLength, int immediate) {
                return (instructionAddress & 0xff0000) |
                        ((instructionAddress + instructionLength + (short)immediate) & 0xffff);
            }
        };

        public abstract int getJumpTarget(int instructionAddress, int instructionLength, int immediate);

    }

}

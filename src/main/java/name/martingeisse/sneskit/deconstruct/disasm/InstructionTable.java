package name.martingeisse.sneskit.deconstruct.disasm;

public final class InstructionTable {

    public static final Entry[] TABLE = new Entry[]{

            // 00-07
            new Entry("BRK ...", AddressingMode.IMPLIED, 2), // defined as 1-byte but effectively 2-byte
            new Entry("ORA ...", AddressingMode.DIRECT_X_POINTER, 2),
            new Entry("COP ...", AddressingMode.IMPLIED, 2),
            new Entry("ORA ...", AddressingMode.STACK_S, 2),
            new Entry("TSB ...", AddressingMode.DIRECT, 2),
            new Entry("ORA ...", AddressingMode.DIRECT, 2),
            new Entry("ASL ...", AddressingMode.DIRECT, 2),
            new Entry("ORA ...", AddressingMode.DIRECT_LONGPOINTER, 2),

            // 08-0f
            new Entry("PHP", AddressingMode.IMPLIED, 1),
            new Entry("ORA ...", AddressingMode.IMMEDIATE_A, InstructionLength.A23),
            new Entry("ASL ...", AddressingMode.IMPLIED, 1),
            new Entry("PHD", AddressingMode.IMPLIED, 1),
            new Entry("TSB ...", AddressingMode.ABSOLUTE, 3),
            new Entry("ORA ...", AddressingMode.ABSOLUTE, 3),
            new Entry("ASL ...", AddressingMode.ABSOLUTE, 3),
            new Entry("ORA ...", AddressingMode.LONG, 4),

            // 10-17
            new Entry("BPL ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("ORA ...", AddressingMode.DIRECT_POINTER_Y, 2),
            new Entry("ORA ...", AddressingMode.DIRECT_POINTER, 2),
            new Entry("ORA ...", AddressingMode.STACK_S_POINTER_Y, 2),
            new Entry("TRB ...", AddressingMode.DIRECT, 2),
            new Entry("ORA ...", AddressingMode.DIRECT_X, 2),
            new Entry("ASL ...", AddressingMode.DIRECT_X, 2),
            new Entry("ORA ...", AddressingMode.DIRECT_LONGPOINTER_Y, 2),

            // 18-1f
            new Entry("CLC", AddressingMode.IMPLIED, 1),
            new Entry("ORA ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("INC ...", AddressingMode.IMPLIED, 1),
            new Entry("TCS", AddressingMode.IMPLIED, 1),
            new Entry("TRB", AddressingMode.ABSOLUTE, 3),
            new Entry("ORA ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("ASL ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("ORA ...", AddressingMode.LONG_X, 4),

            // 20-27
            new Entry("JSR ...", AddressingMode.ABSOLUTE, 3).jumping(StaticJumpAddressingMode.K_BANK_ABSOLUTE),
            new Entry("AND ...", AddressingMode.DIRECT_X_POINTER, 2),
            new Entry("JSL ...", AddressingMode.LONG, 4).jumping(StaticJumpAddressingMode.LONG),
            new Entry("AND ...", AddressingMode.STACK_S, 2),
            new Entry("BIT ...", AddressingMode.DIRECT, 2),
            new Entry("AND ...", AddressingMode.DIRECT, 2),
            new Entry("ROL ...", AddressingMode.DIRECT, 2),
            new Entry("AND ...", AddressingMode.DIRECT_POINTER, 2),

            // 28-2f
            new Entry("PLP", AddressingMode.IMPLIED, 1).withSpecialEffect(InstructionSpecialEffect.PLP),
            new Entry("AND ...", AddressingMode.IMMEDIATE_X, InstructionLength.A23),
            new Entry("ROL ...", AddressingMode.IMPLIED, 1),
            new Entry("PLD", AddressingMode.IMPLIED, 1),
            new Entry("BIT ...", AddressingMode.ABSOLUTE, 3),
            new Entry("AND ...", AddressingMode.ABSOLUTE, 3),
            new Entry("ROL ...", AddressingMode.ABSOLUTE, 3),
            new Entry("AND ...", AddressingMode.LONG, 4),

            // 30-37
            new Entry("BMI ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("AND ...", AddressingMode.DIRECT_POINTER_Y, 2),
            new Entry("AND ...", AddressingMode.DIRECT_POINTER, 2),
            new Entry("AND ...", AddressingMode.STACK_S_POINTER_Y, 2),
            new Entry("BIT ...", AddressingMode.DIRECT_X, 2),
            new Entry("AND ...", AddressingMode.DIRECT_X, 2),
            new Entry("ROL ...", AddressingMode.DIRECT_X, 2),
            new Entry("AND ...", AddressingMode.DIRECT_LONGPOINTER_Y, 2),

            // 38-3f
            new Entry("SEC", AddressingMode.IMPLIED, 1),
            new Entry("AND ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("DEC ...", AddressingMode.IMPLIED, 1),
            new Entry("TSC", AddressingMode.IMPLIED, 1),
            new Entry("BIT ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("AND ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("ROL ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("AND ...", AddressingMode.LONG_X, 4),

            // 40-47
            new Entry("RTI", AddressingMode.IMPLIED, 1).diverting(),
            new Entry("EOR ...", AddressingMode.DIRECT_X_POINTER, 2),
            new Entry("WDM ...", AddressingMode.IMMEDIATE_8, 2),
            new Entry("EOR ...", AddressingMode.STACK_S, 2),
            new Entry("MVP ...", AddressingMode.IMMEDIATE_16, 3),
            new Entry("EOR ...", AddressingMode.DIRECT, 2),
            new Entry("LSR ...", AddressingMode.DIRECT, 2),
            new Entry("EOR ...", AddressingMode.DIRECT_POINTER, 2),

            // 48-4f
            new Entry("PHA", AddressingMode.IMPLIED, 1),
            new Entry("EOR ...", AddressingMode.IMMEDIATE_A, InstructionLength.A23),
            new Entry("LSR ...", AddressingMode.IMPLIED, 1),
            new Entry("PHK", AddressingMode.IMPLIED, 1),
            new Entry("JMP ...", AddressingMode.ABSOLUTE, 3).jumping(StaticJumpAddressingMode.K_BANK_ABSOLUTE).diverting(),
            new Entry("EOR ...", AddressingMode.ABSOLUTE, 3),
            new Entry("LSR ...", AddressingMode.ABSOLUTE, 3),
            new Entry("EOR ...", AddressingMode.LONG, 4),

            // 50-57
            new Entry("BVC ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("EOR ...", AddressingMode.DIRECT_POINTER_Y, 2),
            new Entry("EOR ...", AddressingMode.DIRECT_POINTER, 2),
            new Entry("EOR ...", AddressingMode.STACK_S_POINTER_Y, 2),
            new Entry("MVN ...", AddressingMode.IMMEDIATE_16, 3),
            new Entry("EOR ...", AddressingMode.DIRECT_X, 2),
            new Entry("LSR ...", AddressingMode.DIRECT_X, 2),
            new Entry("EOR ...", AddressingMode.DIRECT_LONGPOINTER_Y, 2),

            // 58-5f
            new Entry("CLI", AddressingMode.IMPLIED, 1),
            new Entry("EOR ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("PHY", AddressingMode.IMPLIED, 1),
            new Entry("TCD", AddressingMode.IMPLIED, 1),
            new Entry("JML ...", AddressingMode.LONG, 4).jumping(StaticJumpAddressingMode.LONG).diverting(),
            new Entry("EOR ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("LSR ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("EOR ...", AddressingMode.LONG_X, 4),

            // 60-67
            new Entry("RTS", AddressingMode.IMPLIED, 1),
            new Entry("ADC ...", AddressingMode.DIRECT_X_POINTER, -1),
            new Entry("PER ...", AddressingMode.IMMEDIATE_16, 3),
            new Entry("ADC ...", AddressingMode.STACK_S, -1),
            new Entry("STZ ...", AddressingMode.DIRECT, 2),
            new Entry("ADC ...", AddressingMode.DIRECT, -1),
            new Entry("ROR ...", AddressingMode.DIRECT, 2),
            new Entry("ADC ...", AddressingMode.DIRECT_POINTER, 2),

            // 68-6f
            new Entry("PLA", AddressingMode.IMPLIED, 1),
            new Entry("ADC ...", AddressingMode.IMMEDIATE_A, InstructionLength.A23),
            new Entry("ROR ...", AddressingMode.IMPLIED, 1),
            new Entry("RTL", AddressingMode.IMPLIED, 1),
            new Entry("JMP ...", AddressingMode.ABSOLUTE_POINTER, 3).diverting(),
            new Entry("ADC ...", AddressingMode.ABSOLUTE, 3),
            new Entry("ROR ...", AddressingMode.ABSOLUTE, 3),
            new Entry("ADC ...", AddressingMode.LONG, 4),

            // 70-77
            new Entry("BVS ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("ADC ...", AddressingMode.DIRECT_POINTER_Y, 2),
            new Entry("ADC ...", AddressingMode.DIRECT_POINTER, 2),
            new Entry("ADC ...", AddressingMode.STACK_S_POINTER_Y, 2),
            new Entry("STZ ...", AddressingMode.DIRECT_X, 2),
            new Entry("ADC ...", AddressingMode.DIRECT_X, 2),
            new Entry("ROR ...", AddressingMode.DIRECT_X, 2),
            new Entry("ADC ...", AddressingMode.DIRECT_LONGPOINTER_Y, 2),

            // 78-7f
            new Entry("SEI", AddressingMode.IMPLIED, 1),
            new Entry("ADC ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("PLY", AddressingMode.IMPLIED, 1),
            new Entry("TDC", AddressingMode.IMPLIED, 1),
            new Entry("JMP ...", AddressingMode.ABSOLUTE_X_POINTER, 3).diverting(),
            new Entry("ADC ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("ROR ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("ADC ...", AddressingMode.LONG_X, 4),

            // 80-87
            new Entry("BRA ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8).diverting(),
            new Entry("STA ...", AddressingMode.DIRECT_X_POINTER, 2),
            new Entry("BRL ...", AddressingMode.RELATIVE_16, 3).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_16).diverting(),
            new Entry("STA ...", AddressingMode.STACK_S, 2),
            new Entry("STY ...", AddressingMode.DIRECT, 2),
            new Entry("STA ...", AddressingMode.DIRECT, 2),
            new Entry("STX ...", AddressingMode.DIRECT, 2),
            new Entry("STA ...", AddressingMode.DIRECT_LONGPOINTER, 2),

            // 88-8f
            new Entry("DEY ...", AddressingMode.IMPLIED, 1),
            new Entry("BIT ...", AddressingMode.IMMEDIATE_A, InstructionLength.A23),
            new Entry("TXA", AddressingMode.IMPLIED, 1),
            new Entry("PHB", AddressingMode.IMPLIED, 1),
            new Entry("STY ...", AddressingMode.ABSOLUTE, 3),
            new Entry("STA ...", AddressingMode.ABSOLUTE, 3),
            new Entry("STX ...", AddressingMode.ABSOLUTE, 3),
            new Entry("STA ...", AddressingMode.LONG, 4),

            // 90-97
            new Entry("BCC ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("STA ...", AddressingMode.DIRECT_POINTER_Y, 2),
            new Entry("STA ...", AddressingMode.DIRECT_POINTER, 2),
            new Entry("STA ...", AddressingMode.STACK_S_POINTER_Y, 2),
            new Entry("STY ...", AddressingMode.DIRECT_X, 2),
            new Entry("STA ...", AddressingMode.DIRECT_X, 2),
            new Entry("STX ...", AddressingMode.DIRECT_Y, 2),
            new Entry("STA ...", AddressingMode.DIRECT_LONGPOINTER_Y, 2),

            // 98-9f
            new Entry("TYA", AddressingMode.IMPLIED, 1),
            new Entry("STA ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("TXS", AddressingMode.IMPLIED, 1),
            new Entry("TXY", AddressingMode.IMPLIED, 1),
            new Entry("STZ ...", AddressingMode.ABSOLUTE, 3),
            new Entry("STA ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("STZ ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("STA ...", AddressingMode.LONG_X, 4),

            // a0-a7
            new Entry("LDY ...", AddressingMode.IMMEDIATE_X, InstructionLength.X23),
            new Entry("LDA ...", AddressingMode.DIRECT_X_POINTER, 2),
            new Entry("LDX ...", AddressingMode.IMMEDIATE_X, InstructionLength.X23),
            new Entry("LDA ...", AddressingMode.STACK_S, 2),
            new Entry("LDY ...", AddressingMode.DIRECT, 2),
            new Entry("LDA ...", AddressingMode.DIRECT, 2),
            new Entry("LDX ...", AddressingMode.DIRECT, 2),
            new Entry("LDA ...", AddressingMode.DIRECT_LONGPOINTER, 2),

            // a8-af
            new Entry("TAY", AddressingMode.IMPLIED, 1),
            new Entry("LDA ...", AddressingMode.IMMEDIATE_A, InstructionLength.A23),
            new Entry("TAX", AddressingMode.IMPLIED, 1),
            new Entry("PLB", AddressingMode.IMPLIED, 1),
            new Entry("LDY ...", AddressingMode.ABSOLUTE, 3),
            new Entry("LDA ...", AddressingMode.ABSOLUTE, 3),
            new Entry("LDX ...", AddressingMode.ABSOLUTE, 3),
            new Entry("LDA ...", AddressingMode.LONG, 4),

            // b0-b7
            new Entry("BCS ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("LDA ...", AddressingMode.DIRECT_POINTER_Y, 2),
            new Entry("LDA ...", AddressingMode.DIRECT_POINTER, 2),
            new Entry("LDA ...", AddressingMode.STACK_S_POINTER_Y, 2),
            new Entry("LDY ...", AddressingMode.DIRECT_X, 2),
            new Entry("LDA ...", AddressingMode.DIRECT_X, 2),
            new Entry("LDX ...", AddressingMode.DIRECT_Y, 2),
            new Entry("LDA ...", AddressingMode.DIRECT_LONGPOINTER_Y, 2),

            // b8-bf
            new Entry("CLV", AddressingMode.IMPLIED, 1),
            new Entry("LDA ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("TSX", AddressingMode.IMPLIED, 1),
            new Entry("TYX", AddressingMode.IMPLIED, 1),
            new Entry("LDY ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("LDA ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("LDX ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("LDA ...", AddressingMode.LONG_X, 4),

            // c0-c7
            new Entry("CPY ...", AddressingMode.IMMEDIATE_X, InstructionLength.X23),
            new Entry("CMP ...", AddressingMode.DIRECT_X_POINTER, 2),
            new Entry("REP ...", AddressingMode.IMMEDIATE_8, 2).withSpecialEffect(InstructionSpecialEffect.REP),
            new Entry("CMP ...", AddressingMode.STACK_S, 2),
            new Entry("CPY ...", AddressingMode.DIRECT, 2),
            new Entry("CMP ...", AddressingMode.DIRECT, 2),
            new Entry("DEC ...", AddressingMode.DIRECT, 2),
            new Entry("CMP ...", AddressingMode.DIRECT_POINTER, 2),

            // c8-cf
            new Entry("INY ...", AddressingMode.IMPLIED, 1),
            new Entry("CMP ...", AddressingMode.IMMEDIATE_A, InstructionLength.A23),
            new Entry("DEX ...", AddressingMode.IMPLIED, 1),
            new Entry("WAI", AddressingMode.IMPLIED, 1),
            new Entry("CPY ...", AddressingMode.ABSOLUTE, 3),
            new Entry("CMP ...", AddressingMode.ABSOLUTE, 3),
            new Entry("DEC ...", AddressingMode.ABSOLUTE, 3),
            new Entry("CMP ...", AddressingMode.LONG, 4),

            // d0-d7
            new Entry("BNE ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("CMP ...", AddressingMode.DIRECT_POINTER_Y, 2),
            new Entry("CMP ...", AddressingMode.DIRECT_POINTER, 2),
            new Entry("CMP ...", AddressingMode.STACK_S_POINTER_Y, 2),
            new Entry("PEI ...", AddressingMode.DIRECT, 2),
            new Entry("CMP ...", AddressingMode.DIRECT_X, 2),
            new Entry("DEC ...", AddressingMode.DIRECT_X, 2),
            new Entry("CMP ...", AddressingMode.DIRECT_LONGPOINTER_Y, 2),

            // d8-df
            new Entry("CLD", AddressingMode.IMPLIED, 1),
            new Entry("CMP ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("PHX", AddressingMode.IMPLIED, 1),
            new Entry("STP", AddressingMode.IMPLIED, 1),
            new Entry("JMP ...", AddressingMode.ABSOLUTE_LONGPOINTER, 3).diverting(),
            new Entry("CMP ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("DEC ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("CMP ...", AddressingMode.LONG_X, 4),

            // e0-e7
            new Entry("CPX ...", AddressingMode.IMMEDIATE_X, InstructionLength.X23),
            new Entry("SBC ...", AddressingMode.DIRECT_X_POINTER, 2),
            new Entry("SEP ...", AddressingMode.IMMEDIATE_8, 2).withSpecialEffect(InstructionSpecialEffect.SEP),
            new Entry("SBC ...", AddressingMode.STACK_S, 2),
            new Entry("CPX ...", AddressingMode.DIRECT, 2),
            new Entry("SBC ...", AddressingMode.DIRECT, 2),
            new Entry("INC ...", AddressingMode.DIRECT, 2),
            new Entry("SBC ...", AddressingMode.DIRECT_POINTER, 2),

            // e8-ef
            new Entry("INX ...", AddressingMode.IMPLIED, 1),
            new Entry("SBC ...", AddressingMode.IMMEDIATE_A, InstructionLength.A23),
            new Entry("NOP", AddressingMode.IMPLIED, 1),
            new Entry("XBA", AddressingMode.IMPLIED, 1),
            new Entry("CPX ...", AddressingMode.ABSOLUTE, 3),
            new Entry("SBC ...", AddressingMode.ABSOLUTE, 3),
            new Entry("INC ...", AddressingMode.ABSOLUTE, 3),
            new Entry("SBC ...", AddressingMode.LONG, 4),

            // f0-f7
            new Entry("BEQ ...", AddressingMode.RELATIVE_8, 2).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("SBC ...", AddressingMode.DIRECT_POINTER_Y, 2),
            new Entry("SBC ...", AddressingMode.DIRECT_POINTER, 2),
            new Entry("SBC ...", AddressingMode.STACK_S_POINTER_Y, 2),
            new Entry("PEA ...", AddressingMode.IMMEDIATE_16, 3),
            new Entry("SBC ...", AddressingMode.DIRECT_X, 2),
            new Entry("INC ...", AddressingMode.DIRECT_X, 2),
            new Entry("SBC ...", AddressingMode.DIRECT_LONGPOINTER_Y, 2),

            // f8-ff
            new Entry("SED", AddressingMode.IMPLIED, 1),
            new Entry("SBC ...", AddressingMode.ABSOLUTE_Y, 3),
            new Entry("PLX", AddressingMode.IMPLIED, 1),
            new Entry("XCE", AddressingMode.IMPLIED, 1).withSpecialEffect(InstructionSpecialEffect.XCE),
            new Entry("JSR ...", AddressingMode.ABSOLUTE_X_POINTER, 3),
            new Entry("SBC ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("INC ...", AddressingMode.ABSOLUTE_X, 3),
            new Entry("SBC ...", AddressingMode.LONG_X, 4),

    };

    public static class Entry {

        private final String mnemonic;
        private final InstructionLength length;
        private final AddressingMode addressingMode;
        private boolean divert;
        private StaticJumpAddressingMode staticJumpAddressingMode;
        private InstructionSpecialEffect specialEffect = InstructionSpecialEffect.NONE;

        private Entry(String mnemonic, InstructionLength length, AddressingMode addressingMode) {
            this.mnemonic = mnemonic;
            this.length = length;
            this.addressingMode = addressingMode;
        }

        private Entry(String mnemonic, AddressingMode addressingMode) {
            this(mnemonic, addressingMode.getDefaultLength(), addressingMode);
        }

        private Entry(String mnemonic, int length, AddressingMode addressingMode) {
            this(mnemonic, InstructionLength.getFixed(length), addressingMode);
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

        public AddressingMode getAddressingMode() {
            return addressingMode;
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

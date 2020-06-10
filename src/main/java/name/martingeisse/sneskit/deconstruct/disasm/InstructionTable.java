package name.martingeisse.sneskit.deconstruct.disasm;

public final class InstructionTable {

    public static final Entry[] TABLE = new Entry[]{

            // 00-07
            new Entry("BRK ...", AddressingMode.IMMEDIATE_8), // defined as 1-byte but effectively 2-byte
            new Entry("ORA ...", AddressingMode.DIRECT_X_POINTER),
            new Entry("COP ...", AddressingMode.IMMEDIATE_8),
            new Entry("ORA ...", AddressingMode.STACK_S),
            new Entry("TSB ...", AddressingMode.DIRECT),
            new Entry("ORA ...", AddressingMode.DIRECT),
            new Entry("ASL ...", AddressingMode.DIRECT),
            new Entry("ORA ...", AddressingMode.DIRECT_LONGPOINTER),

            // 08-0f
            new Entry("PHP", AddressingMode.IMPLIED),
            new Entry("ORA ...", AddressingMode.IMMEDIATE_A),
            new Entry("ASL ...", AddressingMode.IMPLIED),
            new Entry("PHD", AddressingMode.IMPLIED),
            new Entry("TSB ...", AddressingMode.ABSOLUTE),
            new Entry("ORA ...", AddressingMode.ABSOLUTE),
            new Entry("ASL ...", AddressingMode.ABSOLUTE),
            new Entry("ORA ...", AddressingMode.LONG),

            // 10-17
            new Entry("BPL ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("ORA ...", AddressingMode.DIRECT_POINTER_Y),
            new Entry("ORA ...", AddressingMode.DIRECT_POINTER),
            new Entry("ORA ...", AddressingMode.STACK_S_POINTER_Y),
            new Entry("TRB ...", AddressingMode.DIRECT),
            new Entry("ORA ...", AddressingMode.DIRECT_X),
            new Entry("ASL ...", AddressingMode.DIRECT_X),
            new Entry("ORA ...", AddressingMode.DIRECT_LONGPOINTER_Y),

            // 18-1f
            new Entry("CLC", AddressingMode.IMPLIED),
            new Entry("ORA ...", AddressingMode.ABSOLUTE_Y),
            new Entry("INC ...", AddressingMode.IMPLIED),
            new Entry("TCS", AddressingMode.IMPLIED),
            new Entry("TRB", AddressingMode.ABSOLUTE),
            new Entry("ORA ...", AddressingMode.ABSOLUTE_X),
            new Entry("ASL ...", AddressingMode.ABSOLUTE_X),
            new Entry("ORA ...", AddressingMode.LONG_X),

            // 20-27
            new Entry("JSR ...", AddressingMode.ABSOLUTE).jumping(StaticJumpAddressingMode.K_BANK_ABSOLUTE),
            new Entry("AND ...", AddressingMode.DIRECT_X_POINTER),
            new Entry("JSL ...", AddressingMode.LONG).jumping(StaticJumpAddressingMode.LONG),
            new Entry("AND ...", AddressingMode.STACK_S),
            new Entry("BIT ...", AddressingMode.DIRECT),
            new Entry("AND ...", AddressingMode.DIRECT),
            new Entry("ROL ...", AddressingMode.DIRECT),
            new Entry("AND ...", AddressingMode.DIRECT_LONGPOINTER),

            // 28-2f
            new Entry("PLP", AddressingMode.IMPLIED).withSpecialEffect(InstructionSpecialEffect.PLP),
            new Entry("AND ...", AddressingMode.IMMEDIATE_A),
            new Entry("ROL ...", AddressingMode.IMPLIED),
            new Entry("PLD", AddressingMode.IMPLIED),
            new Entry("BIT ...", AddressingMode.ABSOLUTE),
            new Entry("AND ...", AddressingMode.ABSOLUTE),
            new Entry("ROL ...", AddressingMode.ABSOLUTE),
            new Entry("AND ...", AddressingMode.LONG),

            // 30-37
            new Entry("BMI ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("AND ...", AddressingMode.DIRECT_POINTER_Y),
            new Entry("AND ...", AddressingMode.DIRECT_POINTER),
            new Entry("AND ...", AddressingMode.STACK_S_POINTER_Y),
            new Entry("BIT ...", AddressingMode.DIRECT_X),
            new Entry("AND ...", AddressingMode.DIRECT_X),
            new Entry("ROL ...", AddressingMode.DIRECT_X),
            new Entry("AND ...", AddressingMode.DIRECT_LONGPOINTER_Y),

            // 38-3f
            new Entry("SEC", AddressingMode.IMPLIED),
            new Entry("AND ...", AddressingMode.ABSOLUTE_Y),
            new Entry("DEC ...", AddressingMode.IMPLIED),
            new Entry("TSC", AddressingMode.IMPLIED),
            new Entry("BIT ...", AddressingMode.ABSOLUTE_X),
            new Entry("AND ...", AddressingMode.ABSOLUTE_X),
            new Entry("ROL ...", AddressingMode.ABSOLUTE_X),
            new Entry("AND ...", AddressingMode.LONG_X),

            // 40-47
            new Entry("RTI", AddressingMode.IMPLIED).diverting(),
            new Entry("EOR ...", AddressingMode.DIRECT_X_POINTER),
            new Entry("WDM ...", AddressingMode.IMMEDIATE_8),
            new Entry("EOR ...", AddressingMode.STACK_S),
            new Entry("MVP ...", AddressingMode.SOURCE_DESTINATION),
            new Entry("EOR ...", AddressingMode.DIRECT),
            new Entry("LSR ...", AddressingMode.DIRECT),
            new Entry("EOR ...", AddressingMode.DIRECT_LONGPOINTER),

            // 48-4f
            new Entry("PHA", AddressingMode.IMPLIED),
            new Entry("EOR ...", AddressingMode.IMMEDIATE_A),
            new Entry("LSR ...", AddressingMode.IMPLIED),
            new Entry("PHK", AddressingMode.IMPLIED),
            new Entry("JMP ...", AddressingMode.ABSOLUTE).jumping(StaticJumpAddressingMode.K_BANK_ABSOLUTE).diverting(),
            new Entry("EOR ...", AddressingMode.ABSOLUTE),
            new Entry("LSR ...", AddressingMode.ABSOLUTE),
            new Entry("EOR ...", AddressingMode.LONG),

            // 50-57
            new Entry("BVC ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("EOR ...", AddressingMode.DIRECT_POINTER_Y),
            new Entry("EOR ...", AddressingMode.DIRECT_POINTER),
            new Entry("EOR ...", AddressingMode.STACK_S_POINTER_Y),
            new Entry("MVN ...", AddressingMode.SOURCE_DESTINATION),
            new Entry("EOR ...", AddressingMode.DIRECT_X),
            new Entry("LSR ...", AddressingMode.DIRECT_X),
            new Entry("EOR ...", AddressingMode.DIRECT_LONGPOINTER_Y),

            // 58-5f
            new Entry("CLI", AddressingMode.IMPLIED),
            new Entry("EOR ...", AddressingMode.ABSOLUTE_Y),
            new Entry("PHY", AddressingMode.IMPLIED),
            new Entry("TCD", AddressingMode.IMPLIED),
            new Entry("JML ...", AddressingMode.LONG).jumping(StaticJumpAddressingMode.LONG).diverting(),
            new Entry("EOR ...", AddressingMode.ABSOLUTE_X),
            new Entry("LSR ...", AddressingMode.ABSOLUTE_X),
            new Entry("EOR ...", AddressingMode.LONG_X),

            // 60-67
            new Entry("RTS", AddressingMode.IMPLIED),
            new Entry("ADC ...", AddressingMode.DIRECT_X_POINTER),
            new Entry("PER ...", AddressingMode.IMMEDIATE_16),
            new Entry("ADC ...", AddressingMode.STACK_S),
            new Entry("STZ ...", AddressingMode.DIRECT),
            new Entry("ADC ...", AddressingMode.DIRECT),
            new Entry("ROR ...", AddressingMode.DIRECT),
            new Entry("ADC ...", AddressingMode.DIRECT_LONGPOINTER),

            // 68-6f
            new Entry("PLA", AddressingMode.IMPLIED),
            new Entry("ADC ...", AddressingMode.IMMEDIATE_A),
            new Entry("ROR ...", AddressingMode.IMPLIED),
            new Entry("RTL", AddressingMode.IMPLIED),
            new Entry("JMP ...", AddressingMode.ABSOLUTE_POINTER).diverting(),
            new Entry("ADC ...", AddressingMode.ABSOLUTE),
            new Entry("ROR ...", AddressingMode.ABSOLUTE),
            new Entry("ADC ...", AddressingMode.LONG),

            // 70-77
            new Entry("BVS ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("ADC ...", AddressingMode.DIRECT_POINTER_Y),
            new Entry("ADC ...", AddressingMode.DIRECT_POINTER),
            new Entry("ADC ...", AddressingMode.STACK_S_POINTER_Y),
            new Entry("STZ ...", AddressingMode.DIRECT_X),
            new Entry("ADC ...", AddressingMode.DIRECT_X),
            new Entry("ROR ...", AddressingMode.DIRECT_X),
            new Entry("ADC ...", AddressingMode.DIRECT_LONGPOINTER_Y),

            // 78-7f
            new Entry("SEI", AddressingMode.IMPLIED),
            new Entry("ADC ...", AddressingMode.ABSOLUTE_Y),
            new Entry("PLY", AddressingMode.IMPLIED),
            new Entry("TDC", AddressingMode.IMPLIED),
            new Entry("JMP ...", AddressingMode.ABSOLUTE_X_POINTER).diverting(),
            new Entry("ADC ...", AddressingMode.ABSOLUTE_X),
            new Entry("ROR ...", AddressingMode.ABSOLUTE_X),
            new Entry("ADC ...", AddressingMode.LONG_X),

            // 80-87
            new Entry("BRA ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8).diverting(),
            new Entry("STA ...", AddressingMode.DIRECT_X_POINTER),
            new Entry("BRL ...", AddressingMode.RELATIVE_16).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_16).diverting(),
            new Entry("STA ...", AddressingMode.STACK_S),
            new Entry("STY ...", AddressingMode.DIRECT),
            new Entry("STA ...", AddressingMode.DIRECT),
            new Entry("STX ...", AddressingMode.DIRECT),
            new Entry("STA ...", AddressingMode.DIRECT_LONGPOINTER),

            // 88-8f
            new Entry("DEY ...", AddressingMode.IMPLIED),
            new Entry("BIT ...", AddressingMode.IMMEDIATE_A),
            new Entry("TXA", AddressingMode.IMPLIED),
            new Entry("PHB", AddressingMode.IMPLIED),
            new Entry("STY ...", AddressingMode.ABSOLUTE),
            new Entry("STA ...", AddressingMode.ABSOLUTE),
            new Entry("STX ...", AddressingMode.ABSOLUTE),
            new Entry("STA ...", AddressingMode.LONG),

            // 90-97
            new Entry("BCC ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("STA ...", AddressingMode.DIRECT_POINTER_Y),
            new Entry("STA ...", AddressingMode.DIRECT_POINTER),
            new Entry("STA ...", AddressingMode.STACK_S_POINTER_Y),
            new Entry("STY ...", AddressingMode.DIRECT_X),
            new Entry("STA ...", AddressingMode.DIRECT_X),
            new Entry("STX ...", AddressingMode.DIRECT_Y),
            new Entry("STA ...", AddressingMode.DIRECT_LONGPOINTER_Y),

            // 98-9f
            new Entry("TYA", AddressingMode.IMPLIED),
            new Entry("STA ...", AddressingMode.ABSOLUTE_Y),
            new Entry("TXS", AddressingMode.IMPLIED),
            new Entry("TXY", AddressingMode.IMPLIED),
            new Entry("STZ ...", AddressingMode.ABSOLUTE),
            new Entry("STA ...", AddressingMode.ABSOLUTE_X),
            new Entry("STZ ...", AddressingMode.ABSOLUTE_X),
            new Entry("STA ...", AddressingMode.LONG_X),

            // a0-a7
            new Entry("LDY ...", AddressingMode.IMMEDIATE_X),
            new Entry("LDA ...", AddressingMode.DIRECT_X_POINTER),
            new Entry("LDX ...", AddressingMode.IMMEDIATE_X),
            new Entry("LDA ...", AddressingMode.STACK_S),
            new Entry("LDY ...", AddressingMode.DIRECT),
            new Entry("LDA ...", AddressingMode.DIRECT),
            new Entry("LDX ...", AddressingMode.DIRECT),
            new Entry("LDA ...", AddressingMode.DIRECT_LONGPOINTER),

            // a8-af
            new Entry("TAY", AddressingMode.IMPLIED),
            new Entry("LDA ...", AddressingMode.IMMEDIATE_A),
            new Entry("TAX", AddressingMode.IMPLIED),
            new Entry("PLB", AddressingMode.IMPLIED),
            new Entry("LDY ...", AddressingMode.ABSOLUTE),
            new Entry("LDA ...", AddressingMode.ABSOLUTE),
            new Entry("LDX ...", AddressingMode.ABSOLUTE),
            new Entry("LDA ...", AddressingMode.LONG),

            // b0-b7
            new Entry("BCS ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("LDA ...", AddressingMode.DIRECT_POINTER_Y),
            new Entry("LDA ...", AddressingMode.DIRECT_POINTER),
            new Entry("LDA ...", AddressingMode.STACK_S_POINTER_Y),
            new Entry("LDY ...", AddressingMode.DIRECT_X),
            new Entry("LDA ...", AddressingMode.DIRECT_X),
            new Entry("LDX ...", AddressingMode.DIRECT_Y),
            new Entry("LDA ...", AddressingMode.DIRECT_LONGPOINTER_Y),

            // b8-bf
            new Entry("CLV", AddressingMode.IMPLIED),
            new Entry("LDA ...", AddressingMode.ABSOLUTE_Y),
            new Entry("TSX", AddressingMode.IMPLIED),
            new Entry("TYX", AddressingMode.IMPLIED),
            new Entry("LDY ...", AddressingMode.ABSOLUTE_X),
            new Entry("LDA ...", AddressingMode.ABSOLUTE_X),
            new Entry("LDX ...", AddressingMode.ABSOLUTE_Y),
            new Entry("LDA ...", AddressingMode.LONG_X),

            // c0-c7
            new Entry("CPY ...", AddressingMode.IMMEDIATE_X),
            new Entry("CMP ...", AddressingMode.DIRECT_X_POINTER),
            new Entry("REP ...", AddressingMode.IMMEDIATE_8).withSpecialEffect(InstructionSpecialEffect.REP),
            new Entry("CMP ...", AddressingMode.STACK_S),
            new Entry("CPY ...", AddressingMode.DIRECT),
            new Entry("CMP ...", AddressingMode.DIRECT),
            new Entry("DEC ...", AddressingMode.DIRECT),
            new Entry("CMP ...", AddressingMode.DIRECT_LONGPOINTER),

            // c8-cf
            new Entry("INY ...", AddressingMode.IMPLIED),
            new Entry("CMP ...", AddressingMode.IMMEDIATE_A),
            new Entry("DEX ...", AddressingMode.IMPLIED),
            new Entry("WAI", AddressingMode.IMPLIED),
            new Entry("CPY ...", AddressingMode.ABSOLUTE),
            new Entry("CMP ...", AddressingMode.ABSOLUTE),
            new Entry("DEC ...", AddressingMode.ABSOLUTE),
            new Entry("CMP ...", AddressingMode.LONG),

            // d0-d7
            new Entry("BNE ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("CMP ...", AddressingMode.DIRECT_POINTER_Y),
            new Entry("CMP ...", AddressingMode.DIRECT_POINTER),
            new Entry("CMP ...", AddressingMode.STACK_S_POINTER_Y),
            new Entry("PEI ...", AddressingMode.DIRECT),
            new Entry("CMP ...", AddressingMode.DIRECT_X),
            new Entry("DEC ...", AddressingMode.DIRECT_X),
            new Entry("CMP ...", AddressingMode.DIRECT_LONGPOINTER_Y),

            // d8-df
            new Entry("CLD", AddressingMode.IMPLIED),
            new Entry("CMP ...", AddressingMode.ABSOLUTE_Y),
            new Entry("PHX", AddressingMode.IMPLIED),
            new Entry("STP", AddressingMode.IMPLIED),
            new Entry("JMP ...", AddressingMode.ABSOLUTE_LONGPOINTER).diverting(),
            new Entry("CMP ...", AddressingMode.ABSOLUTE_X),
            new Entry("DEC ...", AddressingMode.ABSOLUTE_X),
            new Entry("CMP ...", AddressingMode.LONG_X),

            // e0-e7
            new Entry("CPX ...", AddressingMode.IMMEDIATE_X),
            new Entry("SBC ...", AddressingMode.DIRECT_X_POINTER),
            new Entry("SEP ...", AddressingMode.IMMEDIATE_8).withSpecialEffect(InstructionSpecialEffect.SEP),
            new Entry("SBC ...", AddressingMode.STACK_S),
            new Entry("CPX ...", AddressingMode.DIRECT),
            new Entry("SBC ...", AddressingMode.DIRECT),
            new Entry("INC ...", AddressingMode.DIRECT),
            new Entry("SBC ...", AddressingMode.DIRECT_LONGPOINTER),

            // e8-ef
            new Entry("INX ...", AddressingMode.IMPLIED),
            new Entry("SBC ...", AddressingMode.IMMEDIATE_A),
            new Entry("NOP", AddressingMode.IMPLIED),
            new Entry("XBA", AddressingMode.IMPLIED),
            new Entry("CPX ...", AddressingMode.ABSOLUTE),
            new Entry("SBC ...", AddressingMode.ABSOLUTE),
            new Entry("INC ...", AddressingMode.ABSOLUTE),
            new Entry("SBC ...", AddressingMode.LONG),

            // f0-f7
            new Entry("BEQ ...", AddressingMode.RELATIVE_8).jumping(StaticJumpAddressingMode.K_BANK_RELATIVE_8),
            new Entry("SBC ...", AddressingMode.DIRECT_POINTER_Y),
            new Entry("SBC ...", AddressingMode.DIRECT_POINTER),
            new Entry("SBC ...", AddressingMode.STACK_S_POINTER_Y),
            new Entry("PEA ...", AddressingMode.IMMEDIATE_16),
            new Entry("SBC ...", AddressingMode.DIRECT_X),
            new Entry("INC ...", AddressingMode.DIRECT_X),
            new Entry("SBC ...", AddressingMode.DIRECT_LONGPOINTER_Y),

            // f8-ff
            new Entry("SED", AddressingMode.IMPLIED),
            new Entry("SBC ...", AddressingMode.ABSOLUTE_Y),
            new Entry("PLX", AddressingMode.IMPLIED),
            new Entry("XCE", AddressingMode.IMPLIED).withSpecialEffect(InstructionSpecialEffect.XCE),
            new Entry("JSR ...", AddressingMode.ABSOLUTE_X_POINTER),
            new Entry("SBC ...", AddressingMode.ABSOLUTE_X),
            new Entry("INC ...", AddressingMode.ABSOLUTE_X),
            new Entry("SBC ...", AddressingMode.LONG_X),

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

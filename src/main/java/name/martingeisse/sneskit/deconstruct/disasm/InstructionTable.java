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
            null,
            null,
            null,
            null,
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
            null,
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
            null,
            null,
            null,
            null,
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
            null,
            null,
            null,
            null,
            null,

            // 40-47
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 48-4f
            null,
            null,
            null,
            null,
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
            null,
            null,
            new Entry("JMP (long)", 4).diverting(),
            null,
            null,
            null,

            // 60-67
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,

            // 68-6f
            null,
            null,
            null,
            null,
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
            null,
            null,
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
            null,
            null,
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
            null,
            null,
            null,
            null,
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
            null,
            null,
            null,
            null,
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
            null,
            null,
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
            null,
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
            null,
            null,
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
            null,
            null,
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
            null,
            null,
            null,
            null,
            null,
            null,


    };

    public static class Entry {

        private final String mnemonic;
        private final InstructionLength length;
        private final boolean divert;

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
            return new Entry(mnemonic, length, true);
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

    }

}

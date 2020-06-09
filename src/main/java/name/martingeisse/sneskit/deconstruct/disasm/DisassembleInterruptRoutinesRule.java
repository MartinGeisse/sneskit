package name.martingeisse.sneskit.deconstruct.disasm;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.Rule;
import name.martingeisse.sneskit.deconstruct.RuleContext;
import name.martingeisse.sneskit.util.AddressUtil;

import java.io.IOException;

public class DisassembleInterruptRoutinesRule implements Rule {

    @Override
    public void run(JsonObject ruleConfiguration, RuleContext context) throws IOException {

        // native-mode interrupts
        int vectorTablePhysicalBaseAddress = AddressUtil.virtualToPhysical(0x00_ffe0);
        handleEntry(vectorTablePhysicalBaseAddress + 4, context, false); // COP
        handleEntry(vectorTablePhysicalBaseAddress + 6, context, false); // BRK
        handleEntry(vectorTablePhysicalBaseAddress + 8, context, false); // ABORT
        handleEntry(vectorTablePhysicalBaseAddress + 10, context, false); // NMI
        handleEntry(vectorTablePhysicalBaseAddress + 14, context, false); // IRQ

        // emulation-mode interrupts
        vectorTablePhysicalBaseAddress = AddressUtil.virtualToPhysical(0x00_fff0);
        handleEntry(vectorTablePhysicalBaseAddress + 4, context, true); // COP
        handleEntry(vectorTablePhysicalBaseAddress + 8, context, true); // ABORT
        handleEntry(vectorTablePhysicalBaseAddress + 10, context, true); // NMI
        handleEntry(vectorTablePhysicalBaseAddress + 12, context, true); // RESET
        handleEntry(vectorTablePhysicalBaseAddress + 14, context, true); // IRQ/BRK

    }

    private void handleEntry(int physicalVectorAddress, RuleContext context, boolean emulation) {
        int virtualRoutineAddress = (context.getRom()[physicalVectorAddress] & 0xff) +
                ((context.getRom()[physicalVectorAddress + 1] & 0xff) << 8);
        InstructionFormat format = emulation ? InstructionFormat.EMULATION : InstructionFormat.UNKNOWN;
        context.getDisassembler().disassemble(virtualRoutineAddress, format);
    }

}

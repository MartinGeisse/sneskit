package name.martingeisse.sneskit.deconstruct.disasm;

import com.google.gson.JsonObject;
import name.martingeisse.sneskit.deconstruct.Rule;
import name.martingeisse.sneskit.deconstruct.RuleContext;
import name.martingeisse.sneskit.util.AddressUtil;

import java.io.IOException;

public class DisassembleInterruptRoutinesRule implements Rule {

    @Override
    public void run(JsonObject ruleConfiguration, RuleContext context) throws IOException {
        int vectorTablePhysicalBaseAddress = AddressUtil.virtualToPhysical(0x00_ffe0);
        handleEntry(vectorTablePhysicalBaseAddress + 4, context); // COP
        handleEntry(vectorTablePhysicalBaseAddress + 6, context); // BRK
        handleEntry(vectorTablePhysicalBaseAddress + 8, context); // ABORT
        handleEntry(vectorTablePhysicalBaseAddress + 10, context); // NMI
        handleEntry(vectorTablePhysicalBaseAddress + 14, context); // IRQ
    }

    private void handleEntry(int physicalVectorAddress, RuleContext context) {
        int virtualRoutineAddress = (context.getRom()[physicalVectorAddress] & 0xff) +
                ((context.getRom()[physicalVectorAddress + 1] & 0xff) << 8);
        context.getDisassembler().disassemble(virtualRoutineAddress);
    }

}

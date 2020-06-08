package name.martingeisse.sneskit.deconstruct.sm;

import name.martingeisse.sneskit.deconstruct.Rule;
import name.martingeisse.sneskit.deconstruct.RuleSet;
import name.martingeisse.sneskit.deconstruct.disasm.DisassembleInterruptRoutinesRule;
import name.martingeisse.sneskit.deconstruct.rules.ExtractAsAsmRule;
import name.martingeisse.sneskit.deconstruct.rules.SimilarRule;
import name.martingeisse.sneskit.util.KitException;

import java.util.HashMap;
import java.util.Map;

public class SmRuleSet implements RuleSet {

    private final Map<String, Class<? extends Rule>> rules = new HashMap<>();

    public SmRuleSet() {

        // generic rules
        rules.put("similar", SimilarRule.class);
        rules.put("data", ExtractAsAsmRule.class);
        rules.put("disassemble-interrupt-routines", DisassembleInterruptRoutinesRule.class);

        // SM rules
        rules.put("tileset", TilesetRule.class);

    }

    @Override
    public Rule createRule(String type) {
        Class<? extends Rule> ruleClass = rules.get(type);
        if (ruleClass == null) {
            throw new KitException("unknown rule type: " + type);
        }
        try {
            return ruleClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new KitException("could not create rule of type " + type, e);
        }
    }
}

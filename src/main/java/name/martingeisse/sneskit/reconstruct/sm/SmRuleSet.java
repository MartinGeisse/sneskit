package name.martingeisse.sneskit.reconstruct.sm;

import name.martingeisse.sneskit.reconstruct.Rule;
import name.martingeisse.sneskit.reconstruct.RuleSet;
import name.martingeisse.sneskit.util.KitException;

import java.util.HashMap;
import java.util.Map;

public class SmRuleSet implements RuleSet {

    private final Map<String, Class<? extends Rule>> rules = new HashMap<>();

    public SmRuleSet() {
        rules.put("test", SmTestRule.class);
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

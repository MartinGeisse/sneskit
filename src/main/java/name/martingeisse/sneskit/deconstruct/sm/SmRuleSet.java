package name.martingeisse.sneskit.deconstruct.sm;

import name.martingeisse.sneskit.deconstruct.DeconstructException;
import name.martingeisse.sneskit.deconstruct.Rule;
import name.martingeisse.sneskit.deconstruct.RuleSet;

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
            throw new DeconstructException("unknown rule type: " + type);
        }
        try {
            return ruleClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new DeconstructException("could not create rule of type " + type, e);
        }
    }
}

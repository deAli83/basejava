package ru.javawebinar.basejava.model;

import java.util.HashMap;
import java.util.Map;

public class SkillSection extends AbstractSection {
    private Map<String, Skill> skills;

    public SkillSection(HashMap<String, Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return skills.toString();
    }
}

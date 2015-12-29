package duel.hero;

import duel.Hero;
import duel.skill.warriorSkill.War2;

public class Mage extends Hero
{
    public Mage()
    {
        super();
    }

    @Override
    public void initSkill(Hero p1)
    {
        this.skillList.add(new War2(p1, this));
    }

    @Override
    public void initUlt(Hero p1)
    {
    }
}

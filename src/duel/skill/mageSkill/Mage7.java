package duel.skill.mageSkill;

import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;
import duel.buff.Hbht;

public class Mage7 extends Skill
{
    String buffType = "hbht";

    public Mage7(Hero caster, Hero target)
    {
        this.mark = "7";
        this.name = "Ί®±ω»€Με";
        this.caster = caster;
        this.target = target;
    }

    @Override
    public int perform()
    {
        int ran = RandomIntList.getInstance().getNext() / 5;
        Main.damage = (int) 50 * caster.gj / target.fy + ran;
        U.deleteBuffByType(caster, buffType);
        caster.buffList.add(new Hbht(caster, target));
        cishu++;
        return 0;
    }

}

package duel.skill.mageSkill;

import duel.Const;
import duel.Hero;
import duel.Skill;
import duel.U;
import duel.buff.Sfzz;

public class Mage6 extends Skill
{
    String buffType = "sfzz";

    public Mage6(Hero caster, Hero target)
    {
        this.mark = "6";
        this.name = "施法专注";
        this.caster = caster;
        this.target = target;
        this.skillType = 0;
    }

    @Override
    public int perform()
    {
        U.deleteBuffByType(caster, buffType);
        caster.buffList.add(new Sfzz(caster, target));

        caster.ultList.get(0).ultNum = caster.ultList.get(0).ultNum + 8;
        caster.ultList.get(1).ultNum = caster.ultList.get(1).ultNum + 6;
        caster.ql = caster.ql + 40;

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,将法术力凝聚起来");
        return 0;
    }
}

package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Skill;
import duel.U;
import duel.buff.Dppz;

public class WarE extends Skill
{
    String buffType = "dppz";

    public WarE(Hero caster, Hero target)
    {
        this.mark = "E";
        this.name = "盾牌屏障";
        this.caster = caster;
        this.target = target;
        this.skillType = 2;
    }

    @Override
    public int perform()
    {
        U.deleteBuffByType(caster, buffType);
        caster.buffList.add(new Dppz(caster, target));

        if (caster.ql <= 60)
            caster.ql = 120;
        else
            caster.ql = caster.ql + 60;

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,坚不可摧的防御！");
        return 0;
    }

}

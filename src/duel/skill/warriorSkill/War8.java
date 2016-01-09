package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Skill;
import duel.U;
import duel.buff.Dpfs;

public class War8 extends Skill
{
    String buffType = "dpfs";

    public War8(Hero caster, Hero target)
    {
        this.mark = "8";
        this.name = "盾牌反射";
        this.caster = caster;
        this.target = target;
        this.skillType = 2;
    }

    @Override
    public int perform()
    {
        U.deleteBuffByType(caster, buffType);
        caster.buffList.add(new Dpfs(caster, target));

        caster.ultList.get(2).ultNum = caster.ultList.get(2).ultNum + 12;

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,将盾牌举起朝向对方");
        return 0;
    }

}
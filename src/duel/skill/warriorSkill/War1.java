package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;

public class War1 extends Skill
{
    private double xishu = 1.2;

    public War1(Hero caster, Hero target)
    {
        this.mark = "1";
        this.name = "英勇打击";
        this.caster = caster;
        this.target = target;
    }

    @Override
    public int perform()
    {
        double d = U.critical(caster);
        U.showCrit(caster, d);
        int ran = RandomIntList.getInstance().getNext() / 1000;
        double comDam = (95 + ran) * (caster.gj + 15) / (target.fy + 15) * d;
        Main.damage = xishu * comDam;
        int exDam = (int) (Main.damage - comDam + 0.5);

        caster.ultList.get(0).ultNum = caster.ultList.get(0).ultNum + comDam
                / 10;
        caster.ultList.get(1).ultNum = caster.ultList.get(1).ultNum + exDam
                * 0.4;
        U.incTarget(target, comDam + 50);
        U.incCaster(caster, Main.damage + exDam);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!(技能伤害" + exDam + "点)");
        return 0;
    }

}

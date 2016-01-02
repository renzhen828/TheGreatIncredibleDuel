package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;

public class War5 extends Skill
{
    private double xishu = 0.7;

    public War5(Hero caster, Hero target)
    {
        this.mark = "5";
        this.name = "浴血奋战";
        this.caster = caster;
        this.target = target;
    }

    @Override
    public int perform()
    {
        double d = U.critical(caster);
        U.showCrit(caster, d);
        int ran = RandomIntList.getInstance().getNext() / 1000;
        double comDam = xishu * (95 + ran) * (caster.gj + 15)
                / (target.fy + 15) * d;
        double exDam0 = 0;
        if (caster.xl < 400)
            exDam0 = exDam0 + (400 - caster.xl) / 10;
        for (int i = 400; i >= 100; i = i - 100)
            if (caster.xl < i)
                exDam0 = exDam0 + (i - caster.xl) / 10;
        if (target.xl < 600)
            exDam0 = exDam0 + (600 - target.xl) / 20;
        Main.damage = comDam + exDam0;
        int exDam = (int) (exDam0 + 0.5);

        caster.ultList.get(0).ultNum = caster.ultList.get(0).ultNum + comDam
                / 10;
        caster.ultList.get(1).ultNum = caster.ultList.get(1).ultNum + exDam
                * 0.4;
        U.incTarget(target, comDam);
        U.incCaster(caster, Main.damage + exDam);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!(技能伤害" + exDam + "点)");
        return 0;
    }
}

package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;

public class War4 extends Skill
{
    private double xishu = 1;

    public War4(Hero caster, Hero target)
    {
        this.mark = "4";
        this.name = "乘胜追击";
        this.caster = caster;
        this.target = target;
        this.skillType = 1;
    }

    @Override
    public int perform()
    {
        double d = U.critical(caster);
        U.showCrit(caster, d);
        int ran = RandomIntList.getInstance().getNext() / 1000;
        double comDam = xishu * (95 + ran) * (caster.gj + 15)
                / (target.fy + 15) * d;
        if (Main.roundNum <= 1)
            Main.damage = comDam;
        else
            Main.damage = (Main.roundList.get(Main.roundNum - 2).damage * 0.5 + 50)
                    * d;
        int exDam = (int) (Main.damage - comDam + 0.5);

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

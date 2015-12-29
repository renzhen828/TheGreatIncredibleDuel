package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;

public class War3 extends Skill
{
    private double xishu = 0.9;

    public War3(Hero caster, Hero target)
    {
        this.mark = "3";
        this.name = "毁灭打击";
        this.caster = caster;
        this.target = target;
    }

    @Override
    public int perform()
    {
        double d = U.critical(caster);
        U.showCrit(caster, d);
        int ran = RandomIntList.getInstance().getNext() / 1000;
        int extraSH = 0;
        double damage = xishu * (95 + ran) * (caster.gj + 15)
                / (target.fy + 15) * d;
        if (caster.gj > target.fy)
        {
            Main.damage = xishu * (95 + ran) * (caster.gj + 15)
                    / (target.fy + 15) * d * 2.5 - xishu * (95 + ran) * d * 1.5;
            extraSH = (int) (Main.damage - damage);
        } else
            Main.damage = damage;
        caster.ultNum[1] = caster.ultNum[1] / 10;
        caster.ultNum[2] = caster.ultNum[2] + extraSH * 0.4;
        U.incCaster(caster, Main.damage);
        caster.ql = caster.ql + (int) (extraSH / 10 + 0.5);
        U.incTarget(target, Main.damage - extraSH);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!(技能" + extraSH + "点)");
        return 0;
    }
}

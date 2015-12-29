package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;

public class War4 extends Skill
{
    private double xishu = 1.2;

    public War4(Hero caster, Hero target)
    {
        this.mark = "4";
        this.name = "用力打击";
        this.caster = caster;
        this.target = target;
    }

    @Override
    public int perform()
    {
        double d = U.critical(caster);
        U.showCrit(caster, d);
        int ran = RandomIntList.getInstance().getNext() / 1000;
        Main.damage = (95 + ran) * (caster.gj + 15) / (target.fy + 15) * d;
        caster.ultNum[0] = caster.ultNum[0] + Main.damage / 10;
        U.incTarget(target, Main.damage);
        target.ql=target.ql+8;
        double finalSH = xishu * Main.damage;
        int extraSH = (int) (finalSH - Main.damage + 0.5);
        caster.ultNum[1] = caster.ultNum[1] + extraSH * 0.4;
        Main.damage = finalSH;
        U.incCaster(caster, Main.damage);
        caster.ql = caster.ql + (int) (extraSH / 10 + 0.5);
        
        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!(技能" + extraSH + "点)");
        return 0;
    }

}

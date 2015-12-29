package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;

public class WarQ extends Skill
{
    private double xishu = 1.5;

    public WarQ(Hero caster, Hero target)
    {
        this.mark = "Q";
        this.name = "剑刃风暴";
        this.caster = caster;
        this.target = target;
    }

    @Override
    public int perform()
    {
        double d = U.critical(caster);
        if (d < 1)
            d = 1;
        U.showCrit(caster, d);
        int ran = RandomIntList.getInstance().getNext() / 1000;
        double extraSH = 0;
        if (caster.gj > target.fy)
            extraSH = xishu * (95 + ran) * (caster.gj + 15) / (target.fy + 15)
                    * d * 2 - xishu * (95 + ran) * d * 2;
        Main.damage = extraSH + xishu * (95 + ran) * (caster.gj + 15) / 100 * d;
        U.incCaster(caster, Main.damage);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!(技能" + (int) extraSH + "点)");
        return 0;
    }

}

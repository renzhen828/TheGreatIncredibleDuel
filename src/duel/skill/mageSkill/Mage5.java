package duel.skill.mageSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;
import duel.hero.Mage;

public class Mage5 extends Skill
{
    private double xishu = 0.3;

    public Mage5(Hero caster, Hero target)
    {
        this.mark = "5";
        this.name = "冰霜新星";
        this.caster = caster;
        this.target = target;
    }

    @Override
    public int perform()
    {
        double d = U.critical(caster);
        U.showCrit(caster, d);
        int ran = RandomIntList.getInstance().getNext() / 1000;
        Main.damage = xishu * (95 + ran) * (caster.gj + 15) / (target.fy + 15)
                * d;

        caster.ultList.get(2).ultNum = caster.ultList.get(2).ultNum
                + Main.damage * 0.4;
        U.incTarget(target, Main.damage);
        U.incCaster(caster, Main.damage);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!并将对方冻在原地");
        Mage.coldCount(4, caster, target);
        return 0;
    }
}

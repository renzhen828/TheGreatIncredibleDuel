package duel.skill.mageSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;
import duel.hero.Mage;

public class Mage2 extends Skill
{
    private double xishu = 0.7;

    public Mage2(Hero caster, Hero target)
    {
        this.mark = "2";
        this.name = "寒冰箭";
        this.caster = caster;
        this.target = target;
        this.skillType = 2;
    }

    @Override
    public int perform()
    {
        double d = U.critical(caster);
        U.showCrit(caster, d);
        int ran = RandomIntList.getInstance().getNext() / 1000;
        Main.damage = xishu * (95 + ran) * (caster.gj + 15) / (target.fy + 15)
                * d;
        Mage m = (Mage) caster;

        caster.ultList.get(2).ultNum = caster.ultList.get(2).ultNum
                + Main.damage * 0.2;
        U.incTarget(target, Main.damage);
        U.incCaster(caster, Main.damage);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!对方感到一阵寒意");
        m.coldCount(3, caster, target);
        return 0;
    }
}

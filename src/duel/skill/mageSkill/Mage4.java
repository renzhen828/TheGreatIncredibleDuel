package duel.skill.mageSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;
import duel.hero.Mage;

public class Mage4 extends Skill
{
    private double xishu = 0.3;

    public Mage4(Hero caster, Hero target)
    {
        this.mark = "4";
        this.name = "燃烧";
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
        Main.damage = xishu * (95 + ran) * (caster.gj + 15) / (target.fy + 15)
                * d;
        Mage m = (Mage) caster;
        m.burnCount(1.2, caster, target);
        m.burnDouble = true;

        caster.ultList.get(1).ultNum = caster.ultList.get(1).ultNum
                + Main.damage / 12;
        U.incTarget(target, Main.damage);
        U.incCaster(caster, Main.damage);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!");
        return 0;
    }
}

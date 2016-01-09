package duel.skill.mageSkill;

import java.util.ArrayList;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;
import duel.hero.Mage;

public class MageW extends Skill
{
    private double xishu = 1;

    public MageW(Hero caster, Hero target)
    {
        this.mark = "W";
        this.name = "炎爆术";
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
        Mage m = (Mage) caster;
        m.burnCount(1.6, caster, target);
        double burnDam = 0;
        for (Integer burn : m.burnList)
            burnDam = burnDam + 0.2 * 100 * (caster.gj + 15) / (target.fy + 15)
                    * burn;
        m.burnList = new ArrayList<Integer>();
        Main.damage = comDam + burnDam;

        U.incCaster(caster, Main.damage);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (comDam + 0.5) + "点伤害,引爆灼烧造成" + (int) (burnDam + 0.5)
                + "点伤害！");
        return 0;
    }
}

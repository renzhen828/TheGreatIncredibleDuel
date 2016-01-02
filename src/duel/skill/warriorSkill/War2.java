package duel.skill.warriorSkill;

import duel.Const;
import duel.Hero;
import duel.Main;
import duel.RandomIntList;
import duel.Skill;
import duel.U;

public class War2 extends Skill
{
    private double xishu = 0.8;

    public War2(Hero caster, Hero target)
    {
        this.mark = "2";
        this.name = "破甲一击";
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

        int finalFy = (int) (target.fy * 0.9);
        int decFy = target.fy - finalFy;
        target.fy = finalFy;

        caster.ultList.get(0).ultNum = caster.ultList.get(0).ultNum
                + Main.damage / 20;
        U.incTarget(target, Main.damage);
        U.incCaster(caster, Main.damage);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!(减少防御" + decFy + "点)");
        return 0;
    }
}

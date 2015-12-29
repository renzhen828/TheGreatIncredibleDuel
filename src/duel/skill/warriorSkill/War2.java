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
        Main.damage = (95 + ran) * (caster.gj + 15) / (target.fy + 15) * d;
        Main.damage = xishu * Main.damage;
        int finalFY = (int) (target.fy * 0.9);
        int decFY=target.fy-finalFY;
        target.fy=finalFY;
        caster.ultNum[1]=caster.ultNum[1]+Main.damage/10;
        caster.ultNum[2]=caster.ultNum[2]+decFY;
        U.incCaster(caster, Main.damage);
        U.incTarget(target, Main.damage);
        
        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,造成了"
                + (int) (Main.damage + 0.5) + "点伤害!(减少防御" + decFY + "点)");
        return 0;
    }
}

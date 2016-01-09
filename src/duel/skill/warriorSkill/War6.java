package duel.skill.warriorSkill;

import duel.Buff;
import duel.Const;
import duel.Hero;
import duel.Main;
import duel.Skill;
import duel.U;
import duel.buff.Immune;

public class War6 extends Skill
{
    String buffType = "Immune";

    public War6(Hero caster, Hero target)
    {
        this.mark = "6";
        this.name = "狂暴之怒";
        this.caster = caster;
        this.target = target;
        this.skillType = 2;
    }

    @Override
    public int perform()
    {
        String debuff = "";
        if (1 == Main.half)
        {
            if (caster.gj < Main.gj1)
            {
                caster.gj = Main.gj1;
                debuff = debuff + "  降低攻击";
            } else if (caster.fy < Main.fy1)
            {
                caster.fy = Main.fy1;
                debuff = debuff + "  降低防御";
            }
        } else
        {
            if (caster.gj < Main.gj2)
            {
                caster.gj = Main.gj2;
                debuff = debuff + "  降低攻击";
            } else if (caster.fy < Main.fy2)
            {
                caster.fy = Main.fy2;
                debuff = debuff + "  降低防御";
            }
        }

        int finalGj = (int) (caster.gj * 1.1);
        int incGj = finalGj - caster.gj;
        caster.gj = finalGj;

        U.deleteBuffByType(caster, buffType);
        caster.buffList.add(new Immune(caster, target));

        int index = -1;
        do
        {
            index = -1;
            for (Buff buff : caster.buffList)
                if (2 == buff.Quality)
                    index = caster.buffList.indexOf(buff);
            if (index > -1)
            {
                debuff = debuff + "  *" + caster.buffList.get(index).name;
                caster.buffList.remove(index);
            }
        } while (index > -1);
        if (debuff.equals(""))
            debuff = "无";

        caster.ultList.get(2).ultNum = caster.ultList.get(2).ultNum + 12;
        U.incCaster(caster, 50);

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,增加攻击" + incGj
                + "点,解除自身特效" + debuff + ",并获得  *免疫");
        return 0;
    }
}

package duel.skill.mageSkill;

import duel.Buff;
import duel.Const;
import duel.Hero;
import duel.Main;
import duel.Skill;
import duel.U;
import duel.buff.Fssz;

public class Mage8 extends Skill
{
    String buffType = "fssz";

    public Mage8(Hero caster, Hero target)
    {
        this.mark = "8";
        this.name = "法术反制";
        this.caster = caster;
        this.target = target;
        this.skillType = 0;
    }

    @Override
    public int perform()
    {
        U.deleteBuffByType(target, buffType);
        target.buffList.add(new Fssz(caster, target));

        String strBuff = "";
        if (1 == Main.half)
        {
            if (target.gj > Main.gj2)
            {
                target.gj = Main.gj2;
                strBuff = strBuff + "  增加攻击";
            } else if (target.fy > Main.fy2)
            {
                target.fy = Main.fy2;
                strBuff = strBuff + "  增加防御";
            }
        } else
        {
            if (target.gj > Main.gj1)
            {
                target.gj = Main.gj1;
                strBuff = strBuff + "  增加攻击";
            } else if (target.fy > Main.fy1)
            {
                target.fy = Main.fy1;
                strBuff = strBuff + "  增加防御";
            }
        }

        int index = -1;
        do
        {
            index = -1;
            for (Buff buff : target.buffList)
                if (1 == buff.Quality)
                    index = target.buffList.indexOf(buff);
            if (index > -1)
            {
                strBuff = strBuff + "  *" + target.buffList.get(index).name;
                target.buffList.remove(index);
            }
        } while (index > -1);
        if (strBuff.equals(""))
            strBuff = "无";

        caster.ultList.get(0).ultNum = caster.ultList.get(0).ultNum + 15;

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,限制对方施法,驱散对方特效" + strBuff);
        return 0;
    }
}

package duel.skill.mageSkill;

import duel.Buff;
import duel.Const;
import duel.Hero;
import duel.Main;
import duel.Skill;
import duel.U;

public class MageQ extends Skill
{

    public MageQ(Hero caster, Hero target)
    {
        this.mark = "Q";
        this.name = "时光倒流";
        this.caster = caster;
        this.target = target;
        this.skillType = 0;
    }

    @Override
    public int perform()
    {
        if (Main.roundNum < 4)
        {
            U.dayin(caster.name + "使用了<" + this.name + ">,时光回溯到游戏开始之前……");
            U.waitSeconds(Const.INTERVEL);
            U.dayin("按任意键反创建程序体");
            mark = U.duqu();
            System.exit(0);
        }

        caster.gj = Main.roundList.get(Main.roundNum - 4).atcGj;
        caster.fy = Main.roundList.get(Main.roundNum - 4).atcFy;
        caster.xl = Main.roundList.get(Main.roundNum - 4).atcXl;
        caster.ql = 0;
        target.gj = Main.roundList.get(Main.roundNum - 4).tgtGj;
        target.fy = Main.roundList.get(Main.roundNum - 4).tgtFy;
        target.xl = Main.roundList.get(Main.roundNum - 4).tgtXl;
        target.ql = 0;

        for (Skill skill : caster.skillList)
            skill.cishu = 0;
        for (Skill u : caster.ultList)
        {
            u.area = 0;
            u.ultNum = 0;
        }
        for (Skill skill : target.skillList)
            skill.cishu = 0;
        for (Skill u : target.ultList)
        {
            u.area = 0;
            u.ultNum = 0;
        }

        int index = -1;
        do
        {
            index = -1;
            for (Buff buff : caster.buffList)
                if (2 == buff.Quality)
                    index = caster.buffList.indexOf(buff);
            if (index > -1)
            {
                caster.buffList.remove(index);
            }
        } while (index > -1);

        do
        {
            index = -1;
            for (Buff buff : target.buffList)
                if (1 == buff.Quality)
                    index = target.buffList.indexOf(buff);
            if (index > -1)
            {
                target.buffList.remove(index);
            }
        } while (index > -1);

        target.MageQ();

        U.waitSeconds(Const.INTERVEL / 2);
        U.dayin(caster.name + "使用了<" + this.name + ">,双方仿佛回到了两回合之前……");
        return 0;
    }
}

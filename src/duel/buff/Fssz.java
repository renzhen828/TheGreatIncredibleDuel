package duel.buff;

import duel.Buff;
import duel.Hero;
import duel.Main;
import duel.Skill;

public class Fssz extends Buff
{
    private int a = -1;

    public Fssz(Hero caster, Hero target)
    {
        this.name = "法术反制";
        this.type = "fssz";
        this.Quality = 2;
        this.roundNum = 4;
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void buffOn()
    {
        if ((3 == roundNum) && (Main.roundNum >= 2))
            if (null != Main.roundList.get(Main.roundNum - 2).csk)
                a = Main.roundList.get(Main.roundNum - 2).csk.skillType;
        if (target.equals(Main.attacker))
        {
            for (Skill skill : target.skillList)
                if (skill.skillType == a)
                    skill.cast = false;
            for (Skill skill : target.ultList)
                if (skill.skillType == a)
                    skill.cast = false;
        }
    }

    @Override
    public void buffOff()
    {

    }

    @Override
    public void roundStartDo()
    {
        buffOn();
    }

    @Override
    public void roundExecuteDo()
    {

    }

    @Override
    public void roundEndDo()
    {
        if (this.roundNum > 0)
            this.roundNum--;
    }
}

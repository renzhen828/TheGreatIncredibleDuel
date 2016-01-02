package duel.buff;

import duel.Buff;
import duel.Hero;
import duel.Main;
import duel.Skill;

public class Stun extends Buff
{

    public Stun(Hero caster, Hero target)
    {
        this.name = "»èÃÔ";
        this.type = "Stun";
        this.Quality = 2;
        this.roundNum = 2;
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void buffOn()
    {
        if (target.equals(Main.attacker))
        {
            for (Skill skill : target.skillList)
                skill.cast = false;
            for (Skill skill : target.ultList)
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

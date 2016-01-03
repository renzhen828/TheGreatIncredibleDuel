package duel.buff;

import duel.Buff;
import duel.Critical;
import duel.Hero;
import duel.Main;
import duel.U;

public class Sfzz extends Buff implements Critical
{
    double xishu = 0.3;

    public Sfzz(Hero caster, Hero target)
    {
        this.name = "施法专注";
        this.type = "sfzz";
        this.Quality = 1;
        this.roundNum = 4;
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void buffOn()
    {
        if ((caster.equals(Main.target)) && (Main.damage > 0))
        {
            Main.damage = 0;
            U.dayin("然而" + target.name + "只是被冻在原地，未造成任何伤害");
        }
    }

    @Override
    public void buffOff()
    {

    }

    @Override
    public void roundStartDo()
    {

    }

    @Override
    public void roundExecuteDo()
    {
        buffOn();
    }

    @Override
    public void roundEndDo()
    {
        if (this.roundNum > 0)
        {
            this.roundNum--;
        }
    }

    @Override
    public double buffCrit(double d, Hero caster)
    {
        if ((caster.equals(Main.attacker)) && (this.roundNum > 0))
            if (1 == d)
                d = 1.4;
            else
                d = 1.8;
        return d;
    }
    
}

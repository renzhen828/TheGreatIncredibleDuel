package duel.buff;

import duel.Buff;
import duel.Critical;
import duel.Hero;
import duel.Main;

public class Sfzz extends Buff implements Critical
{
    double xishu = 0.3;

    public Sfzz(Hero caster, Hero target)
    {
        this.name = "Ê©·¨×¨×¢";
        this.type = "sfzz";
        this.Quality = 1;
        this.roundNum = 5;
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void buffOn()
    {
        if (caster.equals(Main.attacker))
            Main.ignDamDec = true;
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
        {
            this.roundNum--;
        }
    }

    @Override
    public double buffCrit(double d, Hero caster)
    {
        if (caster.equals(Main.attacker))
            if (1 == d)
                d = 1.4;
            else
                d = 1.8;
        return d;
    }

}

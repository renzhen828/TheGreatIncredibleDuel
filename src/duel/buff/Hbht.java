package duel.buff;

import duel.Buff;
import duel.Hero;
import duel.Main;
import duel.U;

public class Hbht extends Buff
{
    double xishu = 0.6;

    public Hbht(Hero caster, Hero target)
    {
        this.name = "寒冰护体";
        this.type = "hbht";
        this.Quality = 1;
        this.roundNum = 4;
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void buffOn()
    {
        int decDam = 0;
        if ((caster.equals(Main.target)) && (false == Main.ignDamDec))
        {
            double finalDam = xishu * Main.damage;
            decDam = (int) (Main.damage - finalDam + 0.5);
            Main.damage = finalDam;
            if (Main.damage > 0)
                U.dayin(caster.name + "的  *" + this.name + "  生效，减少" + decDam
                        + "点伤害");
        }
        if (decDam > 0)
            target.ql = target.ql - 8;
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

    }

    @Override
    public void roundEndDo()
    {
        if (this.roundNum > 0)
        {
            buffOn();
            this.roundNum--;
        }
    }
}

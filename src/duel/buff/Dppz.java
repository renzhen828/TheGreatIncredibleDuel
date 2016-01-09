package duel.buff;

import duel.Buff;
import duel.Hero;
import duel.Main;
import duel.U;

public class Dppz extends Buff
{
    double xishu = 0.3;

    public Dppz(Hero caster, Hero target)
    {
        this.name = "盾牌屏障";
        this.type = "dppz";
        this.Quality = 1;
        this.roundNum = 4;
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void buffOn()
    {
        if ((caster.equals(Main.target)) && (false == Main.ignDamDec))
        {
            double finalDam = xishu * Main.damage;
            int decDam = (int) (Main.damage - finalDam + 0.5);
            Main.damage = finalDam;
            if (Main.damage > 0)
                U.dayin(caster.name + "的  *" + this.name + "  生效，减少" + decDam
                        + "点伤害");
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

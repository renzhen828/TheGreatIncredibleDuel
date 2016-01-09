package duel.buff;

import duel.Buff;
import duel.Hero;
import duel.Main;
import duel.U;

public class Dpfs extends Buff
{
    double xishu = 0.35;

    public Dpfs(Hero caster, Hero target)
    {
        this.name = "盾牌反射";
        this.type = "dpfs";
        this.Quality = 1;
        this.roundNum = 2;
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
            target.xl = target.xl - decDam;

            if (Main.damage > 0)
            {
                U.dayin(caster.name + "的  *" + this.name + "  生效,减少" + decDam
                        + "点伤害,并反射给对方");
                this.roundNum = 0;
            }
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
        buffOn();
    }

}

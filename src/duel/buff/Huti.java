package duel.buff;

import duel.Buff;
import duel.CreateHero;
import duel.Hero;
import duel.U;

public class Huti extends Buff
{
    double xishu = 0.8;

    public Huti(Hero target, Hero sender, CreateHero ch)
    {
        this.name = "护体";
        this.type = "js";
        this.roundNum = 4;
        this.target = target;
        this.sender = sender;
        this.ch = ch;
    }

    @Override
    public void buffOn()
    {
        if (target.equals(ch.target)){
            int hutiSH = (int) (xishu * ch.shanghai);
            int jsSH = ch.shanghai - hutiSH;
            ch.shanghai = hutiSH;
            U.dayin(target.name + "的" + this.name + "buff发生效果，减少" + jsSH + "点伤害！");
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
        if (this.roundNum > 0)
        {
            buffOn();
            this.roundNum--;
        }
    }

    @Override
    public void roundEndDo()
    {

    }

}

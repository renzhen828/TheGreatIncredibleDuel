package duel.buff;

import duel.Buff;
import duel.Hero;
import duel.Main;
import duel.U;

public class Immune extends Buff
{

    public Immune(Hero caster, Hero target)
    {
        this.name = "ÃâÒß";
        this.type = "Immune";
        this.Quality = 1;
        this.roundNum = 4;
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void buffOn()
    {
        String debuff = "";
        if (Main.roundNum > 1)
        {
            if ((caster.gj < Main.roundList.get(Main.roundNum - 1).atcGj)
                    && (caster.equals(Main.target)))
            {
                caster.gj = Main.roundList.get(Main.roundNum - 1).atcGj;
                debuff = debuff + "  ½µµÍ¹¥»÷";
            }
            if ((caster.fy < Main.roundList.get(Main.roundNum - 1).atcFy)
                    && (caster.equals(Main.target)))
            {
                caster.fy = Main.roundList.get(Main.roundNum - 1).atcFy;
                debuff = debuff + "  ½µµÍ·ÀÓù";
            }
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
                debuff = debuff + "  *" + caster.buffList.get(index).name;
                target.buffList.remove(index);
            }
        } while (index > -1);
        if (!(debuff.equals("")))
            U.dayin(caster.name + "*ÃâÒß  ÁË," + debuff);
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
            this.roundNum--;
    }
}

package duel.buff;

import duel.Buff;
import duel.Hero;
import duel.Main;
import duel.U;
import duel.hero.Mage;

public class Burn extends Buff
{
    double xishu = 0.2;

    public Burn(Hero caster, Hero target)
    {
        this.name = "×ÆÉÕ";
        this.type = "Burn";
        this.Quality = 2;
        this.roundNum = 2;
        this.caster = caster;
        this.target = target;
    }

    @Override
    public void buffOn()
    {
        if (target.equals(Main.target))
        {
            double burnDam = 0;
            int burnNum = 0;
            for (Integer burn : Mage.burnList)
            {
                burnDam = burnDam + xishu * 100 * (caster.gj + 15)
                        / (target.fy + 15);
                burn--;
                if (burn > 0)
                    burnNum++;// ¼ÆËã×ÆÉÕÉËº¦ÓëÊ£Óà²ãÊý
            }

            int index = -1;
            do
            {
                index = -1;
                for (Integer burn : Mage.burnList)
                    if (0 == burn)
                        index = Mage.burnList.indexOf(burn);
                if (index > -1)
                    Mage.burnList.remove(index);
            } while (index > -1);// Çå³ý×ÆÉÕ²ãÊý

            this.name = "×ÆÉÕ";
            if (burnNum > 0)
                this.name = this.name + burnNum;
            if (Mage.burnNum > 0)
                this.name = this.name + "." + (int) (Mage.burnNum * 10);// ÏÔÊ¾×ÆÉÕbuffÊ£Óà²ãÊý

            if (Mage.burnDouble)
                burnDam = burnDam * 2;// È¼ÉÕ¼¼ÄÜÁ½±¶×ÆÉÕÉËº¦
            Mage.burnDouble = false;

            caster.ultList.get(1).ultNum = caster.ultList.get(1).ultNum
                    + burnDam * 0.2;
            U.incCaster(caster, burnDam * 1.5);
            Main.damage = Main.damage + burnDam;

            if (burnDam > 0)
                U.dayin("*×ÆÉÕ  ÉËº¦" + burnDam + "µã");
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

    }

}

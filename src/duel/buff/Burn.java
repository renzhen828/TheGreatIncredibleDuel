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
            int index = -1;
            Mage m = (Mage) caster;
            for (Integer burn : m.burnList)
            {
                burnDam = burnDam + xishu * 100 * (caster.gj + 15)
                        / (target.fy + 15);

                index = m.burnList.indexOf(burn);
                m.burnList.set(index, burn - 1);
                if (burn > 1)
                    burnNum++;// ¼ÆËã×ÆÉÕÉËº¦ÓëÊ£Óà²ãÊý
            }

            do
            {
                index = -1;
                for (Integer burn : m.burnList)
                    if (0 == burn)
                        index = m.burnList.indexOf(burn);
                if (index > -1)
                    m.burnList.remove(index);
            } while (index > -1);// Çå³ý×ÆÉÕ²ãÊý

            this.name = "×ÆÉÕ";
            if (burnNum > 0)
                this.name = this.name + burnNum;// ÏÔÊ¾×ÆÉÕ²ãÊý

            if (0 == burnNum)
                roundNum = 0;// Çå³ý×ÆÉÕbuff

            if (m.burnDouble)
                burnDam = burnDam * 2;// È¼ÉÕ¼¼ÄÜÁ½±¶×ÆÉÕÉËº¦
            m.burnDouble = false;

            caster.ultList.get(1).ultNum = caster.ultList.get(1).ultNum
                    + burnDam * 0.2;
            U.incCaster(caster, burnDam * 1.5);
            Main.damage = Main.damage + burnDam;

            if (burnDam > 0)
                U.dayin("*×ÆÉÕ  ÉËº¦" + (int) (burnDam + 0.5) + "µã");
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

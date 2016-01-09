package duel.hero;

import java.util.ArrayList;

import duel.Buff;
import duel.Hero;
import duel.U;
import duel.buff.Burn;
import duel.buff.Cold;
import duel.skill.mageSkill.Mage1;
import duel.skill.mageSkill.Mage2;
import duel.skill.mageSkill.Mage3;
import duel.skill.mageSkill.Mage4;
import duel.skill.mageSkill.Mage5;
import duel.skill.mageSkill.Mage6;
import duel.skill.mageSkill.Mage7;
import duel.skill.mageSkill.Mage8;
import duel.skill.mageSkill.MageE;
import duel.skill.mageSkill.MageQ;
import duel.skill.mageSkill.MageW;

public class Mage extends Hero
{
    public double burnNum;
    private double coldNum;
    public boolean burnDouble;
    public boolean mageE = false;
    public ArrayList<Integer> burnList = new ArrayList<Integer>();

    @Override
    public void initSkill(Hero target)
    {
        this.skillList.add(new Mage1(this, target));
        this.skillList.add(new Mage2(this, target));
        this.skillList.add(new Mage3(this, target));
        this.skillList.add(new Mage4(this, target));
        this.skillList.add(new Mage5(this, target));
        this.skillList.add(new Mage6(this, target));
        this.skillList.add(new Mage7(this, target));
        this.skillList.add(new Mage8(this, target));
    }

    @Override
    public void initUlt(Hero target)
    {
        this.ultList.add(new MageQ(this, target));
        this.ultList.add(new MageW(this, target));
        this.ultList.add(new MageE(this, target));
    }

    @Override
    public String discribe()
    {
        String str = "  ";
        if (burnNum > 0)
            str = str + "Burn" + (int) (burnNum * 10);
        if (coldNum > 0)
            str = str + "Cold" + (int) coldNum;
        return (str);
    }

    @Override
    public void MageQ()
    {
        burnNum = 0;
        coldNum = 0;
        burnList = new ArrayList<Integer>();
    }

    public void burnCount(double n, Hero caster, Hero target)
    {
        coldNum = 0;
        boolean burnHad = false;
        for (Buff buff : target.buffList)
            if (buff.type.equals("Burn"))
                burnHad = true;
        if (!burnHad)
        {
            burnList = new ArrayList<Integer>();
            target.buffList.add(new Burn(caster, target));
        }
        burnNum = burnNum + n + 0.001;

        if (burnNum >= 1)
        {
            burnNum--;
            burnList.add(3);
        }
        if (burnNum >= 1)
        {
            burnNum--;
            burnList.add(3);
        }
    }

    public void coldCount(double n, Hero caster, Hero target)
    {
        burnNum = 0;
        int index = -1;
        do
        {
            index = -1;
            for (Integer burn : burnList)
                index = burnList.indexOf(burn);
            if (index > -1)
                burnList.remove(index);
        } while (index > -1);

        U.deleteBuffByType(caster, "Burn");

        if ((!mageE) || (coldNum < 6))
        {
            coldNum = coldNum + n;
            if (coldNum >= 8)
            {
                coldNum = coldNum - 8;
                U.deleteBuffByType(caster, "Cold");
                target.buffList.add(new Cold(caster, target));
                caster.ultList.get(2).ultNum = caster.ultList.get(2).ultNum + 8;
                U.dayin(target.name + "±ª∂≥Ω·¡À£°");
            }
        }
    }
}

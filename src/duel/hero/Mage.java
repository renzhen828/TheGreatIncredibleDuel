package duel.hero;

import java.util.ArrayList;

import duel.Buff;
import duel.Hero;
import duel.U;
import duel.buff.Burn;
import duel.buff.Cold;
import duel.skill.warriorSkill.War2;

public class Mage extends Hero
{
    public static double burnNum;
    private static double coldNum;
    public static boolean burnDouble;
    public static ArrayList<Integer> burnList = new ArrayList<Integer>();

    @Override
    public void initSkill(Hero p1)
    {
        this.skillList.add(new War2(p1, this));
    }

    @Override
    public void initUlt(Hero p1)
    {

    }

    public static void burnCount(double n, Hero caster, Hero target)
    {
        coldNum = 0;
        burnNum = burnNum + n;
        boolean burnHad = false;
        for (Buff buff : target.buffList)
            if (buff.type.equals("Burn"))
                burnHad = true;
        if (!burnHad)
            target.buffList.add(new Burn(caster, target));
        if (burnNum > 1)
        {
            burnNum--;
            burnList.add(3);
        }
        if (burnNum > 1)
        {
            burnNum--;
            burnList.add(3);
        }
    }

    public static void coldCount(double n, Hero caster, Hero target)
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

        coldNum = coldNum + n;
        if (coldNum >= 8)
        {
            coldNum = coldNum - 8;
            U.deleteBuffByType(caster, "冻结");
            target.buffList.add(new Cold(caster, target));
            caster.ultList.get(2).ultNum = caster.ultList.get(2).ultNum + 8;
            U.dayin(target.name + "被冻结了！");
        }
    }
}

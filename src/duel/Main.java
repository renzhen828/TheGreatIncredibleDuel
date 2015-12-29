package duel;

import java.util.ArrayList;

public class Main
{
    public static int roundNum = 0;
    public static double damage = 0;
    public static ArrayList<Round> roundList;
    public static Hero p1, p2;
    public static Hero attacker, target;

    public static void main(String[] args)
    {
        String jixu = "Y";
        while (jixu.equals("Y"))
        {
            U.dayin("**********游戏开始**************");
            CreateHero.cHero();
            jixu = startFight();
            jixu = jixu.toUpperCase();
        }
    }

    private static String startFight()
    {
        roundList = new ArrayList<Round>();
        while (p1.xl > 0 && p2.xl > 0)
        {
            U.dayin(" ");
            U.showXl(p1);
            U.showXl(p2);
            attacker = p1;
            target = p2;
            damage = 0;
            round(p1, p2);
            U.dayin(" ");
            U.showXl(p1);
            U.showXl(p2);
            attacker = p2;
            target = p1;
            damage = 0;
            round(p2, p1);
        }
        U.waitSeconds(Const.INTERVEL);
        U.dayin("=========Game Over=========");
        U.waitSeconds(Const.INTERVEL);
        U.dayin("===========================");
        U.dayin("要继续吗？ （Y/N）");
        String jixu = U.duqu();
        return jixu;
    }

    private static void round(Hero attacker, Hero target)
    {
        if (attacker.xl > 0)
        {
            Round round = new Round(attacker, target);
            roundList.add(round);
            if (target.xl <= 0)
            {
                if (-999 != target.xl)
                    U.showDeath(target);
                U.showWin(attacker);
            }
        }
    }
}
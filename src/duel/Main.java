package duel;

import java.util.ArrayList;

import duel.buff.Sfzz;

public class Main
{
    public static int roundNum = 0, half = 0, gj1 = 0, fy1 = 0, gj2 = 0,
            fy2 = 0;
    public static double damage = 0;
    public static boolean ignDamDec = false;
    public static ArrayList<Round> roundList;
    public static Hero p1, p2;
    public static Hero attacker, target;
    public static ArrayList<Critical> critList = new ArrayList<Critical>();

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
        gj1 = p1.gj;
        fy1 = p1.fy;
        gj2 = p2.gj;
        fy2 = p2.fy;

        roundNum = 0;
        roundList = new ArrayList<Round>();
        critList.add(new Sfzz(attacker, target));

        int zero = 0;
        while (0 == zero)
        {
            if ((p1.xl <= 0) || (p2.xl <= 0))
                break;
            U.dayin(" ");
            U.showXl(p1);
            U.showXl(p2);
            half = 1;
            attacker = p1;
            target = p2;
            damage = 0;
            round(p1, p2);
            if ((p1.xl <= 0) || (p2.xl <= 0))
                break;
            U.dayin(" ");
            U.showXl(p1);
            U.showXl(p2);
            half = 2;
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
            roundNum++;
            if (target.xl <= 0)
            {
                if (-999 != target.xl)
                    U.showDeath(target);
                U.showWin(attacker);
            }
        }
    }
}
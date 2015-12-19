package duel;

import java.util.ArrayList;

public class CreateHero
{
    public int chaju = 30;
    public int zhiye, k;
    public Hero p1, p2;
    int gj = 63, fy = 63, xl = 223; // for语句里这句有问题吧
    int roundNum = 0;
    public ArrayList<Round> roundList;
    public int shanghai=0;
    public Hero attacker = p1, target = p2;
    
    public CreateHero()
    {
        roundList = new ArrayList<Round>();
        String input = "";
        U.dayin("输入你能接受的差距：");
        input = U.duqu();
        chaju = U.s2i(input);

        U.dayin("*****开始选择职业******");

        // p1
        startSuiJi(63, 63, 223);
        U.dayin("请选择一个职业  1 战士  2 法师");
        input = U.duqu();
        zhiye = U.s2i(input);
        if (zhiye == 1)
        {
            p1 = new Warrior();
            p1.type = 1;
        }
        if (zhiye == 2)
        {
            p1 = new Mage();
            p1.type = 2;
        }
        p1.name = "p1";
        p1.gj = gj;
        p1.fy = fy;
        p1.xl = xl;

        // p2
        startSuiJi(63, 63, 223);
        U.dayin("请选择一个职业  1 战士  2 法师");
        input = U.duqu();
        zhiye = U.s2i(input);
        if (zhiye == 1)
        {
            p2 = new Warrior();
            p2.type = 1;
        }
        if (zhiye == 2)
        {
            p2 = new Mage();
            p2.type = 2;
        }
        p2.name = "p2";
        p2.gj = gj;
        p2.fy = fy;
        p2.xl = xl;
        
        p1.initSkill(p2, this);
        p2.initSkill(p1, this);
    }

    private int startSuiJi(int c_gj, int c_fy, int c_xl)
    {
        int sj = 0;
        int total = 0;
        RandomIntList randomList = RandomIntList.getInstance();
        do
        {

            for (int i = 1; i <= 6; i++)
            {
                sj = sj + randomList.getNext()/10;
            }
            gj = c_gj + sj;
            sj = 0;
            for (int i = 1; i <= 6; i++)
            {
                sj = sj + randomList.getNext()/10;
            }
            fy = c_fy + sj;
            sj = 0;
            for (int i = 1; i <= 6; i++)
            {
                sj = sj + randomList.getNext()/5;
            }
            xl = c_xl + sj;
            total = gj + fy + (int) xl * 9 / 28;
            // U.dayin(""+total);
        } while ((total < (270 - chaju)) || (total > (270 + chaju)));
        U.dayin("************************");
        U.dayin("攻击力 = " + gj);
        U.dayin("防御力 = " + fy);
        U.dayin("生命值 = " + xl);

        return gj;
    }

    public String startFight()
    {
        while (p1.xl > 0 && p2.xl > 0)
        {
            U.showXl(p1);
            U.showXl(p2);
            attacker = p1;
            target = p2;
            shanghai = 0;
            huihe(p1, p2);
            U.showXl(p1);
            U.showXl(p2);
            attacker = p2;
            target = p1;
            shanghai = 0;
            huihe(p2, p1);
        }

        U.dayin("=========================");
        U.dayin("=========================");
        U.dayin("要继续吗？ （Y/N）");
        String jixu = U.duqu();
        return jixu;
    }

    private void huihe(Hero attacker, Hero enemy)
    {
        if (attacker.xl > 0)
        {
            Round round = new Round(attacker,enemy,this);
            roundList.add(round);
            if (enemy.xl <= 0)
            {
                U.showDeath(enemy);
                U.showWin(attacker);
            }
        }

    }


    public static void main(String[] args)
    {
        String jixu = "Y";
        while (jixu.equals("Y"))
        {
            U.dayin("**********游戏开始**************");
            CreateHero ch = new CreateHero();
            jixu = ch.startFight();
            jixu = jixu.toUpperCase();
        }

    }
}

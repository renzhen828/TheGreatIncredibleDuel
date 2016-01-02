package duel;

import duel.hero.Mage;
import duel.hero.Warrior;

public class CreateHero
{
    private static int gj = 0, fy = 0;
    private static int diff = 100, type = 0;

    public static void cHero()
    {
        String input = "";
        boolean cz = false;
        do
        {
            U.dayin("输入最大能力差值：   0-54");
            input = U.duqu();
            if ((1 == input.length()) && (input.compareTo("0") >= 0)
                    && (input.compareTo("9") <= 0))
                cz = true;
            if (2 == input.length())
                if ((input.substring(0, 1).compareTo("0") >= 0)
                        && (input.substring(0, 1).compareTo("4") <= 0)
                        && (input.substring(1, 2).compareTo("0") >= 0)
                        && (input.substring(1, 2).compareTo("9") <= 0))
                    cz = true;
                else if ((input.substring(0, 1).compareTo("5") == 0)
                        && (input.substring(1, 2).compareTo("0") >= 0)
                        && (input.substring(1, 2).compareTo("4") <= 0))
                    cz = true;
        } while (false == cz);
        diff = U.s2i(input);
        U.dayin("*****开始选择职业******");

        startSuiJi(63, 63);
        RandomIntList randomList = RandomIntList.getInstance();
        int ran = randomList.getNext() % 50;
        String name = Const.heroName[ran];
        do
        {
            U.dayin(name + "\t请选择一个职业  1 战士  2 法师");
            input = U.duqu();
        } while ((input.compareTo("1") < 0) || (input.compareTo("2") > 0)
                || (1 != input.length()));
        type = U.s2i(input);
        if (type == 1)
        {
            Main.p1 = new Warrior();
            Main.p1.type = 1;
        }
        if (type == 2)
        {
            Main.p1 = new Mage();
            Main.p1.type = 2;
        }
        Main.p1.name = name;
        Main.p1.gj = gj;
        Main.p1.fy = fy;
        Main.p1.xl = Const.HP;

        startSuiJi(63, 63);
        ran = randomList.getNext() % 50;
        name = Const.heroName[ran];
        do
        {
            U.dayin(name + "\t请选择一个职业  1 战士  2 法师");
            input = U.duqu();
        } while ((input.compareTo("1") < 0) || (input.compareTo("2") > 0)
                || (1 != input.length()));
        type = U.s2i(input);
        if (type == 1)
        {
            Main.p2 = new Warrior();
            Main.p2.type = 1;
        }
        if (type == 2)
        {
            Main.p2 = new Mage();
            Main.p2.type = 2;
        }
        ran = randomList.getNext() % 55;
        Main.p2.name = name;
        Main.p2.gj = gj;
        Main.p2.fy = fy;
        Main.p2.xl = Const.HP;
        Main.p2.ql = 20;

        Main.p1.initSkill(Main.p2);
        Main.p1.initUlt(Main.p2);
        Main.p2.initSkill(Main.p1);
        Main.p2.initUlt(Main.p1);
    }

    private static void startSuiJi(int c_gj, int c_fy)
    {
        int sj = 0;
        RandomIntList randomList = RandomIntList.getInstance();
        do
        {
            sj = 0;
            for (int i = 1; i <= 6; i++)
            {
                sj = sj + randomList.getNext() / 1000;
            }
            gj = c_gj + sj;
            sj = 0;
            for (int i = 1; i <= 6; i++)
            {
                sj = sj + randomList.getNext() / 1000;
            }
            fy = c_fy + sj;
            sj = 0;
        } while (((gj + fy) <= (180 - diff - 1))
                || ((gj + fy) >= (180 + diff + 1)));

        U.dayin("************************");
        U.dayin("攻击力 " + gj);
        U.dayin("防御力 " + fy);
    }
}

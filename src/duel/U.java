package duel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class U
{
    /**
     * 在控制台上读取一行字符串
     * @return 读取的字符串，如果读取失败会返回字符串"null"
     */
    public static String duqu()
    {
        String str = "null";
        try
        {
            BufferedReader strin = new BufferedReader(new InputStreamReader(
                    System.in));

            str = strin.readLine();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (str.equals("gg")){
            
        }
        return str;
    }

    
    /**
     * 打印一行字符串到控制台
     * @param str 要打印的内容
     */
    public static void dayin(String str)
    {
        System.out.println(str);
    }
    
    public static int s2i(String str){
        int i = Integer.parseInt(str);
        return i;
    }
    
    
    public static void showDeath(Hero enemy)
    {
        waitSeconds(Const.iterval);
        U.dayin(enemy.name + "已经爬不起来了.");
    }

    public static void showShangHai(Hero enemy, int shanghai)
    {
        waitSeconds(Const.iterval);
        U.dayin(enemy.name + "受到了" + shanghai + "点伤害。");
    }

    public static void showXl(Hero p)
    {
        waitSeconds(Const.iterval/2);
        U.dayin("****" + p.name + "-HP:" + p.xl + "****");
    }
    

    public static void waitSeconds(double s)
    {
        try
        {
            Thread.sleep((long)(1000 * s));
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void showWin(Hero attacker)
    {
        waitSeconds(Const.iterval);
        U.dayin(attacker.name + "终于获得了胜利！");
    }

}

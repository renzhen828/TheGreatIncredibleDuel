package duel;

import java.util.ArrayList;

public class RandomIntList
{
    private int index = 0;

    private static RandomIntList instance;

    private ArrayList<Integer> al = new ArrayList<Integer>();

    private RandomIntList()
    {
        for (int i = 0; i < 100; i++)
        {
            al.add(new Integer((int) (Math.random() * 10000)));
        }
    }

    public static RandomIntList getInstance()
    {
        if (instance == null)
        {
            instance = new RandomIntList();
        }
        return instance;
    }

    public int getNext()
    {
        if (index >= 100)
        {
            index = 0;
        }
        Integer i_o = this.getAl().get(index);
        index++;
        return i_o.intValue();
    }

    private ArrayList<Integer> getAl()
    {
        return al;
    }

}

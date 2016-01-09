package duel;

import java.util.ArrayList;

public abstract class Hero
{
    public int gj = 63, fy = 63, xl = Const.HP, ql = 0;
    public int type = 0; // zhiye
    public String name = " ";

    public ArrayList<Buff> buffList = new ArrayList<Buff>();
    public ArrayList<Skill> skillList = new ArrayList<Skill>();
    public ArrayList<Skill> ultList = new ArrayList<Skill>();

    public abstract void initSkill(Hero p);

    public abstract void initUlt(Hero p);

    public abstract String discribe();

    public abstract void MageQ();

}

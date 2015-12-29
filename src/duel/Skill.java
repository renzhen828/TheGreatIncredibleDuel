package duel;

public abstract class Skill
{
    public int cishu = 0, hold = 0, area = 0;
    public boolean cast = true;
    public String name = "skill";
    public String mark = "null";
    public Hero caster, target;

    public abstract int perform();

    public String describeSkill()
    {
        return "[" + mark + "]:" + name + " " + (hold + 1);
    }

    public String describeUlt()
    {
        return "[" + mark + "]:" + name;
    }
}

package duel;

public abstract class Skill
{
    public int cishu;
    public String name = "skill";
    public String mark = "null";
    public Hero target, sender;
    public CreateHero ch;

    public abstract int perform();

    public String describe()
    {
        return "[" + mark + "]:" + name;
    }
}

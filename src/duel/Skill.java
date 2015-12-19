package duel;

public abstract class Skill
{
    public int cishu;
    public abstract int perform(Hero p1, Hero p2, CreateHero ch);
}

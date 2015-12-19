package duel;

public abstract class Buff
{
    public abstract void buffOn(Hero target, Hero sender, CreateHero ch);

    public abstract void buffOff(Hero target, Hero sender, CreateHero ch);

    public abstract void roundStartDo();

    public abstract void roundExecuteDo();

    public abstract void roundEndDo();
}

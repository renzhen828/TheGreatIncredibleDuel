package duel;

public abstract class Buff
{
    public String name = "";
    public String type = "";
    public int roundNum;
    public Hero caster, target;

    public abstract void buffOn();

    public abstract void buffOff();

    public abstract void roundStartDo();

    public abstract void roundExecuteDo();

    public abstract void roundEndDo();
}

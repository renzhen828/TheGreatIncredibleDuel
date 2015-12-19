package duel;

public abstract class Buff
{
    public String name = "";
    public String type = ""; // js: ºı…À 
    public int roundNum;
    public Hero target, sender;
    public CreateHero ch;
    
    public abstract void buffOn();

    public abstract void buffOff();

    public abstract void roundStartDo();

    public abstract void roundExecuteDo();

    public abstract void roundEndDo();
}

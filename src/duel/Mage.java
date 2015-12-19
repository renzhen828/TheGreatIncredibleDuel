package duel;

public class Mage extends Hero
{
    public Mage()
    {
        super();
    }

    public void initSkill(Hero p1, CreateHero createHero)
    {
        this.skillList.add(new PuGong(p1,this,createHero));
    }
    
}

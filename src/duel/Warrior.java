package duel;

public class Warrior extends Hero
{

    public Warrior()
    {
        super();
    }

    public void initSkill(Hero p1, CreateHero createHero)
    {
        this.skillList.add(new PuGong(p1,this,createHero));
        this.skillList.add(new War1(p1,this,createHero));
        this.skillList.add(new War2(this,this,createHero));
        this.skillList.add(new War3(p1,this,createHero));
        this.skillList.add(new War4(p1,this,createHero));
        this.skillList.add(new War5(p1,this,createHero));
        this.skillList.add(new War6(p1,this,createHero));
        this.skillList.add(new War7(p1,this,createHero));
    }

    public void sk1(){
        
    }
}

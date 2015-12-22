package duel;

public class War4 extends Skill
{
    double xishu = 1.2;
    War4(Hero target, Hero sender, CreateHero c){
        this.mark = "4";
        this.name = "猛烈打击";
        this.target = target;
        this.sender = sender;
        this.ch = c;
        this.cishu = 0;
    }
 


    @Override
    public int perform()
    {
        int ran = RandomIntList.getInstance().getNext() / 5;
        ch.shanghai = (int) 50 * sender.gj / target.fy + ran;
        
        int finalSH = (int)(xishu*ch.shanghai);
        int extraSH = finalSH - ch.shanghai;
        ch.shanghai = finalSH;
        U.dayin(sender.name+"使用了"+this.name+",对"+target.name+"造成了"+extraSH+"点额外伤害！");
        this.cishu++;
        return 0;
    }

}

package duel;

import duel.buff.Huti;

public class War2 extends Skill
{
    String buffType = "js";
    War2(Hero target, Hero sender, CreateHero c){
        this.mark = "2";
        this.name = "Υ½¶ά»¤Με";
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

        U.deleteBuffByType(target, buffType);
        
        target.buffList.add(new Huti(target,sender,ch));
        cishu++;
        return 0;
    }

}

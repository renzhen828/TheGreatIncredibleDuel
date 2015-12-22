package duel.skill.warriorSkill;

import duel.CreateHero;
import duel.Hero;
import duel.RandomIntList;
import duel.Skill;
import duel.U;

public class War3 extends Skill
{
    double xishu = 1.2;
    public War3(Hero target, Hero sender, CreateHero c){
        this.mark = "3";
        this.name = "使劲打击";
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

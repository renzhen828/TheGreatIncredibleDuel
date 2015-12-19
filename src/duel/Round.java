package duel;

public class Round
{
    Hero attacker, target;
    CreateHero ch;
    int num;

    public Round(Hero atc, Hero tgt, CreateHero ch1)
    {
        attacker = atc;
        target = tgt;
        ch = ch1;
        num = ch.roundNum + 1;
        roundStart();
        roundExecute();
        roundEnd();
        
    }

    private void roundStart()
    {
        for (Buff buff : attacker.buffList)
        {
            buff.roundStartDo();
        }
        for (Buff buff : target.buffList)
        {
            buff.roundStartDo();
        }
    }

    private void roundExecute()
    {

        skillPerform(attacker, target);
        
        for (Buff buff : attacker.buffList)
        {
            buff.roundExecuteDo();
        }
        for (Buff buff : target.buffList)
        {
            buff.roundExecuteDo();
        }
        
        target.xl = target.xl - ch.shanghai;
        U.showShangHai(target, ch.shanghai);
    }

    private void roundEnd()
    {
        for (Buff buff : attacker.buffList)
        {
            buff.roundEndDo();
        }
        for (Buff buff : target.buffList)
        {
            buff.roundEndDo();
        }
        U.deleteBuffByNum(attacker);
        U.deleteBuffByNum(target);
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public void skillPerform(Hero caster, Hero target)
    {
        Skill csk = null;
        while (null == csk)
        {
            U.showSkillList(caster);
            String mark = U.duqu();
            for (Skill skill : caster.skillList)
            {
                if (mark.equals(skill.mark))
                {
                    csk = skill;
                    break;
                }
            }
        }
        csk.perform();
    }
}

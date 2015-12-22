package duel;

public class Round
{
    Hero attacker, target;
    CreateHero ch;
    int num;
    Skill csk = null;

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
        createSkill(attacker);
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
        handleSkill(attacker);
    }

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    private void createSkill(Hero attacker)
    {
        int totality = 0, skillNum = 4;
        for (Skill skill : attacker.skillList)
            if (1 == skill.area)
            {
                skillNum--;
            } else if (0 == skill.area)
            {
                totality = totality + skill.cishu + 1;
            }
        for (int i = 1; i <= skillNum; i++)
        {
            RandomIntList randomList = RandomIntList.getInstance();
            int ranNum = randomList.getNext() % totality+1;
            int k = 0;
            for (Skill skill : attacker.skillList)
            {
                if (0 == skill.area)
                {
                    k++;
                    int p = k;
                    k = k + skill.cishu;
                    if ((p <= ranNum) && (k >= ranNum))
                    {
                        skill.area = 1;
                        totality = totality - skill.cishu - 1;
                    }
                }
            }
        }
    }

    private void skillPerform(Hero caster, Hero target)
    {
        csk = null;
        while (null == csk)
        {
            U.showSkillList(caster);
            String mark = U.duqu();
            for (Skill skill : caster.skillList)
            {
                if ((mark.equals(skill.mark)) && (skill.area == 1))
                {
                    csk = skill;
                    csk.cishu++;
                    break;
                }
            }
        }
        csk.perform();
    }

    private void handleSkill(Hero attacker)
    {
        for (Skill skill : attacker.skillList)
            if (skill.area == -1)
            {
                skill.area = 0;
            } else if (skill.area == 1)
            {
                skill.hold++;
                if ((skill.hold == 2) || (skill.mark.equals(csk.mark)))
                {
                    skill.area = -1;
                    skill.hold = 0;
                }
            }
    }
}

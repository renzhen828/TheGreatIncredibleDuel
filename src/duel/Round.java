package duel;

public class Round
{
    private Hero attacker, target;
    private int num;
    private Boolean ult = false;
    private Skill csk = null;

    public Round(Hero atc, Hero tgt)
    {
        attacker = atc;
        target = tgt;
        num = Main.roundNum + 1;
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
        createUlt(attacker);
        skillPerform(attacker, target);
        for (Buff buff : attacker.buffList)
        {
            buff.roundExecuteDo();
        }
        for (Buff buff : target.buffList)
        {
            buff.roundExecuteDo();
        }
        target.xl = target.xl - (int) (Main.damage + 0.5);
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
        if (false == ult)
            handleSkill(attacker);
        attacker.ql = attacker.ql + 4;
        for (Skill skill : attacker.skillList)
            skill.cast = true;
        for (Skill skill : attacker.ultList)
            skill.cast = true;
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
                skillNum--;
            else if (0 == skill.area)
                totality = totality + skill.cishu + 1;
        for (int i = 1; i <= skillNum; i++)
        {
            RandomIntList randomList = RandomIntList.getInstance();
            int ranNum = randomList.getNext() % totality + 1;
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
                        break;
                    }
                }
            }
        }
    }

    private void createUlt(Hero attacker)
    {
        RandomIntList randomList = RandomIntList.getInstance();
        int ranQili = (int) (randomList.getNext() / 100 + 0.5);
        if ((attacker.ql >= 40) && (attacker.ql >= ranQili))
        {
            int totality = 0;
            for (int i = 1; i <= 3; i++)
                totality = totality + (int) (attacker.ultNum[i] + 0.5);
            if (0 < totality)
            {
                int k = 0;
                int ranNum = randomList.getNext() % totality + 1;
                for (int i = 1; i <= 3; i++)
                {
                    int p = k;
                    k = k + (int) (attacker.ultNum[i] + 0.5);
                    if ((p < ranNum) && (k >= ranNum))
                    {
                        attacker.ultUse[i] = 1;
                        break;
                    }
                }
            }
            int j = 0;
            for (Skill u : attacker.ultList)
            {
                j++;
                if (1 == attacker.ultUse[j])
                    u.area = 1;
            }
        }
    }

    private void skillPerform(Hero caster, Hero target)
    {
        csk = null;
        boolean cast = false;
        for (int i = 1; i <= 3; i++)
        {
            if (null != csk)
                break;
            cast = U.showSkillList(caster);
            String mark = U.duqu();
            if (("gg" == mark) || ("GG" == mark))
            {
                U.waitSeconds(Const.INTERVEL * 2);
                U.dayin("在一片欢声笑语," + caster.name + "打出了GG");
                caster.xl = -999;
            }
            for (Skill skill : caster.skillList)
            {
                if ((mark.equals(skill.mark)) && (1 == skill.area)
                        && skill.cast)
                {
                    csk = skill;
                    csk.cishu++;
                    ult = false;
                    break;
                }
            }
            for (Skill skill : caster.ultList)
            {
                if ((mark.equals(skill.mark)) && (1 == skill.area)
                        && skill.cast)
                {
                    csk = skill;
                    ult = true;
                    if (attacker.ql > 100)
                        attacker.ql = attacker.ql - 100;
                    else
                        attacker.ql = 0;
                    for (Skill u : attacker.ultList)
                    {
                        u.area = 0;
                    }
                    for (int j = 1; j <= 3; j++)
                        attacker.ultUse[j] = 0;
                    break;
                }
            }
        }
        if (null == csk)
            U.over(caster);
        if (cast)
            csk.perform();
        else
            U.dayin("没有可施放的技能！");
    }

    private void handleSkill(Hero attacker)
    {
        for (Skill skill : attacker.skillList)
            if (-1 == skill.area)
                skill.area = 0;
            else if (1 == skill.area)
            {
                skill.hold++;
                if ((2 == skill.hold) || (skill.mark.equals(csk.mark)))
                {
                    skill.area = -1;
                    skill.hold = 0;
                }
            }
    }
}

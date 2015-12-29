package duel;

import duel.skill.warriorSkill.War1;

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
        handleSkill(attacker);
        if (ult)
            if (attacker.ql > 100)
                attacker.ql = attacker.ql - 100;
            else
                attacker.ql = 0;
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
            for (int i = 0; i <= 2; i++)
                totality = totality + (int) attacker.ultNum[i];
            if (0 < totality)
            {
                int k = 0;
                int ranNum = randomList.getNext() % totality + 1;
                for (int i = 0; i <= 2; i++)
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
                if (1 == attacker.ultUse[j])
                    u.area = 1;
                j++;
            }
        }
    }

    private void skillPerform(Hero caster, Hero target)
    {
        csk = null;
        ult = false;
        boolean cast = false;
        for (int i = 1; i <= 3; i++)
        {
            if (null != csk)
                break;
            cast = U.showSkillList(caster);
            if (!cast)
            {
                U.waitSeconds(Const.INTERVEL / 2);
                U.dayin("没有可施放的技能！");
                csk = new War1(caster, target);
                U.waitSeconds(Const.INTERVEL * 2);
                break;
            }
            String mark = U.duqu();
            mark = mark.toUpperCase();
            if ("GG".equals(mark))
            {
                U.waitSeconds(Const.INTERVEL * 2);
                U.dayin("在一片欢声笑语," + caster.name + "打出了GG");
                csk = new War1(caster, target);
                cast = false;
                caster.xl = -999;
                break;
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
                    for (Skill u : attacker.ultList)
                    {
                        u.area = 0;
                    }
                    for (int j = 0; j <= 2; j++)
                        attacker.ultUse[j] = 0;
                    break;
                }
            }
        }
        if (null == csk)
            U.over(caster);
        if (cast)
            csk.perform();
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

package duel;

import duel.skill.warriorSkill.War1;

public class Round
{
    private Hero attacker, target;
    private Boolean ult = false;
    public Skill csk = null;
    public int atcGj, atcFy, atcXl, tgtGj, tgtFy, tgtXl;
    public double damage = 0;

    public Round(Hero atc, Hero tgt)
    {
        attacker = atc;
        target = tgt;
        roundStart();
        roundExecute();
        roundEnd();
    }

    private void roundStart()
    {
        Main.ignDamDec = false;
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
    }

    private void roundEnd()
    {
        int index = -1;
        for (Buff buff : attacker.buffList)
            if ("Immune" == buff.type)
                index = attacker.buffList.indexOf(buff);
        if (index > -1)
            attacker.buffList.get(index).buffOn();
        index = -1;
        for (Buff buff : target.buffList)
            if ("Immune" == buff.type)
                index = target.buffList.indexOf(buff);
        if (index > -1)
            target.buffList.get(index).buffOn(); // 这一段处理免疫buff，费死劲了

        for (Buff buff : attacker.buffList)
        {
            buff.roundEndDo();
        }
        for (Buff buff : target.buffList)
        {
            buff.roundEndDo();
        }
        U.deleteBuffByNum(attacker);
        U.deleteBuffByNum(target); // 删到期buff

        target.xl = target.xl - (int) (Main.damage + 0.5);
        this.damage = Main.damage;

        atcGj = attacker.gj;
        atcFy = attacker.fy;
        atcXl = attacker.xl;
        tgtGj = target.gj;
        tgtFy = target.fy;
        tgtXl = target.xl;

        handleSkill(attacker); // 技能列表回合后处理

        if (ult)
            if (attacker.ql > 100)
                attacker.ql = attacker.ql - 100;
            else
                attacker.ql = 0;
        attacker.ql = attacker.ql + 6; // 处理气力

        for (Skill skill : attacker.skillList)
            skill.cast = true;
        for (Skill skill : attacker.ultList)
            skill.cast = true; // 解除沉默
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
            int ranNum = RandomIntList.getInstance().getNext() % totality + 1;
            int k = 0;
            for (Skill skill : attacker.skillList)
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

    private void createUlt(Hero attacker)
    {
        RandomIntList randomList = RandomIntList.getInstance();
        int ranQili = (int) (randomList.getNext() / 100 + 0.5);
        if ((attacker.ql >= 40)
                && ((int) ((attacker.ql - 40) / 6 * 9 + 10) >= ranQili))
        {
            int totality = 0;
            for (Skill u : attacker.ultList)
                if (u.ultNum < 0)
                    u.ultNum = 0;
                else if (0 == u.area)
                    totality = totality + (int) u.ultNum;
            if (0 < totality)
            {
                int k = 0;
                int ranNum = randomList.getNext() % totality + 1;
                for (Skill u : attacker.ultList)
                    if (0 == u.area)
                    {
                        int p = k;
                        k = k + (int) u.ultNum;
                        if ((p < ranNum) && (ranNum <= k))
                        {
                            u.area = 1;
                            break;
                        }
                    }
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
                U.waitSeconds(Const.INTERVEL * 1);
                U.dayin("在一片欢声笑语中,");
                U.waitSeconds(Const.INTERVEL * 3);
                U.dayin(caster.name + "打出了GG………");
                U.waitSeconds(Const.INTERVEL * 2);
                csk = new War1(caster, target);
                cast = false;
                caster.xl = -999;
                break;
            }
            for (Skill skill : caster.skillList)
                if ((mark.equals(skill.mark)) && (1 == skill.area)
                        && skill.cast)
                {
                    csk = skill;
                    csk.cishu++;
                    ult = false;
                    break;
                }
            for (Skill skill : caster.ultList)
                if ((mark.equals(skill.mark)) && (1 == skill.area)
                        && skill.cast)
                {
                    csk = skill;
                    ult = true;
                    for (Skill u : attacker.ultList)
                    {
                        u.area = 0;
                    }
                    break;
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

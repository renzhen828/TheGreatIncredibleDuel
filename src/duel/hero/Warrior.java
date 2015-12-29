package duel.hero;

import duel.Hero;
import duel.skill.warriorSkill.War1;
import duel.skill.warriorSkill.War2;
import duel.skill.warriorSkill.War3;
import duel.skill.warriorSkill.War4;
import duel.skill.warriorSkill.War5;
import duel.skill.warriorSkill.War6;
import duel.skill.warriorSkill.War7;
import duel.skill.warriorSkill.War8;
import duel.skill.warriorSkill.WarQ;
import duel.skill.warriorSkill.WarW;

public class Warrior extends Hero
{
    @Override
    public void initSkill(Hero target)
    {
        this.skillList.add(new War1(this, target));
        this.skillList.add(new War2(this, target));
        this.skillList.add(new War3(this, target));
        this.skillList.add(new War4(this, target));
        this.skillList.add(new War5(this, target));
        this.skillList.add(new War6(this, target));
        this.skillList.add(new War7(this, target));
        this.skillList.add(new War8(this, target));
    }

    @Override
    public void initUlt(Hero target)
    {
        this.ultList.add(new WarQ(this, target));
        this.ultList.add(new WarW(this, target));
        this.ultList.add(new WarQ(this, target));
    }
}

package duel;

public class Round
{
    Hero attacker, target;
    int num;


    public Round(Hero p1, Hero p2, int lastNum)
    {
        attacker = p1;
        target = p2;
        num = lastNum;
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
        int shanghai = attacker.attack(target);
        target.xl = target.xl - shanghai;
        U.showShangHai(target, shanghai);
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
        for (Buff buff : attacker.buffList)
        {
            buff.roundEndDo();
        }
        for (Buff buff : target.buffList)
        {
            buff.roundEndDo();
        }
    }
    

    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }
}

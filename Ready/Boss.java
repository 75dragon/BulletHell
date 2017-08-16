package Ready;

import java.util.Random;

public class Boss
{
    int X;
    int Y;
    int hp;
    int spd;
    int dif;
    int phases;
    int orgHp;
    World world;
    /**
     * 
     * @param X the x value of the spawn point
     * @param Y the y value of the spawn point
     * @param spd how fast the boss goes
     * @param dif difficulty - more bullets
     * @param hp base hitpoints
     * @param phases how many phases the boss has
     */
    public Boss(int X, int Y, int spd, int dif, int hp, int phases, World world)
    {
        this.X = X;
        this.Y = Y;
        this.spd = spd;
        this.dif = dif;
        this.hp = hp;
        this.phases = phases;
        this.orgHp = hp;
        this.world = world;
    }
    
    public void tick( long milliseconds )
    {
        
        X = (int)( X + 0 * milliseconds / 1000 );//spd = 0 for now
        checkHp();
    }
    
    public void checkHp()
    {
        if (hp <= 0)
        {
            if (phases > 0)
            {
                phases--;
                hp = (int)( orgHp * 1.1 );
                System.out.println( "ChangePhase" );
            }
            else
            {
               world.removeBoss();
            }
        }
    }
    
    public void takeHit(int dmg)
    {
        hp = hp - dmg;
        checkHp();
    }
}

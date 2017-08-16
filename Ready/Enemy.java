package Ready;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Enemy
{
    int health;

    int speed;

    int X;

    int Y;

    int difficulty;

    Movement move;

    FirePattern fire;

    int AICond;

    int fireCond;

    World world;

    Timer firepowerTimer;

    int hitboxRadius;

    int fireSpeed;


    /**
     * @param difficulty
     * @param x
     * @param y
     * @param speed
     * @param health
     * @param cond
     *            type of AI
     * @param world
     * @param hitboxRadius
     */
    public Enemy(
        int difficulty,
        int x,
        int y,
        int speed,
        int health,
        int AICond,
        int fireCond,
        World world,
        int hitboxRadius )
    {
        fireSpeed = ( 1000 - difficulty );
        this.difficulty = difficulty;
        this.X = x;
        this.Y = y;
        this.speed = speed;
        this.health = health;
        this.AICond = AICond;
        this.fireCond = fireCond;
        this.world = world;
        this.hitboxRadius = hitboxRadius;
        defineMovement( AICond );
        defineFire( fireCond );
        fire.fire( world );
    }


    public void defineMovement( int cond )
    {
        if ( cond == 0 )
        {
            move = new StillAI( this );
        }
        if ( cond == 1 )
        {
            move = new FollowAI( this );
        }
        if ( cond == 2 )
        {
            move = new RandomMovementAI( this );
        }
        if ( cond == 3)
        {
            move = new driftAI(this);
        }
    }


    public void defineFire( int cond )
    {
        if ( cond == 0 )
        {
            fire = new regularFire( this );
        }
        if ( cond == 1 )
        {
            fire = new randomFire( this );
        }
        if ( cond == 2 )
        {
            fire = new targetFire( this );
        }
        if ( cond == 3 )
        {
            fire = new strafeFire( this );
        }
    }


    public void tick( long milliseconds )
    {
        move.move( world, milliseconds );
    }


    public void takeHit( int damage )
    {
        health = health - damage;
        System.out.println( " enemy took " + damage + "damage" );
        System.out.println( health );
        checkHp();
    }


    public void checkHp()
    {
        if ( health <= 0 )
        {
            world.removeEnemy( this );
            System.out.println( "EnemyDeath" );
            firepowerTimer.stop();
        }
    }
}

package Ready;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Player
{
    int X; // x location

    int Y; // y location

    boolean focus;

    boolean fire;
    
    boolean canFire;//so i can stop it with 2 conditions
    
    boolean abilityCharging;

    int mana;
    
    int startingHp;

    int hp;

    int charge;// the value they charged to

    int xDirection;

    int yDirection;

    Timer abilityTimer;

    int playerSpeed = 200;

    int fireDelay = 250;

    int Abilitydelay = 1000;

    World world;

    boolean immune;

    Timer firepowerTimer;

    Timer immuneTimer;
    
    int hitboxSize;

    /**
     * Constructs a new player
     * @param x the spawnpoint of the player
     * @param y the spawnpoint of the player
     * @param hitpoints how much
     * @param world the world in
     */
    public Player( int x, int y, int hitpoints, World world )
    {
        this.X = x;
        this.Y = y;
        this.world = world;
        hp = hitpoints;
        startingHp = hitpoints;
        firePower();
        manaRegen();
        abilityCharging = false;
        immune = false;
        mana = 0;
        hitboxSize = 10;
        canFire = true;
    }

    /**
     * Players movement, check if focused
     * @param milliseconds just the caluclation of time
     */
    public void tick( long milliseconds )
    {
        if ( focus == false ) // move faster
        {
            Y += playerSpeed * milliseconds * yDirection / 1000;
            Y = Math.max( 50, Y );
            Y = Math.min( 950, Y );
            X += playerSpeed * milliseconds * xDirection / 1000;
            X = Math.max( 50, X );
            X = Math.min( 950, X );
        }
        else // half speed
        {
            Y += playerSpeed * milliseconds * yDirection / 2000;
            Y = Math.max( 0, Y );
            Y = Math.min( 950, Y );
            X += playerSpeed * milliseconds * xDirection / 2000;
            X = Math.max( 50, X );
            X = Math.min( 950, X );
        }
    }

    /**
     * Players should always regenerate 1 mana each 10 seconds
     */
    public void manaRegen()
    {

        Timer x = new Timer( 2000, new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                if ( mana < 10 )
                {
                    mana++;
                    System.out.println( "regained mana" + mana );
                }
            }
        } );
        x.start();

    }


    /**
     * Starts a ability charge, if you have more than 2 mana.
     */
    public void abilityCharge()
    {
        canFire = false;
        if ( mana > 2 )
        {
            if (abilityCharging == false)
            {
            abilityTimer = new Timer( Abilitydelay, new ActionListener()
            {

                @Override
                public void actionPerformed( ActionEvent e )
                {
                    if ( charge < 10 )
                    {
                        charge++;
                        System.out.println( "Charging!" + charge );
                    }
                }
            } );
            abilityTimer.start();
            abilityCharging = true;
            }
        }
        else
        {
            canFire = true;
            System.out.println( "oom" );
        }
        
    }


    /**
     * Stops the ability charge, and then takes the lower of mana or charge
     * value and applies the ability of the corrosponding value.
     */
    public void abilityUse()
    {
        if(abilityCharging == false)
        {
            return;
        }
        System.out.println( abilityTimer.isRunning() );
        if ( charge > mana )
        {
            System.out.println( "ability use: max mana!" );
            //does mana dmg / size
            Bullet fire = new Bullet(mana, X, Y, mana, mana, 270, 500, 0, 0, false, null, world);
            mana = 0;
            charge = 0;
            world.addBullet( fire );
        }
        else if ( charge > 3 )
        {
            System.out.println( "ability use: charge!" );
            //does charge dmg / size
            Bullet fire = new Bullet(charge, X, Y, charge, charge, 270, 500, 0, 0, false, null, world);
            mana = mana - charge;
            charge = 0;
            world.addBullet( fire );
        }
        else
        {
            System.out.println( "CHARGE MORE!!!" );
            charge = 0;
        }
        abilityTimer.stop();
        canFire = true;
        abilityCharging = false;
    }


    /**
     * Player's firepattern
     */
    public void firePower()
    {

        firepowerTimer = new Timer( fireDelay, new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                if ( fire == true && canFire == true )
                {
                    Bullet fire = new Bullet( 1, X, Y, 3, 3, 270, 500, 0, 0, false, null, world );
                    world.addBullet( fire );
                }
            }
        } );

        firepowerTimer.start();
    }


    /**
     * Player takes x damage if not immune
     * 
     * @param damage
     *            how much dmg
     */
    public void takeHit( int damage )
    {
        if ( immune == false )
        {
            hp = hp - damage;
            if ( hp <= 0 )
            {
                world.removePlayer( this );
                firepowerTimer.stop();
            }
            System.out.println( "Player took " + damage + " damage" );
            immune(1);
        }
    }


    /**
     * Player becomes immune for x seconds
     * 
     * @param x
     *            how many seconds you are immune for
     */
    public void immune( int x )
    {
        immune = true;
        System.out.println( "im immune" + x );
        immuneTimer = new Timer( x * 1000, new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                immune = false;
                System.out.println( "im not immune" );
                immuneTimer.stop();
            }
        } );
        immuneTimer.start();
    }


}

package Ready;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;


public class World
{
    // players
    ArrayList<Player> Players;

    // bullets
    ArrayList<Bullet> Bullets = new ArrayList<>();

    // boss
    Boss Boss;

    // Enemies
    ArrayList<Enemy> Enemies;

    int width;

    int height;

    Displayer dis;

    DisplayerRight disR;

    Timer timer;

    Timer spawn;

    int gameLength;

    Random rand;

    int fireCond;

    int AICond;

    int spawnX;

    int spawnY;

    World world;


    /**
     * @param x
     *            the X length of the window
     * @param y
     *            the Y length of the window
     * @param players
     *            number of players
     */
    public World( int x, int y, int players )
    {
        world = this;
        Enemies = new ArrayList<>();
        Bullets = new ArrayList<>();
        Players = new ArrayList<>();
        for ( int i = 0; i < players; i++ )
        {
            Players.add( new Player( 500, 500, 10, this ) );
            System.out.println( "player has been added: " + i );
        }
        gameLength = 0;
        spawnStuff();
    }


    /**
     * Adds a boss to the game
     */
    public void addBoss( Boss x )
    {
        Boss = x;
    }


    /**
     * Removes the boss from the game
     */
    public void removeBoss()
    {
        Boss = null;
    }


    public void addEnemy( Enemy x )
    {
        Enemies.add( x );
    }


    public void removeEnemy( Enemy x )
    {
        Enemies.remove( x );
    }


    public void addBullet( Bullet x )
    {
        Bullets.add( x );
    }


    public void removeBullet( Bullet x )
    {
        Bullets.remove( x );
    }


    public void removePlayer( Player x )
    {
        Players.remove( x );
        if ( Players.size() == 0 )
        {
            timer.stop();
            spawn.stop();
        }

    }


    public void runWorld()
    {
        long delay = 10;
        timer = new Timer( (int)delay, new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                tick( delay );
                dis.repaint();
                disR.repaint();
            }
        } );
        timer.start();
    }


    public void spawnStuff()
    {
        spawn = new Timer( 1000, new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                if ( Boss == null && gameLength % 5 == 0 )
                {
                    Random rand = new Random();
                    AICond = rand.nextInt( 4 );
                    fireCond = rand.nextInt( 4 );
                    spawnX = rand.nextInt( 400 ) + 300;
                    spawnY = rand.nextInt( 150 ) + 50;
                    addEnemy(
                        new Enemy( ( gameLength / 10 ) + 1, spawnX, spawnY, 100, 10, AICond, fireCond, world, 25 ) );
                }
                gameLength++;
            }
        } );
        spawn.start();
    }


    public void tick( long milliseconds )
    {
        for ( int x = 0; x < Bullets.size(); x++ )
        {
            if ( Bullets.get( x ) != null )
            {
                Bullets.get( x ).tick( milliseconds );
            }
        }
        if ( Boss != null )
        {
            Boss.tick( milliseconds );
        }
        for ( int x = 0; x < Players.size(); x++ )
        {
            if ( Players.get( x ) != null )
            {
                Players.get( x ).tick( milliseconds );
            }
        }
        for ( int x = 0; x < Enemies.size(); x++ )
        {
            if ( Enemies.get( x ) != null )
            {
                Enemies.get( x ).tick( milliseconds );
            }
        }
    }

}

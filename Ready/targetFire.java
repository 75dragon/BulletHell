package Ready;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;


public class targetFire implements FirePattern
{
    Random rand;

    int randPlayer;

    Enemy enemy;

    double Xvel;

    double Yvel;

    double angle;


    public targetFire( Enemy enemy )
    {
        this.enemy = enemy;
        rand = new Random();
        randPlayer = rand.nextInt( enemy.world.Players.size() );
        System.out.println( randPlayer );
    }


    @Override
    public void fire( World world )
    {
        enemy.firepowerTimer = new Timer( enemy.fireSpeed, new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                if ( world.Players.size() != 0 )
                {
                    while ( world.Players.size() < randPlayer + 1 )
                    {
                        randPlayer--;
                    }
                    if ( ( enemy.X - world.Players.get( randPlayer ).X ) == 0
                        && ( enemy.Y - world.Players.get( randPlayer ).Y ) == 0 )
                    {
                        Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 90, 500, 0, 0, true, null, world );
                        world.addBullet( fire );
                    }
                    else if ( ( enemy.X - world.Players.get( randPlayer ).X ) == 0 )
                    {
                        if ( enemy.Y - world.Players.get( randPlayer ).Y < 0 )
                        {
                            Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 90, 500, 0, 0, true, null, world );
                            world.addBullet( fire );
                        }
                        else
                        {
                            Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 270, 500, 0, 0, true, null, world );
                            world.addBullet( fire );
                        }
                    }
                    else if ( ( enemy.Y - world.Players.get( randPlayer ).Y ) == 0 )
                    {
                        if ( enemy.X - world.Players.get( randPlayer ).X < 0 )
                        {
                            Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 360, 500, 0, 0, true, null, world );
                            world.addBullet( fire );
                        }
                        else
                        {
                            Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 180, 500, 0, 0, true, null, world );
                            world.addBullet( fire );
                        }
                    }
                    else
                    {
                        Xvel = ( enemy.X - world.Players.get( randPlayer ).X );
                        Yvel = ( enemy.Y - world.Players.get( randPlayer ).Y );
                        angle = ( Math.toDegrees( Math.atan( Yvel / Xvel ) ) );
                        if(Xvel > 0)
                        {
                            angle = angle + 180;
                        }
                        Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, angle, 500, 0, 0, true, null, world );
                        world.addBullet( fire );
                    }
                }

            }
        } );

        enemy.firepowerTimer.start();

    }

}

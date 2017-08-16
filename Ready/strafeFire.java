package Ready;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;


public class strafeFire implements FirePattern
{
    Random rand;

    int randPlayer;

    Enemy enemy;

    double Xvel;

    double Yvel;

    double angle;
    
    double strafe;

    public strafeFire( Enemy enemy )
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
                        strafe = rand.nextDouble() * 20 - 10;
                        Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 90 + strafe, 500, 0, 0, true, null, world );
                        world.addBullet( fire );
                    }
                    else if ( ( enemy.X - world.Players.get( randPlayer ).X ) == 0 )
                    {
                        if ( enemy.Y - world.Players.get( randPlayer ).Y < 0 )
                        {
                            strafe = rand.nextDouble() * 20 - 10;
                            Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 90 + strafe, 500, 0, 0, true, null, world );
                            world.addBullet( fire );
                        }
                        else
                        {
                            strafe = rand.nextDouble() * 20 - 10;
                            Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 270 + strafe, 500, 0, 0, true, null, world );
                            world.addBullet( fire );
                        }
                    }
                    else if ( ( enemy.Y - world.Players.get( randPlayer ).Y ) == 0 )
                    {
                        if ( enemy.X - world.Players.get( randPlayer ).X < 0 )
                        {
                            strafe = rand.nextDouble() * 20 - 10;
                            Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 360 + strafe, 500, 0, 0, true, null, world );
                            world.addBullet( fire );
                        }
                        else
                        {
                            strafe = rand.nextDouble() * 20 - 10;
                            Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, 180 + strafe, 500, 0, 0, true, null, world );
                            world.addBullet( fire );
                        }
                    }
                    else
                    {
                        strafe = rand.nextDouble() * 20 - 10;
                        Xvel = ( enemy.X - world.Players.get( randPlayer ).X );
                        Yvel = ( enemy.Y - world.Players.get( randPlayer ).Y );
                        angle = ( Math.toDegrees( Math.atan( Yvel / Xvel ) ) );
                        if ( Xvel > 0 )
                        {
                            angle = angle + 180;
                        }
                        Bullet fire = new Bullet( 1, enemy.X, enemy.Y, 3, 3, angle + strafe, 500, 0, 0, true, null, world );
                        world.addBullet( fire );
                    }
                }

            }
        } );

        enemy.firepowerTimer.start();

    }
}

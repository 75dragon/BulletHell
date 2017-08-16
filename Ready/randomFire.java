package Ready;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class randomFire implements FirePattern
{
    Random rand;
    Enemy enemy;
    double fireAngle;
    public randomFire(Enemy enemy)
    {
        this.enemy = enemy;
        rand = new Random();
    }

    @Override
    public void fire( World world )
    {
        enemy.firepowerTimer = new Timer( enemy.fireSpeed / 10, new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                fireAngle = rand.nextDouble() * 360;
                Bullet fire = new Bullet(1, enemy.X, enemy.Y, 3, 3, fireAngle, 500, 0, 0, true, null, world);
                world.addBullet( fire );

            }
        } );

        enemy.firepowerTimer.start();
    }
}

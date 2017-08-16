package Ready;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class regularFire implements FirePattern
{
    Enemy enemy;
    public regularFire(Enemy enemy)
    {
        this.enemy = enemy;
    }
    @Override
    public void fire( World world )
    {
        enemy.firepowerTimer = new Timer( enemy.fireSpeed / 2, new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {

                Bullet fire = new Bullet(1, enemy.X, enemy.Y, 3, 3, 90, 500, 0, 0, true, null, world);
                world.addBullet( fire );

            }
        } );

        enemy.firepowerTimer.start();
    }

}

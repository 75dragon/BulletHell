package Ready;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;


public class driftAI implements Movement
{
    Enemy enemy;

    World world;

    Random rand;

    double Xrand;

    double Yrand;

    Timer drift;


    public driftAI( Enemy enemy )
    {
        this.enemy = enemy;
        rand = new Random();
        driftMove();
    }


    public void driftMove()
    {
        drift = new Timer( 2000, new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                if ( enemy == null )
                {
                    drift.stop();
                }
                Xrand = rand.nextDouble() * 3 - 1;
                System.out.println( Xrand );
                Yrand = rand.nextDouble() * 3 - 1;
                System.out.println( Yrand );
            }
        } );
        drift.start();
    }


    @Override
    public void move( World world, long milliseconds )
    {
        enemy.X = (int)( enemy.X + enemy.speed * milliseconds * Xrand / 1000 );
        enemy.Y = (int)( enemy.Y + enemy.speed * milliseconds * Yrand / 1000 );
    }
}

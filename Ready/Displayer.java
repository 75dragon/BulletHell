package Ready;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Displayer extends JPanel
{
    World world;

    int width;

    int height;

    Graphics g;

    Bullet bullet;

    Enemy enemy;

    Player player;
    
    Dimension x;


    public Displayer()
    {
        
    }


    public void addWorld( World w )
    {
        System.out.println( "do i get here" );
        world = w;
        width = 1000;
        height = 1000;
        x = new Dimension(width, height);
        world.dis = this;
        this.setSize( x );
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent( g );
        this.g = g;
        g.setColor( Color.white );
        g.fillRect( 0, 0, width, height );
        g.setColor( Color.black );
        if ( world.Boss != null )
        {
            drawBoss( world.Boss );
        }
        for ( int x = 0; x < world.Players.size(); x++ )
        {
            if ( ( player = world.Players.get( x ) ) != null )
            {
                drawPlayer( player );
            }
        }
        for ( int x = 0; x < world.Bullets.size(); x++ )
        {
            if ( ( bullet = world.Bullets.get( x ) ) != null )
            {
                drawBullet( bullet );
            }
        }
        for ( int x = 0; x < world.Enemies.size(); x++ )
        {
            if ( ( enemy = world.Enemies.get( x ) ) != null )
            {
                drawEnemy( enemy );
            }
        }
    }
    
    public void drawBoss(Boss dBoss)
    {
        int x = (int)( dBoss.X - 50 );
        int y = (int)( dBoss.Y - 50 );

        int diameter = 100;
        g.setColor( Color.red );
        g.fillOval( x, y, diameter, diameter );
    }
    
    public void drawPlayer(Player dPlayer)
    {
        int x = (int)( dPlayer.X - 25 );
        int y = (int)( dPlayer.Y - 25 );

        int diameter = 50;
        g.setColor( Color.blue );
        g.fillOval( x, y, diameter, diameter );
    }
    
    public void drawBullet(Bullet dBullet)
    {
        int radius = dBullet.radius;
        int x = (int)( dBullet.X - radius );
        int y = (int)( dBullet.Y - radius );

        int diameter = radius * 2;
        g.setColor( Color.black );
        g.fillOval( x, y, diameter, diameter );
    }
    
    public void drawEnemy(Enemy dEnemy)
    {
        int x = (int)( dEnemy.X - 50 );
        int y = (int)( dEnemy.Y - 50 );

        int diameter = 100;
        g.setColor( Color.orange );
        g.fillOval( x, y, diameter, diameter );
    }

}

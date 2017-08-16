package Ready;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DisplayerRight extends JPanel
{
    World world;
    int width;
    int height;
    Graphics g;
    Player player;
    public DisplayerRight()
    {
        
    }
    
    public void addWorld(World w)
    {
        System.out.println( "info time" );
        world = w;
        world.disR = this;
        this.setPreferredSize( new Dimension(300,1000) );
        width = 300;
        height = 1000;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent( g );
        this.g = g;
        g.setColor( Color.gray );
        g.fillRect( 0, 0, width, height );
        if (world.Players.size() != 0)
        {
            for ( int x = 0; x < world.Players.size(); x++)
            {
                if((player = world.Players.get( x )) != null)
                {
                    drawPlayerStats(player , x);
                }
            }
        }
    }
    
    public void drawPlayerStats(Player dPlayer, int x)
    {
        //Text
        g.setColor( Color.BLACK );
        g.drawString( "Player " + (x + 1) , 50, 200 * x + 25 );
        g.drawString( "Health", 50, 200 * x + 50 );
        g.drawString( "Mana", 50, 200 * x + 100 );
        g.drawString( "Charge", 50, 200 * x + 150);
        
        //DrawHpBar
        g.fillRect( 50, 200 * x + 60, 200, 10 );
        g.setColor( Color.RED );
        g.fillRect( 50, 200 * x + 60, (int)((dPlayer.hp * 1.0) / dPlayer.startingHp * 200), 10 );
        
        //DrawManaBar
        g.setColor( Color.BLACK );
        g.fillRect( 50, 200 * x + 110, 200, 10 );
        g.setColor( Color.BLUE );
        g.fillRect( 50, 200 * x + 110, (int)((dPlayer.mana * 1.0) / 10 * 200), 10);
        
        //DrawChargeBar
        g.setColor( Color.BLACK );
        g.fillRect( 50, 200 * x + 160, 200, 10 );
        g.setColor( Color.YELLOW );
        g.fillRect( 50, 200 * x + 160, (int)((dPlayer.charge * 1.0) / 10 * 200), 10 );
    }
    
    
}

package Ready;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener
{
    World world;
    public Listener()
    {        
        System.out.println( "new listenerrrrr" );
    }
    
    public void addWorld(World a)
    {
        world = a;
        System.out.println( "ADDED WORLD DOMINATION" );
    }

    @Override
    public void keyPressed( KeyEvent e )
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                world.Players.get( 0 ).yDirection = -1;
                break;
            case KeyEvent.VK_DOWN:
                world.Players.get(0).yDirection = 1;
                break;
            case KeyEvent.VK_LEFT:
                world.Players.get(0).xDirection = -1;
                break;
            case KeyEvent.VK_RIGHT:
                world.Players.get(0).xDirection = 1;
                break;
            case KeyEvent.VK_SHIFT:
                world.Players.get(0).focus = true;
                break;
            case KeyEvent.VK_Z:
                world.Players.get(0).fire = true;
                break;
            case KeyEvent.VK_X:
                world.Players.get(0).abilityCharge();
                break;
        }
        
    }

    @Override
    public void keyReleased( KeyEvent e )
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                world.Players.get(0).yDirection = 0;
                break;
            case KeyEvent.VK_DOWN:
                world.Players.get(0).yDirection = 0;
                break;
            case KeyEvent.VK_LEFT:
                world.Players.get(0).xDirection = 0;
                break;
            case KeyEvent.VK_RIGHT:
                world.Players.get(0).xDirection = 0;
                break;
            case KeyEvent.VK_SHIFT:
                world.Players.get(0).focus = false;
                break;
            case KeyEvent.VK_Z:
                world.Players.get(0).fire = false;
                break;
            case KeyEvent.VK_X:
                world.Players.get(0).abilityUse();
                break;
        }
        
    }

    @Override
    public void keyTyped( KeyEvent arg0 )
    {
        // TODO Auto-generated method stub
        
    }
    
}

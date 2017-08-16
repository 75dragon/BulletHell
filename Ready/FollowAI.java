package Ready;

import java.util.Random;

public class FollowAI implements Movement
{
    Enemy enemy;
    
    Random rand;
    int randPlayer;
    int Yvalue;
    int Xvalue;
    
    public FollowAI(Enemy enemy)
    {
        this.enemy = enemy;
        rand = new Random();
        System.out.println( enemy.world.Players.size() + "hello there" );
        randPlayer = rand.nextInt( enemy.world.Players.size());
        System.out.println( randPlayer );
    }

    @Override
    public void move( World world, long milliseconds )
    {
        if(world.Players.size() != 0)
        {
        while (world.Players.size() < randPlayer + 1 )
        {
            randPlayer--;
        }
        if(world.Players.get( randPlayer ).X > enemy.X)
        {
            Xvalue = 1;
        }
        else
        {
            Xvalue = -1;
        }
        if(world.Players.get( randPlayer ).Y > enemy.Y)
        {
            Yvalue = 1;
        }
        else
        {
            Yvalue = -1;
        }
        enemy.X = (int)( enemy.X + enemy.speed * milliseconds * Xvalue / 1000);
        enemy.Y = (int)( enemy.Y + enemy.speed * milliseconds * Yvalue / 1000);
        }
    }
}

package Ready;

import java.util.Random;

public class RandomMovementAI implements Movement
{
    Enemy enemy;
    World world;
    Random rand;
    int Xrand;
    int Yrand;
    
    public RandomMovementAI(Enemy enemy)
    {
        this.enemy = enemy;
        rand = new Random();
    }

    @Override
    public void move(World world, long milliseconds )
    {
        Xrand = rand.nextInt( 3 ) - 1;
        Yrand = rand.nextInt( 3 ) - 1;
        enemy.X = (int)( enemy.X + enemy.speed * milliseconds * Xrand / 1000);
        enemy.X = Math.max( enemy.X, 975 );
        enemy.X = Math.min( enemy.X, 25 );
        enemy.Y = (int)( enemy.Y + enemy.speed * milliseconds * Yrand / 1000);
        enemy.Y = Math.max( enemy.Y, 975 );
        enemy.Y = Math.min( enemy.Y, 25 );
    }
    
}

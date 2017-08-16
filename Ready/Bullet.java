package Ready;

import java.awt.Color;
import java.util.ArrayList;


public class Bullet
{
    int damage;

    double X;

    double Y;

    int radius; // size of bullet, since it can be larger than a hitbox

    int hitbox; // size of hitbox

    double velocityX;

    double velocityY;

    double degrees;
    
    int speed;

    int curveX;

    int curveY;

    boolean type; // enemy bullet or player bullet

    Color color;

    int lazer;

    Player[] players;

    ArrayList<Enemy> Enemies = new ArrayList<Enemy>();

    Boss Boss;

    World world;


    /**
     * @param damage damage
     * @param X X
     * @param Y Y
     * @param radius radius
     * @param hitbox hitbox
     * @param degrees degrees of bullet, 270 is down
     * @param speed speed of bullet
     * @param curveX curveX
     * @param curveY curveY
     * @param type type
     * @param color color
     * @param world world
     */
    public Bullet(
        int damage,
        int X,
        int Y,
        int radius,
        int hitbox,
        double degrees,
        int speed,
        int curveX,
        int curveY,
        boolean type,
        Color color,
        World world )
    {
        this.damage = damage;
        this.X = X;
        this.Y = Y;
        this.radius = radius;
        this.hitbox = hitbox;
        this.degrees = degrees;
//        this.velocityX = velocityX;
//        this.velocityY = velocityY;
        this.speed = speed;
        this.curveX = curveX;
        this.curveY = curveY;
        this.type = type;
        this.color = color;
//        this.lazer = lazer;
        this.world = world;
        change(degrees, speed);
    }
    
    public void change(double dgs, int spd)
    {
        velocityX = Math.cos( dgs / 180 * Math.PI ) * spd;
        velocityY = Math.sin( dgs / 180 * Math.PI ) * spd;
    }

    public void addPlayers( Player[] players )
    {
        this.players = players;
    }


    public void tick( long milliseconds )
    {
        X += velocityX * milliseconds / 1000;
        Y += velocityY * milliseconds / 1000;

        handleCollisions();
        offScreen();
    }


    public double getX()
    {
        return X;
    }


    public double getY()
    {
        return Y;
    }


    // TODO make sure this works with lazers :/

    public void handleCollisions()
    {
        if ( type == true ) // Its a enemy bullet, can hit players
        {
            for ( int x = 0; x < world.Players.size(); x++ )
            {
                if ( world.Players.get( x ) != null )
                {
                    // pythag therom, basically since it is a circle hitbox,
                    // take the difference of X player / bullet, same with y,
                    // and square
                    // em
                    // then take the hitbox distance n compare
                    if ( ( world.Players.get(x).X - X ) * ( world.Players.get(x).X - X ) + ( world.Players.get(x).Y - Y )
                        * ( world.Players.get(x).Y - Y ) < ( ( radius + world.Players.get(x).hitboxSize )
                            * ( radius + world.Players.get(x).hitboxSize ) ) )
                    {
                        world.Players.get(x).takeHit( damage );
                        // TODO check for lazer, if not remove
                    }
                }
            }
        }
        else // Its a player bullet, can only hit enemies
        {
            // its bob the builder again gdi
            for ( int x = 0; x < world.Enemies.size(); x++ )
            {
                if ( world.Enemies.get( x ) != null )
                {
                    if ( ( world.Enemies.get( x ).X - X ) * ( world.Enemies.get( x ).X - X )
                        + ( world.Enemies.get( x ).Y - Y )
                            * ( world.Enemies.get( x ).Y - Y ) < ( ( radius + world.Enemies.get( x ).hitboxRadius )
                                * ( radius + world.Enemies.get( x ).hitboxRadius ) ) )
                    {
                        world.Enemies.get( x ).takeHit( damage );
                        world.removeBullet( this );                        
                        return;
                    }
                }
            }
            if ( world.Boss != null )
            {
                if ( ( world.Boss.X - X ) * ( world.Boss.X - X )
                    + ( world.Boss.Y - Y ) * ( world.Boss.Y - Y ) < ( radius * radius ) )
                {
                    world.Boss.takeHit( damage );
                    // TODO check for lazer, if not remove
                }
            }

        }

    }


    public void offScreen()
    {
        if ( X < 0 || Y < 0 || X > 1000 || Y > 1000 )
        {
            world.removeBullet( this );
        }
    }
}

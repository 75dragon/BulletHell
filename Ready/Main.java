package Ready;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Main
{

    public static void main( String[] args )
    {
        World world = new World( 1000, 1000, 1 );
        Displayer dis = new Displayer();
        DisplayerRight disR = new DisplayerRight();
        Listener list = new Listener();
        BorderLayout layout = new BorderLayout();

        list.addWorld( world );
        dis.addWorld( world );
        disR.addWorld( world );
        // dis.addKeyListener( list );
        JFrame frame = new JFrame();
        frame.setSize( 1300, 1000 );
        frame.setLayout( layout );
        frame.add( dis, BorderLayout.CENTER );
        frame.add( disR, BorderLayout.EAST );
        frame.addKeyListener( list );

        frame.setVisible( true );

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setResizable( false );
        world.runWorld();
    }
}

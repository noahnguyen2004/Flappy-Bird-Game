package flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdGame implements ActionListener, MouseListener {     //to implements the ActionListener class in order to make Timer work.
    public static FlappyBirdGame flappybirdgame;

    protected final int width = 1200;

    protected final int height = 800;

    public int ticks;        //motion of the bird

    public int yMotion;      //motion of the bird

    public int birdscore;    //score of the user play

    public FlappyBirdJPanel flappybirdjpanel;

    public Rectangle flappybirdshape;   //flappybirdshape equals bird

    public Random birdrandom;

    public boolean gameOver, started;

    public ArrayList<Rectangle> columns;

    public FlappyBirdGame()
    {
        JFrame bird = new JFrame();
        Timer timer = new Timer(20, this);  //the Timer method has the delay and the listener.
        flappybirdjpanel = new FlappyBirdJPanel();
        birdrandom = new Random();



        bird.add(flappybirdjpanel);
        bird.setTitle("Flappy Bird");
        bird.setSize(width, height);
        bird.addMouseListener(this);
        bird.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) makes the game automatically close after the user quits.
        bird.setResizable(false);  //setResizable lets the game be resizable or not, in this case, it is not resizable since it is false.
        bird.setVisible(true);
        bird.setAlwaysOnTop(false);




        flappybirdshape = new Rectangle(width / 2 - 10, height / 2 - 10, 20, 20);   //x, y are equal to half of the width and height minus 10 for each x and y
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);    //to iterate the columns (obstacles of the game)




        timer.start();



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;
        int speed = 10;//speed of the object during the game.
        if (started) {
            for (int index = 0; index < columns.size(); index++) {
                Rectangle column = columns.get(index);
                column.x -= speed;
            }


            if (ticks % 2 == 0 && yMotion < 15)
            {
                yMotion += 2;
            }

            for (int index = 0; index < columns.size(); index++) {
                Rectangle column = columns.get(index);

                if (column.x + column.width < 0) {
                    columns.remove(column);

                    if (column.y == 0) {
                        addColumn(false);   //the obstacles will be infinite (never stops)
                    }
                }
            }
            flappybirdshape.y += yMotion;

            for (Rectangle column : columns) {
                if (column.y == 0 && flappybirdshape.x + flappybirdshape.width / 2 > column.x + column.width / 2 - 10 && flappybirdshape.x + flappybirdshape.width / 2 < column.x + column.width / 2 + 10)
                {
                    birdscore++;
                }
                if (column.intersects(flappybirdshape))     //the condition which the game will be over
                {
                    gameOver = true;

                    flappybirdshape.x = column.x - flappybirdshape.width;
                }
            }

            if (flappybirdshape.y > height - 120 || flappybirdshape.y < 0)      //the condition which the game will be over
            {
                gameOver = true;
            }

            if (flappybirdshape.y + yMotion >= height - 120)        //the condition which when the bird collapses with the obstacle, game is over.
            {
                flappybirdshape.y = height - 120;
            }
        }

        flappybirdjpanel.repaint();

    }

    public void addColumn(boolean birdboolean)  //bird boolean
    {
        int space = 300;
        int widthcolumn = 100;  //width column equals width in the video
        int heightcolumn = 50 + birdrandom.nextInt(300);   //to generate a random height for the object at the beginning of the game and set the bound to 300
        //height column of 50 is the minimum height of the object at the beginning of the game
        //the random height of the height column is the random height and size of the obstacles
        //height column equals height in the video
        //the columns are the obstacles of the flappy bird game

       if (birdboolean) {
           columns.add(new Rectangle(width + widthcolumn + columns.size() * 300, height - heightcolumn - 120, widthcolumn, heightcolumn));
           columns.add(new Rectangle(width + widthcolumn + (columns.size() - 1) * 300, 0, widthcolumn, height - heightcolumn - space));
       }
       else
       {
           columns.add(new Rectangle(columns.get(columns.size() -1).x + 600, height - heightcolumn - 120, widthcolumn, heightcolumn));
           columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, widthcolumn, height - heightcolumn - space));



       }


    }

    public void paintColumn(Graphics g, Rectangle column){
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);

    }

    public void birdJump()
    {
        if (gameOver)      //if the game is over
        {
            flappybirdshape = new Rectangle(width / 2 - 10, height / 2 - 10, 20, 20);   //x, y are equal to half of the width and height minus 10 for each x and y
            columns.clear();
            yMotion = 0;
            birdscore = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);    //to iterate the columns (obstacles of the game)
            gameOver = false;
        }

        if (!started)      //if the game is not started yet
        {
            started = true;
        }

        else if (!gameOver)
        {
            if (yMotion > 0)
            {
                yMotion = 0;
            }
            yMotion -= 10;
        }
    }

    public void repaint(Graphics g) {
        g.setColor(Color.darkGray);     //to set the background color for graphic into Cyan.
        g.fillRect(0, 0, width, height);

        g.setColor(Color.black);
        g.fillRect(0, height-120, width, 120);

        g.setColor(Color.green);
        g.fillRect(0, height-120, width, 20);

        g.setColor(Color.red);
        g.fillRect(flappybirdshape.x, flappybirdshape.y, flappybirdshape.width, flappybirdshape.height);

        for (Rectangle column : columns)      //for-each loop
        {
            paintColumn(g, column);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 50));

        if (!started) {
            g.drawString("Nguyen Viet Khoi (Noah) click to start", 75, height / 2 - 50);
            //the position of the object (bird) at the beginning of the game, or after game over (reset).
        }

        if (gameOver) {
            g.drawString("Game Over :)", 100, height / 2 - 50);     //the position of the object (bird) at the beginning of the game, or after game over (reset).
        }

        if (!gameOver && started)       //the score will be counted when the game starts and after the game is over for a new game
        {
            g.drawString(String.valueOf(birdscore), width / 2 - 25, 100);
        }

    }


    public static void main(String[]args)
    {
        flappybirdgame = new FlappyBirdGame();

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        birdJump();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

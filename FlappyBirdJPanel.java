package flappybird;

import javax.swing.*;
import java.awt.*;

public class FlappyBirdJPanel extends JPanel {
    public static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    //paintComponent makes the component paint
        FlappyBirdGame.flappybirdgame.repaint(g);   //makes the graphics g repaint
    }
}

package flappybird;

import java.awt.*;
import javax.swing.*;

class colorgraphics extends JPanel  {
    public void setColor(Graphics graphics)
    {
        graphics.setColor (Color.red);
        graphics.drawRect (0,0,100,100);
        graphics.clipRect (25, 25, 50, 50);
        graphics.drawLine (0,100,100,0);
    }
}

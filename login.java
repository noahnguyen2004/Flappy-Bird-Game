package flappybird;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
//invoke when the action happens (begins)

public class login implements ActionListener  {
    private static JLabel usernamelabel;
    private static JTextField usertext;
    private static JLabel passwordlabel;
    private static JPasswordField userpassword;
    private static JButton button;
    private static JLabel userlogin;
    private static JFrame frame;

    public JPanel setbackgroundcolor()
    {
        JPanel framecolor = new JPanel();
        framecolor.setBackground(Color.PINK);
        frame.add(framecolor);
        return framecolor;
    }

    public void setColor(Graphics graphics)
    {
        graphics.setColor (Color.red);
        graphics.drawRect (0,0,100,100);
        graphics.clipRect (25, 25, 50, 50);
        graphics.drawLine (0,100,100,0);
    }
    public static void main(String[] args) {

        final int width = 400;
        final int height = 250;
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Login");
        Container contain = frame.getContentPane();
        contain.setBackground(Color.cyan);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);




        panel.setLayout(null); //set the layout to null

        //create a new label called Username
        usernamelabel = new JLabel("Username");
        usernamelabel.setBounds(10, 20, 80, 25);
        panel.add(usernamelabel);

        //create a text box for user text
        usertext = new JTextField();
        usertext.setBounds(100, 20, 165, 25);
        panel.add(usertext);

        //create a new label called Password
        passwordlabel = new JLabel("Password");
        passwordlabel.setBounds(10, 50, 80, 25);
        panel.add(passwordlabel);

        //create a new text field for user password
        userpassword = new JPasswordField();
        userpassword.setBounds(100, 50, 165, 25);
        panel.add(userpassword);

        //create a new button for the login window
        button = new JButton("Enter");
        button.setBounds(120, 100, 80, 25);
        button.addActionListener(new login());  //to add the login class to action listener method
        panel.add(button);

        //create a label under the Enter button
        userlogin = new JLabel();
        userlogin.setBounds(110, 120, 300, 25);
        panel.add(userlogin);

        frame.setVisible(true);




    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String userlogintext = usertext.getText();
        String userloginpass = userpassword.getText();

        //to print the password that the user enters on the screen
        if (userlogintext.equals("noah") && userloginpass.equals("noahnguyenlogin123"))
        {
            userlogin.setText("Login successfully! Enjoy the game :)");
        }

        else if (userlogintext.equals(""))
            userlogin.setText("Please enter Username");
        else if (userloginpass.equals(""))
            userlogin.setText("Please enter Password");
        else
            userlogin.setText("Login failed, please re-enter");




    }
}


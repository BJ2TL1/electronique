/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.KeyStroke;

public class KeyBinding {
    GUI window = null;

    private int valueToSend = 0;
   // private int rightThrottle = 0;

    private static final int SPEED_INCREMENT = 5;

    private static char send = 'q';
    //private static char leftDecel = 'a';
    private static char receive = 'e';
    //private static char rightDecel = 'd';
    //private static char bothAccel = 'w';
    //private static char bothDecel = 's';

    public KeyBinding(GUI window)
    {
        this.window = window;
    }

    public void bindKeys()
    {
        //set input maps so that the program can read key bindings
        //putting something in the input map means to assign a key to an action name
        //action name is associated with a method in the action map
        window.btnSend.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(send), "accelerateLeft");
        window.btnSend.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(send)), "accelerateLeft");
        window.btnSend.getActionMap().put("accelerateLeft", sending);

        //window.txtbox1.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(leftDecel), "decelerateLeft");
        //window.txtbox1.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(leftDecel)), "decelerateLeft");
        //window.txtbox1.getActionMap().put("decelerateLeft", decelerateLeft);

        window.btnReceive.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(receive), "accelerateRight");
        window.btnReceive.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(receive)), "accelerateRight");
        window.btnReceive.getActionMap().put("accelerateRight", receiving);

       /* window.txtbox2.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(rightDecel), "decelerateRight");
        window.txtbox2.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(rightDecel)), "decelerateRight");
        window.txtbox2.getActionMap().put("decelerateRight", decelerateRight);

        //just attach to the left button, since theres no button for both
        window.btnSend.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(bothAccel), "accelerateBoth");
        window.btnSend.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(bothAccel)), "accelerateBoth");
        window.btnSend.getActionMap().put("accelerateBoth", accelerateBoth);

        //just attach to the left button, since theres no button for both
        window.txtbox1.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(bothDecel), "decelerateBoth");
        window.txtbox1.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Character.toUpperCase(bothDecel)), "decelerateBoth");
        window.txtbox1.getActionMap().put("decelerateBoth", decelerateBoth);*/
    }

    public void toggleControls()
    {
        if (window.communicator.getConnected() == true)
        {
            window.btnSend.setEnabled(true);
            window.txtbox1.setEnabled(true);
            window.btnReceive.setEnabled(true);
            window.txtbox2.setEnabled(true);

            window.btnDisconnect.setEnabled(true);
            window.btnConnect.setEnabled(false);
            window.cboxPorts.setEnabled(false);
        }
        else
        {
            window.btnSend.setEnabled(false);
            window.txtbox1.setEnabled(false);
            window.btnReceive.setEnabled(false);
            window.txtbox2.setEnabled(false);

            window.btnDisconnect.setEnabled(false);
            window.btnConnect.setEnabled(true);
            window.cboxPorts.setEnabled(true);
        }
    }

    //defining the action
    Action sending = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            /*leftThrottle = accelerate(leftThrottle);
            updateLabels();*/
        		valueToSend = getTextToSend();
        }
    };

   /* Action receiving = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            leftThrottle = decelerate(leftThrottle);
            updateLabels();
        }
    };*/

    Action receiving = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            /*rightThrottle = accelerate(rightThrottle);
            updateLabels();*/
        }
    };

   /* Action decelerateRight = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            rightThrottle = decelerate(rightThrottle);
            updateLabels();
        }
    };*/

   /* Action accelerateBoth = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            leftThrottle = accelerate(leftThrottle);
            rightThrottle = accelerate(rightThrottle);
            updateLabels();
        }
    };

    Action decelerateBoth = new AbstractAction()
    {
        public void actionPerformed(ActionEvent evt)
        {
            leftThrottle = decelerate(leftThrottle);
            rightThrottle = decelerate(rightThrottle);
            updateLabels();
        }
    };*/

   /* public void updateLabels()
    {
        window.lblLeft.setText(String.valueOf(leftThrottle));
        window.lblRight.setText(String.valueOf(rightThrottle));

        window.communicator.writeData(leftThrottle, rightThrottle);
    }*/

   /* public int accelerate(int throttle)
    {
        if (throttle < 100)
        {
            throttle += SPEED_INCREMENT;
        }
        return throttle;
    }

    public int decelerate(int throttle)
    {
        if (throttle > 0)
        {
            throttle -= SPEED_INCREMENT;
        }
        return throttle;
    }*/

    final public int getTextToSend()
    {
        return Integer.parseInt(window.txtbox1.getText());
    }

   /* public void setLeftThrottle(int value)
    {
        leftThrottle = value;
    }

    final public int getRightThrottle()
    {
        return rightThrottle;
    }

    public void setRightThrottle(int value)
    {
        rightThrottle = value;
    }*/
}

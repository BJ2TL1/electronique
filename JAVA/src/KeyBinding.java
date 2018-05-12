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

    private static int valueToSend;
    private static char send = 'q';

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
        		getTextToSend();
        }
    };


    final public int getTextToSend()
    {
    		valueToSend = Integer.parseInt(window.txtbox1.getText()); 
        return valueToSend;
    }

}

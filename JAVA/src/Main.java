import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;

import com.fazecast.jSerialComm.*;

public class Main extends JFrame implements ActionListener{
	public JButton button1;
	public JButton button2;
	public JTextField text1;
	public double d1;
	String s;
	SerialPort[] ports;
	SerialPort serialPort;
	Box bv;
	Box bh1;
	Box bh2;
	
	public void ask(SerialPort[] values)
	{
		String result = null;

		JPanel panel = new JPanel();
		panel.add(new JLabel("Please make a selection:"));
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		for (SerialPort value : values) {
			model.addElement(value.getSystemPortName());
		}
		JComboBox comboBox = new JComboBox(model);
		panel.add(comboBox);

		int iResult = JOptionPane.showConfirmDialog(null, panel, "Port", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		switch (iResult) {
		case JOptionPane.OK_OPTION:
			result = (String) comboBox.getSelectedItem();
			break;
		}

        s= result;

	}
	
	public void actionPerformed(ActionEvent e) {
	    if ("send".equals(e.getActionCommand())) {
	    	sendData(Integer.parseInt(text1.getText()));
	    } else {
	        d1 = getData();
	    }
	}  
	
	public void gui()
	{
	//	JPanel panel = new JPanel();
		button1 = new JButton("send");
		button1.setActionCommand("send");
		button1.addActionListener(this);
		button2 = new JButton("receive");
		button2.setActionCommand("receive");
		button2.addActionListener(this);
		text1 = new JTextField();
		bv = Box.createVerticalBox();
		bh1 = Box.createHorizontalBox();
		bh1.add(button1);
		bh1.add(button2);
		bh2 = Box.createHorizontalBox();
		bh2.add(text1);
		
		bv.add(bh1);
		bv.add(bh2);
		
		
	//	panel.add(text1, BorderLayout.NORTH);
	//	panel.add(button1, BorderLayout.CENTER);
	//	panel.add(button2, BorderLayout.CENTER);
		
		
	//	this.add(panel);
		this.add(bv);
		this.setTitle("Electronique");
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	

	
	public void sendData(double d)
	{
		PrintWriter output = new PrintWriter(serialPort.getOutputStream());
		while(true) {
			output.print(d);
			output.flush();
		}
	}
	
	public double getData()
	{
		double d = 0.0;
		Scanner data = new Scanner(serialPort.getInputStream());
		while(data.hasNextLine()){
			try{d = Double.parseDouble(data.nextLine());}catch(Exception e){}
			String temp = Double.toString(d);
			text1.setText(temp);
		}
		return d;
	}

	public static void main(String[] args) {


		Main m = new Main();
		m.gui();
		m.ports = SerialPort.getCommPorts();
		m.ask(m.ports);
		int i;
		for(i = 0; i< (m.ports).length; i++)
		{
			if(m.s.equals(m.ports[i].getSystemPortName())==true)
			{
				break;
			}
		}
		int chosenPort = i;

		m.serialPort = m.ports[chosenPort];
		if(m.serialPort.openPort())
			System.out.println("Port opened successfully.");
		else {
			System.out.println("Unable to open the port.");
			return;
		}
		//serialPort.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
		m.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);

		//System.out.println("Done.");
	}

}

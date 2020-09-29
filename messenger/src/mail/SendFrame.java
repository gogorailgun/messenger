package mail;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mail.*;

public class SendFrame {
	JFrame wFr;
	JLabel lbl;
	JTextArea area;
	JButton sendB, exitB;
	JTextField field;
	JPanel p1;
	
	public SendFrame(Mail main) {
		wFr = new JFrame("메일 전송");
		wFr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				wFr.setVisible(false);
				main.fr.setVisible(true);
			}
		});
		
		
		area = new JTextArea();
		
		sendB = new JButton("답장"); 
		exitB = new JButton("닫기"); 
		
		
		lbl = new JLabel("받는 사람 : ");
		field = new JTextField();
		// 고치지 못하게
		field.setEditable(false);
		
		p1 = new JPanel(new BorderLayout());
		p1.add("West",lbl);
		p1.add("Center",field);
		
		wFr.add(p1, BorderLayout.NORTH);
		wFr.add(area, BorderLayout.CENTER);
		
		
		wFr.setSize(new Dimension(250,250));
		wFr.setVisible(true);
		wFr.setResizable(false);
	}
	
	

}

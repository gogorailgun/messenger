package mail;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mail.*;

public class ReceiveFrame {
	Mail main;
	JFrame rFr;
	public JTextField field;
	public JTextArea area;
	JButton replyB, exitB;
	JLabel lbl;
	JPanel nameP, btnP;
	
	public ReceiveFrame(Mail main) {
		this.main = main;
		rFr = new JFrame("메일창");
		rFr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				rFr.setVisible(false);
			}
		});
		// 라벨 필드 추가
		lbl = new JLabel("보낼 사람 : ");
		field = new JTextField();
		field.setEditable(false);
		
//		String msg = main.msgList
		
		// 버튼 추가
		replyB = new JButton("답장");
		exitB = new JButton("닫기");
		
		// 버튼 이벤트 추가
		replyB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		exitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rFr.setVisible(false);
			}
		});
		
		nameP = new JPanel(new BorderLayout());
		nameP.add("West",lbl);
		nameP.add("East",field);
		
		btnP = new JPanel(new GridLayout(1,2));
		btnP.add(replyB);
		btnP.add(exitB);
		
		area = new JTextArea();
		area.setEditable(false);
		
		rFr.add(nameP,BorderLayout.NORTH);
		rFr.add(area, BorderLayout.CENTER);
		rFr.add(btnP,BorderLayout.SOUTH);
		
		
		
		
		
		
		rFr.setSize(new Dimension(250,250));
		rFr.setVisible(true);
		rFr.setResizable(false);
	}

}

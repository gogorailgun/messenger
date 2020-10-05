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
	String sid;
	
	public ReceiveFrame(Mail main, String sid) {
		this.main = main;
		rFr = new JFrame("메일창");
		rFr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				rFr.setVisible(false);
			}
		});
		// 라벨 필드 추가
		String id = sid.substring(0, sid.indexOf(':'));
		lbl = new JLabel("보낸 사람 : " + id);
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
				ReceiveFrame.this.rFr.setVisible(false);
				SendFrame sFr = new SendFrame(main);
				sFr.field.setText(id);
				sFr.wFr.setVisible(true);
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
		
		area = new JTextArea(sid.substring(sid.indexOf(':') + 1));
		area.setEditable(false);
		
		rFr.add(nameP,BorderLayout.NORTH);
		rFr.add(area, BorderLayout.CENTER);
		rFr.add(btnP,BorderLayout.SOUTH);
		
		
		
		
		
		
		rFr.setSize(new Dimension(250,250));
		rFr.setResizable(false);
	}

}

package mail;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mail.*;

public class SendFrame {
	BtnEvent evt;
	
	public String sid;
	
	Mail main;
	JFrame wFr;
	JLabel lbl;
	JTextArea area;
	JButton sendB, exitB;
	JTextField field;
	JPanel nameP, btnP;
	
	public SendFrame(Mail main) {
		this.main = main;
		wFr = new JFrame("메일 전송");
		wFr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				wFr.setVisible(false);
			}
		});
		
		
		
		
		sid = (String) main.idJList.getSelectedValue();
		
		// 라벨 필드 추가후 nameP에 추가
		lbl = new JLabel("받는 사람 : ");
		field = new JTextField(sid);
		// 고치지 못하게
		field.setEditable(false);
		
		// 판넬 추가
		nameP = new JPanel(new BorderLayout());
		nameP.add("West",lbl);
		nameP.add("Center",field);
		
		// 버튼 추가
		sendB = new JButton("보내기"); 
		exitB = new JButton("닫기"); 
		
		btnP = new JPanel(new GridLayout(1,2));
		btnP.add(sendB);
		btnP.add(exitB);
		
		evt = new BtnEvent(this);
		// 버튼 이벤트 추가
		sendB.addActionListener(evt);
		
		exitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wFr.setVisible(false);
			}
		});
		
		area = new JTextArea();
		
		
		//프레임에 설정
		wFr.add(nameP, BorderLayout.NORTH);
		wFr.add(area, BorderLayout.CENTER);
		wFr.add(btnP, BorderLayout.SOUTH);
		
		wFr.setSize(new Dimension(250,250));
		wFr.setResizable(false);
	}
	
	

}

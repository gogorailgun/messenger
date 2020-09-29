package mail;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

import mail.*;

public class Mail {
	// ip와 id가 같이 담겨있는 Properties
	public Properties idToIp, ipToId;
	
	// 메인 화면 리스트
	JFrame fr ;
	JPanel pan1 , pan2, pan3;
	JButton btn1 , btn2 , btn3;
	JList list1, list2;
	JScrollPane scroll1, scroll2;
	JLabel lbl1, lbl2;
	
	// UDP 통신을 할 DatagramSocket 만들기
	public DatagramSocket sSocket, rSocket;
	
	public Mail() {
		setUI();
	}
	
	public void setUI() {
		fr = new JFrame("*** 메일함 ***");
		fr.addWindowListener(new WindowAdapter() {
			public void windowColsing(WindowEvent e) {
				close();
				System.exit(0);
			}
		});
		
		// 판넬 추가
		pan1 = new JPanel(new BorderLayout());
		pan2 = new JPanel(new BorderLayout());
		pan3 = new JPanel(new GridLayout(1,3));
		
		pan1.setSize(new Dimension(245,230));
		pan2.setSize(new Dimension(245,230));
		pan3.setSize(new Dimension(30,500));
		
		// list 추가
		list1 = new JList();
		list2 = new JList();
		
		// label 추가
		lbl1 = new JLabel("회원정보");
		lbl2 = new JLabel("메일함");
		
		list1.setSize(new Dimension(240,230));
		list2.setSize(new Dimension(240,230));
		
		pan1.add(list1);
		pan1.add(lbl1,BorderLayout.NORTH);
		pan2.add(list2);
		pan2.add(lbl2,BorderLayout.NORTH);
		
		// 버튼 추가
		btn1 = new JButton("보내기");
		btn2 = new JButton("보기");
		btn3 = new JButton("닫기");
		
		// 버튼 이벤트 추가
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SendFrame sFr = new SendFrame(Mail.this);
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReceiveFrame rFr = new ReceiveFrame(Mail.this);
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				System.exit(0);
			}
		});
		
		pan3.add(btn1);
		pan3.add(btn2);
		pan3.add(btn3);
		
		
		// frame 설정
		fr.add(pan1, BorderLayout.WEST);
		fr.add(pan2, BorderLayout.EAST);
		fr.add(pan3, BorderLayout.SOUTH);
		
		fr.setSize(500, 500);
		fr.setVisible(true);
		fr.setResizable(false);
	}
	
	public void close() {
		try {
			sSocket.close();
			rSocket.close();
		} catch(Exception e) {}
	}

	public static void main(String[] args) {
		new Mail();
	}

}

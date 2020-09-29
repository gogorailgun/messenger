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
	JPanel panIdList , panMsgList, panBtn;
	JButton btnSend , btnShowMsg , btnClose;
	JList idList, MsgList;
	JScrollPane scroll1, scroll2;
	JLabel lblIdList, lblMsgList;
	
	// UDP 통신을 할 DatagramSocket 만들기
	public DatagramSocket sSocket, rSocket;
	
	public Mail() {
		setUI();
	}
	
	public void setUI() {
		fr = new JFrame("*** 메일함 ***");
		fr.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
				System.exit(0);
			}

		});
		
		// 판넬 추가
		JPanel pan = new JPanel(new GridLayout(1,2));
		panIdList = new JPanel(new BorderLayout());
		panMsgList = new JPanel(new BorderLayout());
		panBtn = new JPanel(new GridLayout(1,3));
		
		panIdList.setSize(new Dimension(245,230));
		panMsgList.setSize(new Dimension(245,230));
		panBtn.setSize(new Dimension(30,500));
		
		// list 추가
		idList = new JList();
		MsgList = new JList();
		
		// label 추가
		lblIdList = new JLabel("회원정보");
		lblIdList.setHorizontalAlignment(JLabel.CENTER);
		lblMsgList = new JLabel("메일함");
		lblMsgList.setHorizontalAlignment(JLabel.CENTER);
		
		idList.setSize(new Dimension(240,230));
		MsgList.setSize(new Dimension(240,230));
		
		panIdList.add(idList);
		panIdList.add(lblIdList,BorderLayout.NORTH);
		panMsgList.add(MsgList);
		panMsgList.add(lblMsgList,BorderLayout.NORTH);
		
		// 버튼 추가
		btnSend = new JButton("보내기");
		btnShowMsg = new JButton("보기");
		btnClose = new JButton("닫기");
		
		// 버튼 이벤트 추가
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SendFrame sFr = new SendFrame(Mail.this);
			}
		});
		
		btnShowMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReceiveFrame rFr = new ReceiveFrame(Mail.this);
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
				System.exit(0);
			}
		});
		
		panBtn.add(btnSend);
		panBtn.add(btnShowMsg);
		panBtn.add(btnClose);
		
		
		// frame 설정
		pan.add(panIdList, BorderLayout.WEST);
		pan.add(panMsgList, BorderLayout.EAST);
		fr.add(pan, BorderLayout.CENTER);
		fr.add(panBtn, BorderLayout.SOUTH);
		
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

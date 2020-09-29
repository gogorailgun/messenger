package mail;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.DatagramSocket;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.IdIpDAO;
import mail.thread.MailThread;
import vo.IdIpVO;


public class Mail {
	// ip와 id가 같이 담겨있는 Properties
	public Properties idToIp, ipToId;
	
	// 메인 화면 리스트
	JFrame fr ;
	JPanel panIdList , panMsgList, panBtn;
	JButton btnSend , btnShowMsg , btnClose;
	JScrollPane scroll1, scroll2;
	JLabel lblIdList, lblMsgList;
	public JList idJList, msgJList;
	
	
	public Vector<IdIpVO> voList;
	public Vector<String> idList;
	public Vector<String> msgList;
	
	// UDP 통신을 할 DatagramSocket 만들기
	public DatagramSocket sSocket, rSocket;
	
	
	public Mail() {
		setNetwork();
		setUI();
	}
	
	public void setNetwork() {
		try {
			sSocket = new DatagramSocket();
			rSocket = new DatagramSocket(7799);
			
			MailThread mthread = new MailThread(this);
			mthread.start();
			
		} catch(Exception e) {
			e.printStackTrace();
			close();
			System.exit(0);
		}
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
		// Vector 추가
		
		// 판넬 추가
		JPanel pan = new JPanel(new GridLayout(1,2));
		panIdList = new JPanel(new BorderLayout());
		panMsgList = new JPanel(new BorderLayout());
		panBtn = new JPanel(new GridLayout(1,3));
		
		panIdList.setSize(new Dimension(245,230));
		panMsgList.setSize(new Dimension(245,230));
		panBtn.setSize(new Dimension(30,500));
		
		// list 추가
		IdIpDAO dao = new IdIpDAO();
		voList = dao.selectIdList();
		idList = new Vector<String>();
		for (IdIpVO vo : voList) {
			idList.add(vo.getId());
		}
		idJList = new JList(idList);
		msgList = new Vector<String>();
		msgJList = new JList(msgList);
		
		// label 추가
		lblIdList = new JLabel("회원정보");
		lblIdList.setHorizontalAlignment(JLabel.CENTER);
		lblMsgList = new JLabel("메일함");
		lblMsgList.setHorizontalAlignment(JLabel.CENTER);
		
		idJList.setSize(new Dimension(240,230));
		msgJList.setSize(new Dimension(240,230));
		
		panIdList.add(idJList);
		panIdList.add(lblIdList,BorderLayout.NORTH);
		panMsgList.add(msgJList);
		panMsgList.add(lblMsgList,BorderLayout.NORTH);
		
		// 버튼 추가
		btnSend = new JButton("보내기");
		btnShowMsg = new JButton("보기");
		btnClose = new JButton("닫기");
		
		// 버튼 이벤트 추가
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sid = (String) idJList.getSelectedValue();
				
				if(sid == null) {
					JOptionPane.showMessageDialog(null, "*** 아이디를 먼저 선택하세요! ***");
					return;
				}
				SendFrame sFr = new SendFrame(Mail.this);
			}
		});
		
		btnShowMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sid = (String) msgJList.getSelectedValue();
				
				if(sid == null) {
					JOptionPane.showMessageDialog(null, "*** 아이디를 먼저 선택하세요! ***");
					return;
				}
				
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

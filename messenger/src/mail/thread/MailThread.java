package mail.thread;

import java.net.*;
import java.io.*;
import javax.swing.*;

import dao.IdIpDAO;
import mail.*;
import vo.IdIpVO;

public class MailThread extends Thread {
	Mail main;
	ReceiveFrame rFr;
	public MailThread(Mail main) {
		this.main = main;
	}
	
	public void receiveProc(DatagramPacket pack) {
		// 패킷에서 온 데이터와 ip를 받아서 저장한다.
		byte[] buff = pack.getData();
		InetAddress inet = pack.getAddress();
		
		String msg = new String(buff,0,buff.length);
		String ip = inet.getHostAddress();
		IdIpDAO dao = new IdIpDAO();
//		rFr.area = new JTextArea(msg);
		IdIpVO id = dao.getIdByIp(ip);
//		rFr.field = new JTextField(id);
		
//		String data = new String(buff);
//		String id = "";
//		for (int i = 0; i < buff.length; i++) {
//			char ch = data.charAt(i);
//			if (ch == ':') {
//				break;
//			}
//			id += ch;
//		}
		
		main.msgList.add(id.getId() + ":" + msg);
		main.msgJList.setListData(main.msgList);
		
		
		
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				byte[] buff = new byte[10240];
				DatagramPacket pack = new DatagramPacket(buff,10240); 
				// receive는 블로킹 함수로 실행이 되면 그 다음으로 넘어간다.
				main.rSocket.receive(pack);
				// try 안에서 실행
				receiveProc(pack);
			}
		} catch(Exception e) {
		} 
		
	}
	
}

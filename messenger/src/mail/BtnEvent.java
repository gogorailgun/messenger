package mail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.InetAddress;

import javax.swing.JButton;

import dao.IdIpDAO;
import vo.IdIpVO;
public class BtnEvent implements ActionListener {
	SendFrame sFr;

	public BtnEvent(SendFrame sFr) {
		this.sFr = sFr;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 할일
		// 어떤 버튼이 눌려졌는지 알아낸다.
		JButton tmp = (JButton) e.getSource();
		if(tmp == sFr.sendB) {
			System.out.println("debug1");
			sendProc();
		} else if(tmp == sFr.exitB) {
			exitProc();
		} 
	}
	
	public void sendProc() {
		IdIpDAO dao = new IdIpDAO();
		// 할일 
		// 1. 보낼 내용 알아내고
		String msg = sFr.area.getText();
		byte[] buff = msg.getBytes();
		// 2. 받는 사람 주소 알아내고
		String id = sFr.sid;
		dao.getIpById(id);
		IdIpVO vo = dao.getIpById(id);
		String ip = vo.getIp();
		InetAddress inet = null;
		try {
			inet = InetAddress.getByName(ip);
		} catch(Exception e) {}
		System.out.println("debug2");
		// 3. 패킷을 만들고
		DatagramPacket pack = null;
		try {
			pack = new DatagramPacket(buff,buff.length,inet,7799);
			/*
			 	DatagramPacket 생성방법
			 	
			 		new DatagramPacket(byte배열, 배열길이 , 받는사람주소(InetAddress), 포트번호 );
			 */
			// 4. 네트워크에 흘려보낸다.
			sFr.main.sSocket.send(pack);
		} catch(Exception e) {e.printStackTrace(); return;}
		System.out.println("debug3");
		
		sFr.area.setText("");
		// 다 보냈으니 닫는다.
		sFr.wFr.setVisible(false);
		sFr.wFr.dispose();
	}
	
	public void exitProc() {
		sFr.wFr.setVisible(false);
	}
}

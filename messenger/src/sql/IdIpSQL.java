package sql;

public class IdIpSQL {
	public final String GET_IPID_IP = "getIP";
	public final String GET_IPID_ID = "getID";

	public String MailSQL(String code) {
		StringBuffer buff = new StringBuffer();
	
	
		switch(code) {
		case GET_IPID_ID:
			buff.append("SELECT ");
			buff.append(" ID, IP ");
			buff.append("FROM ");
			buff.append("	IPID");
			buff.append("WHERE ");
			buff.append("	ID = ?");
			break;
		case GET_IPID_IP:
			buff.append("SELECT ");
			buff.append(" ID, IP ");
			buff.append("FROM ");
			buff.append("	IPID");
			buff.append("WHERE ");
			buff.append("	IP = ?");
			break;
		}
		
		return buff.toString();
	}
}
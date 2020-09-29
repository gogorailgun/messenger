package sql;

public class IdIpSQL {
	public final int GET_IP =  1000;
	public final int GET_ID = 1001;
	public final int GET_ID_LIST = 2000;

	public String IdIpSQL(int code) {
		StringBuffer buff = new StringBuffer();
	
	
		switch(code) {
		case GET_IP:
			buff.append("SELECT ");
			buff.append(" ID, IP ");
			buff.append("FROM ");
			buff.append("	IPID ");
			buff.append("WHERE ");
			buff.append("	ID = ? ");
			break;
		case GET_ID:
			buff.append("SELECT ");
			buff.append(" ID, IP ");
			buff.append("FROM ");
			buff.append("	IPID ");
			buff.append("WHERE ");
			buff.append("	IP = ? ");
			break;
		case GET_ID_LIST:
			buff.append("SELECT ");
			buff.append(" ID, IP ");
			buff.append("FROM ");
			buff.append("	IPID ");
			buff.append("ORDER BY ");
			buff.append("   ID ");
			break;
		}
		
		return buff.toString();
	}
}
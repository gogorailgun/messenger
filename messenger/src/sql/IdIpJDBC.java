package sql;

import java.sql.*;

public class IdIpJDBC {

	String url,id,pw;

	public IdIpJDBC() {
		try {
			// 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			System.out.println("### 드라이버 로딩 실패 ###");
			e.printStackTrace();
		}
		this.url = "jdbc:oracle:thin:@localhost:1521:orcl";
	}

	// MyJDBC 오버로딩
	public IdIpJDBC(String ip) {
		try {
			// 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			System.out.println("### 드라이버 로딩 실패 ###");
			e.printStackTrace();
		}
		this.url = "jdbc:oracle:thin:@"+ ip +":1521:orcl";
	}

	public Connection getCon() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, "test", "test");
		} catch(Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public Connection getCon(String id, String pw) {
		Connection con = null;
		this.id = id;
		this.pw = pw;

		try {
			con = DriverManager.getConnection(url, id, pw);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	// Statement 반환해주는 함수
	public Statement getSTMT(Connection con) {
		Statement stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	// PreparedStatement 반환해주는 함수
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}


	// 다사용한 자원 반환해주는 함수
	public void close(Object obj) {
		// 반환해야할 자원의 종류가 네가지가 있기 때문에 
		// 네가지 모두를 기억할 수 있는 데이터 타입은 Object 이다.

		// 입력된 데이터의 소속을 밝혀서 처리해주면 된다.
		try {
			if(obj instanceof Connection) {
				((Connection)obj).close();
			} else if(obj instanceof Statement) {
				((Statement)obj).close();
			} else if(obj instanceof PreparedStatement) {
				((PreparedStatement)obj).close();
			} else if(obj instanceof ResultSet) {
				((ResultSet)obj).close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

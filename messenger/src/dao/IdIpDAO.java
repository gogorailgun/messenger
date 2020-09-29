package dao;

import java.util.Vector;
import sql.*;
import vo.IdIpVO;
import java.sql.*;
import java.util.*;

public class IdIpDAO implements IdIpDaoInterface {
	IdIpVO iVO;
	IdIpSQL iSQL;
	IdIpJDBC db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public IdIpDAO() {
		db = new IdIpJDBC();
		iSQL = new IdIpSQL();
	}

	@Override
	public Vector<IdIpVO> selectIdList() {
		Vector<IdIpVO> list = new Vector<IdIpVO>();
		
		con = db.getCon();
		String sql = iSQL.IdIpSQL(iSQL.GET_ID);
		pstmt = db.getPSTMT(con, sql);
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				IdIpVO iVO = new IdIpVO();
				
				iVO.setId(rs.getString("id"));
				iVO.setId(rs.getString("ip"));
				
				list.add(iVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return list;
	}

	@Override
	public IdIpVO getIdByIp(String id) {
		
		con = db.getCon();
		String sql = iSQL.IdIpSQL(iSQL.GET_IP);
		pstmt = db.getPSTMT(con, sql);
		try {
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				rs.next();
				
				String ip = rs.getString("ip");
				
				iVO.setIp(ip);
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return iVO;
		
	}

	@Override
	public IdIpVO getIpById(String ip) {
		con = db.getCon();
		String sql = iSQL.IdIpSQL(iSQL.GET_ID);
		pstmt = db.getPSTMT(con, sql);
		try {
				pstmt.setString(1, ip);
				
				rs = pstmt.executeQuery();
				rs.next();
				
				String id = rs.getString("ip");
				
				iVO.setIp(id);
				
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return iVO;
	}

}

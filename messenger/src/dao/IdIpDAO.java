package dao;

import java.util.Vector;
import sql.*;
import vo.IdIpVO;
import java.sql.*;
import java.util.*;

public class IdIpDAO implements IdIpDaoInterface {
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
		String sql = iSQL.IdIpSQL(iSQL.GET_ID_LIST);
		pstmt = db.getPSTMT(con, sql);
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {
				IdIpVO iVO = new IdIpVO();
				
				iVO.setId(rs.getString("id"));
				iVO.setIp(rs.getString("ip"));
				
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
	public IdIpVO getIdByIp(String ip) {
		
		con = db.getCon();
		String sql = iSQL.IdIpSQL(iSQL.GET_ID);
		pstmt = db.getPSTMT(con, sql);
		IdIpVO iVO = null;
		try {
				pstmt.setString(1, ip);
				
				rs = pstmt.executeQuery();
				rs.next();
				
				String id = rs.getString("id");
				iVO = new IdIpVO();
				iVO.setId(id);
				
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
	public IdIpVO getIpById(String id) {
		con = db.getCon();
		String sql = iSQL.IdIpSQL(iSQL.GET_IP);
		pstmt = db.getPSTMT(con, sql);
		IdIpVO iVO = null;
		try {
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				rs.next();
				
				String ip = rs.getString("ip");
				iVO = new IdIpVO();
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

/*
	public static void main(String[] args) {
		IdIpDAO dao = new IdIpDAO();
		Vector<IdIpVO> sVector  = dao.selectIdList();
		
		for(int i = 0; i < sVector.size(); i++) {
			System.out.println(sVector.get(i).getId());
			System.out.println(sVector.get(i).getIp());
			
		}
		
		System.out.println("\n=================================\n");
		
		IdIpVO str = dao.getIpById("euns");
		System.out.println(str.getIp());
		
		IdIpVO str2 = dao.getIdByIp("192.168.0.62");
		System.out.println(str2.getId());
		
	}
*/
}

package dao;

import java.util.Vector;

import vo.IdIpVO;

public interface IdIpDaoInterface {
	
	Vector<IdIpVO> selectIdList();
	
	IdIpVO getIdByIp(String ip);
	
	IdIpVO getIpById(String id);
	
	
}

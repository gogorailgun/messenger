package dao;

import java.util.ArrayList;

import vo.IdIpVO;

public interface IdIpDaoInterface {
	
	ArrayList<IdIpVO> selectIdList();
	
	IdIpVO getIdByIp(String ip);
	
	IdIpVO getIpById(String id);
	
	
}

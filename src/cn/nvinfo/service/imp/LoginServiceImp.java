package cn.nvinfo.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nvinfo.dao.LoginDao;
import cn.nvinfo.domain.Staff;
import cn.nvinfo.service.LoginService;
/**
 *  登陆
 * @author 杨立   2017-10-09
 *
 */
@Service
@Transactional
public class LoginServiceImp implements LoginService {
	@Resource
	private LoginDao loginDao;
	/*
	 * 查询用户	杨立	2017-10-09
	 */
	public Staff getStaff(String loginName, String password) {
		
		return loginDao.getStaff(loginName,password);
	}
	/*
	 *  修改密码  杨立  2017-10-09(non-Javadoc)
	 * @see cn.nvinfo.service.LoginService#editPassword(java.lang.Integer, java.lang.String)
	 */
	public int editPassword(Integer id, String password) {
		return loginDao.editPassword(id,password);
	}
	/*
	 * 重置密码 	杨立	2017-10-09(non-Javadoc)
	 * @see cn.nvinfo.service.LoginService#initPassword(java.lang.Integer)
	 */
	public int initPassword(Integer id) {
		return loginDao.initPassword(id);
	}

}

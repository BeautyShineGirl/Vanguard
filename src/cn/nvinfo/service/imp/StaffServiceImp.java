package cn.nvinfo.service.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nvinfo.dao.StaffDao;
import cn.nvinfo.domain.Department;
import cn.nvinfo.domain.Power;
import cn.nvinfo.domain.Staff;
import cn.nvinfo.service.StaffService;
import cn.nvinfo.tools.StaffList;
import cn.nvinfo.utils.Pager;

/**
 *  用户管理
 * @author 杨立   2017-09-29
 *
 */
@Service
@Transactional
public class StaffServiceImp implements StaffService {

	@Resource
	private StaffDao staffDao;


	/*
	 * 
	 * 分页查询	杨立   2017-09-29
	 * (non-Javadoc)
	 * @see cn.nvinfo.service.CustomTypeService#getPager(java.lang.Integer, java.lang.Integer)
	 */
	public Pager<StaffList> getPager(Integer pageIndex, Integer pageSize) {
		Pager<StaffList> pager =new Pager<StaffList>();
		pager.setCurrPage(pageIndex);
		pager.setPageSize(pageSize);
		pager.setAllCount(staffDao.getAllCount());
		pager.setDatas(staffDao.getPageDate(pageIndex,pageSize));
		pager.setPageCount();
		return pager;
	}

	/*
	 *删除	 杨立   2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.service.StaffService#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		return staffDao.delete(id);
	}
	
	/*
	 * 从字典中获得部门	杨立   2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.service.StaffService#getdepartment()
	 */
	public List<Department> getdepartment() {
		return staffDao.getdepartment();
	}
	/*
	 * 从字典中获得角色	杨立   2017-09-29
	 */
	public List<String> getRole() {
		return staffDao.getRole();
	}
	/*
	 * 返回权限集合	杨立   2017-09-29
	 */
	public List<Power> getPower() {
		return staffDao.getPower();
	}
	/*
	 * 添加	杨立	2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.service.StaffService#add(cn.nvinfo.domain.Staff)
	 */
	public int add(Staff staff) {
		return staffDao.add(staff);
	}
	/*
	 * 根据id获得用户信息		yangli	2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.service.StaffService#getById(java.lang.Integer)
	 */
	public Staff getById(Integer id) {
		return staffDao.getById(id);
	}
	/*
	 * 修改	杨立	2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.service.StaffService#edit(cn.nvinfo.domain.Staff)
	 */
	public int edit(Staff staff) {
		return staffDao.edit(staff);
	}
	/*
	 * 检查用户名是否已存在，     yangli 2017-10-09(non-Javadoc)
	 * @see cn.nvinfo.service.StaffService#checkLoginName(java.lang.String)
	 */
	public List<String> checkLoginName(String loginName) {
		return staffDao.checkLoginName(loginName);
	}
	/*
	 * //再删除人员之前，先查询该人员是否有负责的景区，若有，则返回删除失败 yangli 2017-10-12(non-Javadoc)
	 * @see cn.nvinfo.service.StaffService#getView()
	 */
	public int getView(Integer id) {
		return staffDao.getView(id);
	}

	
}

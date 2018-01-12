package cn.nvinfo.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.nvinfo.dao.StaffDao;
import cn.nvinfo.domain.Department;
import cn.nvinfo.domain.Power;
import cn.nvinfo.domain.Staff;
import cn.nvinfo.tools.StaffList;
/**
 *  用户管理
 * @author 杨立   2017-09-29
 *
 */

@Repository
public class StaffDaoImp implements StaffDao {

	@Resource
	private SqlSessionTemplate template;
	
	/*
	 * 获得总记录数
	 * (non-Javadoc)
	 * @see cn.nvinfo.dao.CustomTypeDao#getAllCount()
	 */
	public int getAllCount() {
		int rows=template.selectOne("staff.getAllCount");
		return rows;
	}

	/*
	 * 获得当前页的数据
	 * (non-Javadoc)
	 * @see cn.nvinfo.dao.CustomTypeDao#getPageDate(java.lang.Integer, java.lang.Integer)
	 */
	public List<StaffList> getPageDate(Integer pageIndex, Integer pageSize) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pageIndex", (pageIndex-1)*pageSize);
		map.put("pageSize", pageSize);
		List<StaffList> list = template.selectList("staff.getPageDate", map);
		return list;
	}
	/*
	 * 删除	 杨立   2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.dao.StaffDao#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		return template.delete("staff.delete", id);
	}
	/*
	 * 从字典中获得部门	杨立   2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.dao.StaffDao#getdepartment()
	 */
	public List<Department> getdepartment() {
		return template.selectList("staff.getdeparment");
	}
	/*
	 * 从字典中获得部门	杨立   2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.dao.StaffDao#getRole()
	 */
	public List<String> getRole() {
		return template.selectList("staff.getRole");
	}
	/*
	 * 
	 * 返回权限集合	杨立   2017-09-29
	 *(non-Javadoc)
	 * @see cn.nvinfo.dao.StaffDao#getPower()
	 */
	public List<Power> getPower() {
		return template.selectList("staff.getPower");
	}
	/*
	 * 添加	杨立	2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.dao.StaffDao#add(cn.nvinfo.domain.Staff)
	 */
	public int add(Staff staff) {
		return template.insert("staff.save", staff);
	}
	/*
	 * 根据id获得用户信息		yangli	2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.dao.StaffDao#getById(java.lang.Integer)
	 */
	public Staff getById(Integer id) {
		return template.selectOne("staff.getById", id);
	}
	/*
	 * 修改	杨立	2017-09-29(non-Javadoc)
	 * @see cn.nvinfo.dao.StaffDao#edit(cn.nvinfo.domain.Staff)
	 */
	public int edit(Staff staff) {
		return template.update("staff.edit", staff);
	}
	/*
	 * 检查用户名是否已存在，     yangli 2017-10-09
	 */
	public List<String> checkLoginName(String loginName) {
		return template.selectList("staff.checkLoginName", loginName);
		
	}
	/*
	 * //再删除人员之前，先查询该人员是否有负责的景区，若有，则返回删除失败	yangli	2017-10-12(non-Javadoc)
	 * @see cn.nvinfo.dao.StaffDao#getView(java.lang.Integer)
	 */
	public int getView(Integer id) {
		return template.selectOne("staff.getView", id);
	}

	

	
}

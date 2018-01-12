package cn.nvinfo.domain;

import java.util.List;

/**
 * 用户实体 
 * @author 杨立   2017-09-18
 *
 */
public class Staff {

	private int id;//编号
	
	private String name;//姓名
	
	private String loginName;//用户名
	
	private String password;//密码
	
	private String department;//部门
	
	private String role;//角色
	
	private int powerId;//权限
	
	//private Power power;
	
	private String powerName;
	private String powerRemark;
	
	//给前台返回时需要
	//private Menu menu;
	
	private List<Menu> menu;
	
	public Staff() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getPowerId() {
		return powerId;
	}

	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}

	/*public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}*/

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getPowerRemark() {
		return powerRemark;
	}

	public void setPowerRemark(String powerRemark) {
		this.powerRemark = powerRemark;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	/*public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
*/
	

	
	
	
	
}
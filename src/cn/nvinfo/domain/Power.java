package cn.nvinfo.domain;
/**
 * 权限实体
 * @author 杨立   2017-09-18
 *
 */

public class Power {

	private int id;
	
	private String name;
	
	private String remark;

	public Power() {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}

package cn.nvinfo.tools;

import java.util.List;

import cn.nvinfo.domain.Priority;

/**
 * 产品信息管理  字典数据
 * @author 杨立   2017-09-25
 *
 */
public class ProductDictionary {

	private int id;//编号
	private List<String> typeList;//门票类型的集合
	private List<INList> ticketType;//票型的集合
	private List<String> isCancel;//是否可退
	private List<Priority> priority;//优先级
	private List<INList> custom;  //分销商
	private List<INList> view;//景区编号和名字
	private List<INList> supplier;//供应商
	private List<INList> viewType;//景区类别
	public ProductDictionary() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}


	public List<INList> getTicketType() {
		return ticketType;
	}

	public void setTicketType(List<INList> ticketType) {
		this.ticketType = ticketType;
	}

	public List<String> getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(List<String> isCancel) {
		this.isCancel = isCancel;
	}

	public List<Priority> getPriority() {
		return priority;
	}

	public void setPriority(List<Priority> priority) {
		this.priority = priority;
	}


	public List<INList> getCustom() {
		return custom;
	}

	public void setCustom(List<INList> custom) {
		this.custom = custom;
	}

	public List<INList> getView() {
		return view;
	}

	public void setView(List<INList> view) {
		this.view = view;
	}

	public List<INList> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<INList> supplier) {
		this.supplier = supplier;
	}

	public List<INList> getViewType() {
		return viewType;
	}

	public void setViewType(List<INList> viewType) {
		this.viewType = viewType;
	}
	
	
	
}

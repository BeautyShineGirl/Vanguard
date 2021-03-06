package cn.nvinfo.domain;

import java.util.List;



/**
 * 产品实体  
 * @author 杨立   2018-01-12
 *
 */
public class Product {

	private int id;//产品编号
	private String name;//票名 
	private int viewId;//景区编号
	private String viewName;
	private double marketPrice;//门市价
	private String salePrice;//销售价	
	private String  isSale;//是否销售  
	private String startTime;//开始时间 	
	private String endTime;//结束时间
	private int num;//总数量
	private String orderTime;//提前预订时间
	private String isCancel;//是否可退    否，是  数据字典
	private String notice;//入园须知
	private String costInside;//费用包含
	private String costOutside;//费用不包含
	private String remark;//备注
	private String userType;//使用者类别开关   0禁用 ，1不禁用
	private int sort;//产品排序
	private String method;//入园方式
	private String characteristic;//产品特点
	private String picture;//该产品所对应的景区图片
	private List<Images> images;//分销商页面返回产品所对应的图片
	private String pro_img;//产品的主图
	private Images qrcode;
	private String viewRemark;//景区简介的字段
	public Product() {
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


	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getCostInside() {
		return costInside;
	}

	public void setCostInside(String costInside) {
		this.costInside = costInside;
	}

	public String getCostOutside() {
		return costOutside;
	}

	public void setCostOutside(String costOutside) {
		this.costOutside = costOutside;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}



	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}


	
	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getPro_img() {
		return pro_img;
	}

	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}

	public String getViewRemark() {
		return viewRemark;
	}

	public void setViewRemark(String viewRemark) {
		this.viewRemark = viewRemark;
	}


	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}


	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Images getQrcode() {
		return qrcode;
	}

	public void setQrcode(Images qrcode) {
		this.qrcode = qrcode;
	}

	
}

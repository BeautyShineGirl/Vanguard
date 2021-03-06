package cn.nvinfo.domain;

import java.util.List;

/**
 * 景区实体
 * @author 杨立   2017-09-18
 *
 */
public class ViewMessage {

	private int id;//景区编号
	private String random_no;//根据随机数，改变url，上传图片用
	private String name;//景区名称 	
	private int staffId;//业务人员
	private String level;//景区等级   5A, 4A, 3A,自由景区等级    数据字典
	private String type;//景区分类  	数据字典
	private String remark;//景区简介
	private String address;//地址              
	private String businessTime;//营业时间
	private String phone;//电话
	private String reminder;//温馨提示
	private String discount;//优惠政策
	private String busMessage;//公交信息
	private String selfRoute;//自驾线路
	private String picture;//照片
	private String staffName;
	private double lng;//经度
	private double lat;//纬度
	private int sort;//景区排序    0,1,2级别越高，数字越小
	private String province;//所属省份
	private String city;//所属城市
	private String tinyPrice;//景区产品最低价
	private String tag1;
	private String tag2;
	private String tag3;
	private String tag4;
	private List<Images> pic;
	private String view_img;
	private List<Images> view_img_pic;
	
	private int logic;//将华润万家的景区分开，普通景区为1，抢购景区为2
	private String logicName;
	public ViewMessage() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRandom_no() {
		return random_no;
	}
	public void setRandom_no(String random_no) {
		this.random_no = random_no;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusinessTime() {
		return businessTime;
	}
	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReminder() {
		return reminder;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getBusMessage() {
		return busMessage;
	}
	public void setBusMessage(String busMessage) {
		this.busMessage = busMessage;
	}
	public String getSelfRoute() {
		return selfRoute;
	}
	public void setSelfRoute(String selfRoute) {
		this.selfRoute = selfRoute;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public List<Images> getPic() {
		return pic;
	}
	public void setPic(List<Images> pic) {
		this.pic = pic;
	}
	public String getTinyPrice() {
		return tinyPrice;
	}
	public void setTinyPrice(String tinyPrice) {
		this.tinyPrice = tinyPrice;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	public String getTag3() {
		return tag3;
	}
	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}
	public String getTag4() {
		return tag4;
	}
	public void setTag4(String tag4) {
		this.tag4 = tag4;
	}
	public String getView_img() {
		return view_img;
	}
	public void setView_img(String view_img) {
		this.view_img = view_img;
	}
	public List<Images> getView_img_pic() {
		return view_img_pic;
	}
	public void setView_img_pic(List<Images> view_img_pic) {
		this.view_img_pic = view_img_pic;
	}
	public int getLogic() {
		return logic;
	}
	public void setLogic(int logic) {
		this.logic = logic;
	}
	public String getLogicName() {
		return logicName;
	}
	public void setLogicName(String logicName) {
		this.logicName = logicName;
	}
	
}
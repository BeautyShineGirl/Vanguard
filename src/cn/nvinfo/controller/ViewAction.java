package cn.nvinfo.controller;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;


import cn.nvinfo.domain.Images;
import cn.nvinfo.domain.Product;
import cn.nvinfo.domain.User;
import cn.nvinfo.domain.ViewMessage;
import cn.nvinfo.service.ViewService;
import cn.nvinfo.tools.INList;
import cn.nvinfo.tools.ViewDictionary;
import cn.nvinfo.tools.ViewList;
import cn.nvinfo.utils.CheckUtil;
import cn.nvinfo.utils.Pager;
import cn.nvinfo.utils.Result;

/**
 *  景区管理    
 * @author 杨立   2017-09-19
 *
 */

@Controller
@Scope("prototype")
@SessionAttributes("staff")
@RequestMapping("view")
public class ViewAction {
	Logger log=Logger.getLogger(ViewAction.class);
	@Resource
	private ViewService viewService;
	private Integer pageSize=10;

	/**
	 *  分页查询	杨立  2017-09-21 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findPageData.action")
	public @ResponseBody Object findPageData(Integer pageIndex,Integer viewId,Integer logic,String staffName,
			String viewName,String level,String viewType,String province,String city,Integer staff_id,Integer power_id){

		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(pageIndex,staff_id,power_id))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		log.info("分页查询提交条件： pageIndex="+pageIndex+", viewId="+viewId+", logic="+logic+", staffName="+staffName+", viewName="+viewName
				+", level="+level+", viewType="+viewType+", province="+province+", city="+city+", power_id="+power_id+", staff_id="+staff_id);
		Pager<ViewMessage> pager=null;
		if(power_id==0){
			log.info("系统管理员查询");
			//系统管理员
			pager=viewService.getPager(pageIndex,pageSize,viewId,logic,staffName,viewName,level,viewType,province,city);
		}else{
			log.info("普通管理员");
			//普通管理员	yangli	2017-10-19
			pager=viewService.getPager(pageIndex,pageSize,viewId,logic,staffName,viewName,level,viewType,province,city,staff_id);
		}
		List<ViewList> list = new ArrayList<ViewList>();  
		Map<Object,Object> listPage=new HashMap<Object,Object>();
		if(pager.getDatas().size()!=0){
			for (ViewMessage v : pager.getDatas()) {
				int id = v.getId();
				//查询每个景区所对应的产品数量
				int num=viewService.getViewNum(id);
				ViewList view =new ViewList();
				view.setId(v.getId());
				view.setName(v.getName());
				view.setLevel(v.getLevel());
				view.setSort(v.getSort());
				view.setStaffId(v.getStaffId());
				view.setBusinessTime(v.getBusinessTime());
				view.setViewType(v.getType());
				view.setNumber(num);
				view.setStaffName(v.getStaffName());
				list.add(view);
			}

			listPage.put("allCount",pager.getAllCount());
			listPage.put("currPage",pager.getCurrPage());
			listPage.put("pageSize",pager.getPageSize());
			listPage.put("pageCount",pager.getPageCount());
			listPage.put("datas",list);
			log.info(new Result(1,"查询成功",listPage));
			return new Result(1,"查询成功",listPage);
		}else{
			log.info(new Result(0,"暂无数据",pager));
			return new Result(0,"暂无数据",pager);
		}

	}


	/**
	 * 添加请求，返回字典数据	杨立   2017-09-19 
	 * @return
	 */
	@RequestMapping("/addUI.action")
	public @ResponseBody Object addUI(){
		//从字典表中获得景区分类
		List<String> typeList=viewService.getViewType();
		//从字典表中或的景区等级
		List<String> levelList=viewService.getViewLevel();
		//从字典中获得省份集合
		List<String> viewProvince=viewService.getViewProvince();
		//从字典中获得城市集合
		List<String> viewCity=viewService.getViewCity();
		//从字典中获得景区属性集合	yangli 2018-02-06 
		List<INList> viewLogic=viewService.getViewLogic();
		//获得业务员的编号和姓名     杨立     2017-09-20
		List<User> staff=viewService.getStaff();
		ViewDictionary vd=new ViewDictionary();
		vd.setViewType(typeList);
		vd.setViewLevel(levelList);
		vd.setViewProvince(viewProvince);
		vd.setViewCity(viewCity);
		vd.setStaff(staff);
		vd.setViewLogic(viewLogic);
		log.info(new Result(1,"查询成功",vd));
		return new Result(1,"查询成功",vd);
	}

	/**
	 * 修改数据回显  杨立  2017-09-21 
	 * @param id
	 * @return
	 */
	@RequestMapping("/editUI.action")
	public @ResponseBody Object editUI(Integer id,HttpServletRequest request){

		//验证参数 
		if(!"".equals(CheckUtil.checkArgsNotNull(id))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}


		//查询
		ViewMessage view = viewService.getById(id);
		String picture = view.getPicture();
		if(picture==null||"".equals(picture)){
			
			view.setPic(null);
		}else{
			String[] split = picture.split(",");
			List<Images> list=new ArrayList<Images>();
			for (String string : split) {
				Images images=new Images();
				images.setName(string);
				//正式服务器
				String path="http://service.nvinfo.cn:8080"+request.getContextPath()+"/upload/images/"+string;
				//String path="http://test.elvmedia.cn:8080"+request.getContextPath()+"/upload/images/"+string;
				//String path="http://192.168.1.109:8080"+request.getContextPath()+"/upload/images/"+string;
				images.setUrl(path);
				list.add(images);

			}
			view.setPic(list);

		}
		String view_img = view.getView_img();
		if(view_img==null||"".equals(view_img)){
			view.setView_img_pic(null);
		}else{
			List<Images> list=new ArrayList<Images>();
			Images images=new Images();
			images.setName(view_img);
			//正式服务器
			String path="http://service.nvinfo.cn:8080"+request.getContextPath()+"/upload/images/QRcode/"+view_img;
			//String path="http://test.elvmedia.cn:8080"+request.getContextPath()+"/upload/images/QRcode/"+string;
			//String path="http://192.168.1.109:8080"+request.getContextPath()+"/upload/images/QRcode/"+string;
			images.setUrl(path);
			list.add(images);
			view.setView_img_pic(list);
		}
		log.info(new Result(1,"查询成功",view));
		return new Result(1,"查询成功",view);
	}


	/**
	 * 添加修改景区信息    杨立   2017-09-20    完成状态，批量上传，需大批测试
	 * @param view
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/addUpdate.action")
	public @ResponseBody Object add(ViewMessage view){
		String name= view.getName();
		String level=view.getLevel();
		String viewType=view.getType();
		String address=view.getAddress();
		String businessTime=view.getBusinessTime();
		String phone=view.getPhone();
		String reminder=view.getReminder(); 
		String discount=view.getDiscount();
		String busMessage=view.getBusMessage();
		String selfRoute=view.getSelfRoute();		
		Integer staffId = view.getStaffId();
		//根据staffId查名字	杨立	2017-10-26
		view.setStaffName(viewService.getByStaffId(staffId));
		Double lng =view.getLng();
		Double lat=view.getLat();
		Integer sort=view.getSort();
		String province=view.getProvince();
		String city=view.getCity();
		String remark=view.getRemark();
		String random_no=view.getRandom_no();
		String tinyPrice=view.getTinyPrice();
		String tag1=view.getTag1();
		String tag2=view.getTag2();
		String tag3=view.getTag3();
		String tag4=view.getTag4();
		Integer logic=view.getLogic();
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(name,level,viewType,address,businessTime,
				phone,discount,staffId,lng,lat,sort,province,city,remark,random_no,tinyPrice,tag1,tag2,tag3,tag3,logic))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}

		log.info("添加提交条件： name="+name+", level="+level+", viewType="+viewType+", address="+address+", businessTime="+businessTime+", phone="+phone+", reminder="+reminder+", discount="+discount
				+", busMessage="+busMessage+", selfRoute="+selfRoute+", staffId="+staffId+", lng="+lng+", lat="+lat+", sort="+sort+", province="+province+", city="+city+", remark="+remark+", random_no="+random_no+
				", tinyPrice="+tinyPrice+", tag1="+tag1+", tag2="+tag2+", tag3="+tag3+", tag4="+tag4+", logic="+logic);
		
		//添加操作
		int rows= viewService.edit(view);
		if(rows>0){
			log.info(new Result(1,"添加成功"));
			return new Result(1,"添加成功");
		} else {
			log.info(new Result(0,"添加失败"));
			return new Result(0,"添加失败");
		} 
	}

	/*
	 * 后台管理      添加抢购产品的图片上传到服务器
	 */ 
	@RequestMapping("/addImages.action")
	public @ResponseBody Object addImages(MultipartFile file,HttpServletRequest request,String random_no){
		String newUrl=null;
		//先查询数据库中是否存在这个随机数，若存在，则不进行添加随机数	2018-01-12
		List<String> random_no_old=viewService.getRandom_no(random_no);
		if(random_no_old.size()==0){
			//先添加产品随机数，就相当于添加了一个产品，然后在对这个产品进行修改url	2018-01-12
			viewService.addRandom_no(random_no);
		}
		String iconName=file.getOriginalFilename();

		//根据random_no查出原本的oldUrl	yangli	2018-01-12
		List<String> oldUrl=viewService.getOldUrl(random_no);
		//保存文件  
		saveFile(file,request);  
		if(oldUrl.size()!=0){
			//保存获取文件的路径
			if(oldUrl.get(0)==null||oldUrl.get(0).equals("")){
				newUrl=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+iconName;
			}else{
				newUrl=oldUrl.get(0)+","+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+iconName;
			}
		}
		//修改图片路径	2018-01-12	杨立
		int rows= viewService.updateUrl(random_no,newUrl);
		if(rows>0){
			log.info(new Result(1,"上传成功"));
			return new Result(1,"上传成功");
		} else {
			log.info(new Result(0,"上传失败"));
			return new Result(0,"上传失败");
		} 
	}
	/*
	 * 后台管理      添加景区二维码
	 */ 
	@RequestMapping("/addViewImg.action")
	public @ResponseBody Object addViewImg(MultipartFile file,HttpServletRequest request,String random_no){
	
		//先查询数据库中是否存在这个随机数，若存在，则不进行添加随机数	2018-01-12
		List<String> random_no_old=viewService.getRandom_no(random_no);
		if(random_no_old.size()==0){
			//先添加产品随机数，就相当于添加了一个产品，然后在对这个产品进行修改url	2018-01-12
			viewService.addRandom_no(random_no);
		}
		String iconName=file.getOriginalFilename();

		//保存文件  
		saveFileImg(file,request);  
		String view_img=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+iconName;
		//修改二维码图片路径	2018-01-12	杨立
		int rows= viewService.updateView_img(random_no,view_img);
		if(rows>0){
			log.info(new Result(1,"上传成功"));
			return new Result(1,"上传成功");
		} else {
			log.info(new Result(0,"上传失败"));
			return new Result(0,"上传失败");
		} 
	}
	/*** 
	 * 保存文件 
	 * @param file 
	 * @return 
	 */  
	public boolean saveFile(MultipartFile file,HttpServletRequest request) {  
		String iconName=file.getOriginalFilename();
		// 校验图片格式  
		// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分  
		String lastName = iconName.substring(iconName.lastIndexOf("\\") + 1);  
		// 得到上传文件的扩展名  
		String fileExtName = lastName.substring(lastName.lastIndexOf(".") + 1);//doc,docx,pdf
		// 校验图片格式 	这段代码没有测试
		if("bmp".equalsIgnoreCase(fileExtName)||"jpg".equalsIgnoreCase(fileExtName)||
				"jpeg".equalsIgnoreCase(fileExtName)||"gif".equalsIgnoreCase(fileExtName)||"png".equalsIgnoreCase(fileExtName)){
			//获取上传文件的路径
			String src=request.getSession().getServletContext().getRealPath("upload/images/")+File.separator;
			String path=src+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+iconName;
			File newFile=new File(path);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
			} 

		}
		return false; 	
	}
	public boolean saveFileImg(MultipartFile file,HttpServletRequest request) {  
		String iconName=file.getOriginalFilename();
		// 校验图片格式  
		// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分  
		String lastName = iconName.substring(iconName.lastIndexOf("\\") + 1);  
		// 得到上传文件的扩展名  
		String fileExtName = lastName.substring(lastName.lastIndexOf(".") + 1);//doc,docx,pdf
		// 校验图片格式 	这段代码没有测试
		if("bmp".equalsIgnoreCase(fileExtName)||"jpg".equalsIgnoreCase(fileExtName)||
				"jpeg".equalsIgnoreCase(fileExtName)||"gif".equalsIgnoreCase(fileExtName)||"png".equalsIgnoreCase(fileExtName)){
			//获取上传文件的路径
			String src=request.getSession().getServletContext().getRealPath("upload/images/QRcode")+File.separator;
			String path=src+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+iconName;
			File newFile=new File(path);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
			} 

		}
		return false; 	
	}
	/*
	 * 后台管理   修改产品  删除图片
	 */ 
	@RequestMapping("/deleteImages.action")
	public @ResponseBody Object deleteImages(HttpServletRequest request,String random_no,String deleteURL){
		String newUrl=null;
		/*
		 * 先删除服务器中的图片，然后更新表中的url
		 */
		String src=request.getSession().getServletContext().getRealPath("upload/images")+File.separator;
		String path=src+deleteURL;
		// 判断目录或文件是否存在  
		File file1 = new File(path); 
		// 路径为文件且不为空则进行删除  
		if (file1.isFile() && file1.exists()) {  
			file1.delete();  
		} 

		/*
		 * 更新数据库中的url
		 */
		//根据random_no查出原本的oldUrl	yangli	2017-12-25
		List<String> oldUrl=viewService.getOldUrl(random_no);
		if(deleteURL!=null||!"".equals(deleteURL)){
			String editUrl=oldUrl.get(0);
			if(oldUrl.size()!=0){
				String[] split = editUrl.split(",");
				for (int i = 0; i < split.length; i++) {
					if(!split[i].equals(deleteURL)){
						if(newUrl==null){
							newUrl=split[i];
						}else{
							newUrl=newUrl+","+split[i];
						}
					}
				}
			}
		}
		//修改图片路径	2017-12-25	杨立
		int rows= viewService.updateUrl(random_no,newUrl);
		if(rows>0){
			log.info(new Result(1,"删除成功"));
			return new Result(1,"删除成功");
		} else {
			log.info(new Result(0,"删除失败"));
			return new Result(0,"删除失败");
		} 
	}

	/*
	 * 后台管理   修改产品  删除二维码图片
	 */ 
	@RequestMapping("/deleteViewImg.action")
	public @ResponseBody Object deleteViewImg(HttpServletRequest request,String random_no,String deleteURL){
		String newUrl=null;
		/*
		 * 先删除服务器中的图片，然后更新表中的url
		 */
		String src=request.getSession().getServletContext().getRealPath("upload/images/QRcode")+File.separator;
		String path=src+deleteURL;
		// 判断目录或文件是否存在  
		File file1 = new File(path); 
		// 路径为文件且不为空则进行删除  
		if (file1.isFile() && file1.exists()) {  
			file1.delete();  
		} 

		/*
		 * 更新数据库中的view_img
		 */
	
		//修改图片路径	2017-12-25	杨立
		int rows= viewService.updateView_img(random_no,newUrl);
		if(rows>0){
			log.info(new Result(1,"删除成功"));
			return new Result(1,"删除成功");
		} else {
			log.info(new Result(0,"删除失败"));
			return new Result(0,"删除失败");
		} 
	}

	/**
	 * 删除  	杨立   2017-09-20  
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete.action")
	public @ResponseBody Object delete(Integer id){
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(id))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		log.info("删除提交的信息：id="+id);
		//再删除景区之前，先查询该景区是否有产品，若有，则返回删除失败
		int viewCount=viewService.getProduct(id);
		if(viewCount!=0){
			log.info(new Result(0,"删除失败，该景区存在产品","id="+id));
			return new Result(0,"删除失败，该景区存在产品");
		}
		//修改操作
		int rows= viewService.delete(id);
		if(rows>0){
			log.info(new Result(1,"删除成功","id="+id));
			return new Result(1,"删除成功");
		} else {
			log.info(new Result(0,"删除失败","id="+id));
			return new Result(0,"删除失败");
		}		
	}


	/**
	 * 向页面返回景区信息  	杨立	2018-01-12
	 * @param 
	 * @return
	 */
	@RequestMapping("/getView.action")
	public @ResponseBody Object getView(HttpServletRequest request){
		//获得景区的集合,按照sort从小到大排列	杨立	2018-01-12
		List<ViewMessage> list= viewService.getView();
		if(list.size()!=0){
			for (ViewMessage viewMessage : list) {
				String picture = viewMessage.getPicture();
				if(picture==null&&"".equals("")){
					viewMessage.setPic(null);
				}else{
					String[] split = picture.split(",");
					List<Images> list1=new ArrayList<Images>();
					for (String string : split) {
						Images images=new Images();
						images.setName(string);
						//正式服务器
						String path="http://service.nvinfo.cn:8080"+request.getContextPath()+"/upload/images/"+string;
						//String path="http://test.elvmedia.cn:8080"+request.getContextPath()+"/upload/images/"+string;
						//String path="http://192.168.1.109:8080"+request.getContextPath()+"/upload/images/"+string;
						images.setUrl(path);
						list1.add(images);

					}
					viewMessage.setPic(list1);

				}
			}
			log.info(new Result(1,"查询成功",list));
			return new Result(1,"查询成功",list);
		}else{
			log.info(new Result(0,"暂无数据"));
			return new Result(0,"暂无数据");
		}
	}

	/**
	 * 根据景区id，返回产品列表信息	杨立	2018-01-12
	 */
	@RequestMapping("/productList.action")
	public @ResponseBody Object productList(Integer id,HttpServletRequest request){
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(id))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		//查询根据景区id返回产品List详情	yangli	2018-01-12
		List<Product> list = viewService.getByIdList(id);
		if(list.size()!=0){
			for (Product product2 : list) {
				//处理照片
				String picture = product2.getPicture();
				if(picture==null&&"".equals("")){
					product2.setImages(null);
				}else{
					String[] split = picture.split(",");	
					List<Images> list2=new ArrayList<Images>();
					for (String string : split) {
						Images images=new Images();
						images.setName(string);
						//正式服务器
						String path="http://service.nvinfo.cn:8080"+request.getContextPath()+"/upload/images/"+string;
						//String path="http://test.elvmedia.cn:8080"+request.getContextPath()+"/upload/images/"+string;
						//String path="http://192.168.1.109:8080"+request.getContextPath()+"/upload/images/"+string;
						images.setUrl(path);
						list2.add(images);
					}
					product2.setImages(list2);
				}
			}
			log.info(new Result(1,"查询成功",list));
			return new Result(1,"查询成功",list);
		}else{
			log.info(new Result(0,"暂无数据"));
			return new Result(0,"暂无数据");
		}
	}
	
	/**
	 * logic=2
	 * 向页面返回抢购产品景区信息logic=2  	杨立	2018-02-06
	 * @param 
	 * @return
	 */
	@RequestMapping("/getHotView.action")
	public @ResponseBody Object getHotView(HttpServletRequest request){
		// 向页面返回抢购产品景区信息logic=2  	杨立	2018-02-06
		List<ViewMessage> list= viewService.getHotView();
		if(list.size()!=0){
			for (ViewMessage viewMessage : list) {
				String picture = viewMessage.getPicture();
				if(picture==null&&"".equals("")){
					viewMessage.setPic(null);
				}else{
					String[] split = picture.split(",");
					List<Images> list1=new ArrayList<Images>();
					for (String string : split) {
						Images images=new Images();
						images.setName(string);
						//正式服务器
						String path="http://service.nvinfo.cn:8080"+request.getContextPath()+"/upload/images/"+string;
						//String path="http://test.elvmedia.cn:8080"+request.getContextPath()+"/upload/images/"+string;
						//String path="http://192.168.1.109:8080"+request.getContextPath()+"/upload/images/"+string;
						images.setUrl(path);
						list1.add(images);

					}
					viewMessage.setPic(list1);

				}
			}
			log.info(new Result(1,"查询成功",list));
			return new Result(1,"查询成功",list);
		}else{
			log.info(new Result(0,"暂无数据"));
			return new Result(0,"暂无数据");
		}
	}

}

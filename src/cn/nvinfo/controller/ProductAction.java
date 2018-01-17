package cn.nvinfo.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





import cn.nvinfo.domain.Images;
import cn.nvinfo.domain.Product;
import cn.nvinfo.service.ProductService;
import cn.nvinfo.tools.ProductDictionary;
import cn.nvinfo.tools.INList;
import cn.nvinfo.tools.ProductList;
import cn.nvinfo.utils.CheckUtil;
import cn.nvinfo.utils.Pager;
import cn.nvinfo.utils.Result;

/**
 * 产品信息管理
 * @author 杨立   2017-09-25
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("product")
public class ProductAction {
	private static Logger log=Logger.getLogger(ProductAction.class);
	@Resource
	private ProductService productService;
	private Integer pageSize=10;	


	/**
	 *  分页查询	杨立  2017-09-26
	 * @param pageIndex
	 * @param name
	 * @param viewName
	 * @param viewType
	 * @return
	 */
	@RequestMapping("/findPageData.action")
	public @ResponseBody Object findPageData(Integer pageIndex,Integer id,Integer logic,String name,String viewName,String viewType,
			Integer power_id,Integer staff_id){
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(pageIndex,power_id,staff_id))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		log.info("分页查询提交条件： pageIndex="+pageIndex+", id="+id+", logic="+logic+", name="+name+", viewName="+viewName
				+", viewType="+viewType+", staff_id="+staff_id+", power_id="+power_id);
		Pager<ProductList> pager=null;
		if(power_id==0){
			log.info("系统管理员");
			//系统管理员	yangli	2017-10-19	
			pager=productService.getPager(pageIndex,pageSize,id,logic,name,viewName,viewType);
		}else{
			log.info("普通管理员");
			//普通管理员	yangli	2017-10-19
			pager=productService.getPager(pageIndex,pageSize,id,logic,name,viewName,viewType,staff_id);
		}
		if(pager.getDatas().size()!=0){
			log.info(new Result(1,"查询成功",pager));
			return new Result(1,"查询成功",pager);
		}else{
			log.info(new Result(0,"暂无数据",pager));
			return new Result(0,"暂无数据",pager);
		}
	}


	/**
	 * 添加请求，返回字典数据	杨立   2017-09-25 
	 * @return
	 */
	@RequestMapping("/addUI.action")
	public @ResponseBody Object addUI(){
		//从字典中是否可退
		List<String> isCancel=productService.getIsCancel();
		//所属景区
		List<INList> view = productService.getView();
		//返回景区类别
		List<INList> viewType = productService.getViewType();
		ProductDictionary pd=new ProductDictionary();
		pd.setIsCancel(isCancel);
		pd.setView(view);
		pd.setViewType(viewType);
		log.info(new Result(1,"查询成功",pd));
		return new Result(1,"查询成功",pd);
	}
	
	/**
	 * 添加  杨立   2017-09-25 
	 * @param view
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/add.action")
	public @ResponseBody Object add(Product product){	
		String name=product.getName();
		Integer view=product.getViewId();
		Double marketPric=product.getMarketPrice();
		String salePrice=product.getSalePrice();
		String isSale=product.getIsSale();
		String startTime=product.getStartTime();
		String endTime=product.getEndTime();
		Integer num=product.getNum();
		String orderTime=product.getOrderTime();
		String isCancel=product.getIsCancel();
		String notice=product.getNotice();
		String costInside=product.getCostInside();
		String costOutside=product.getCostOutside();
		Integer sort=product.getSort();
		String method=product.getMethod();
		String remark=product.getRemark();
		product.setUserType("1");
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(name,view,marketPric,isSale,
				startTime,endTime,num,orderTime,isCancel,notice,costInside,costOutside,
				method,sort,remark))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}

		log.info("添加提交条件：name="+name+", view="+view+", marketPric="+marketPric+", salePrice="+salePrice+", isSale="+isSale+", ticketType="+", startTime="+startTime+
				", endTime="+endTime+", num="+num+", orderTime="+orderTime+", isCancel"+isCancel+", notice="+notice+", costInside="+costInside+
				", costOutside"+costOutside+", sort="+sort+", method="+method+", remark="+remark);
		//添加操作
		int rows= productService.add(product);
		Integer pageIndex=1;
		Pager<ProductList> pager1=productService.getPager(pageIndex,pageSize,null,null,null,null,null);
		Integer pageCount =pager1.getPageCount();
		Pager<ProductList> pager=productService.getPager(pageCount,pageSize,null,null,null,null,null);
		if(rows>0){
			log.info(new Result(1,"添加成功",pager));
			return new Result(1,"添加成功",pager);
		} else {
			log.info(new Result(0,"添加失败"));
			return new Result(0,"添加失败");
		}
	}
	/**
	 * 修改数据回显  杨立  2017-09-25
	 * @param id
	 * @return
	 */
	@RequestMapping("/editUI.action")
	public @ResponseBody Object editUI(Integer id){
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(id))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		//查询
		Product product = productService.getById(id);
		Integer viewId = product.getViewId();
		//根据id获得景区名字
		String viewName=productService.getViewName(viewId);
		product.setViewName(viewName);
		log.info(new Result(1,"查询成功",product));
		return new Result(1,"查询成功",product);
	}
	/**
	 * 修改	杨立	2017-09-25
	 * 
	 */
	@RequestMapping("/edit.action")
	public @ResponseBody Object edit(Product product){
		Integer id=product.getId();
		String name=product.getName();
		Integer view=product.getViewId();
		Double marketPric=product.getMarketPrice();
		String salePrice=product.getSalePrice();
		String isSale=product.getIsSale();
		String startTime=product.getStartTime();
		String endTime=product.getEndTime();
		Integer num=product.getNum();
		String orderTime=product.getOrderTime();
		String isCancel=product.getIsCancel();
		String notice=product.getNotice();
		String costInside=product.getCostInside();
		String costOutside=product.getCostOutside();
		Integer sort=product.getSort();
		String method=product.getMethod();
		String remark=product.getRemark();
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(id,name,view,marketPric,salePrice,isSale,startTime,endTime,num,orderTime,
				isCancel,notice,costInside,costOutside,method,sort,remark))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		log.info("修改提交条件： id="+id+", name="+name+", view="+view+", marketPric="+marketPric+", salePrice="+salePrice
				+", isSale="+isSale+", startTime="+startTime+", endTime="+endTime+", num="+num+", orderTime="+orderTime
				+", isCance="+isCancel+", notice="+notice+", costInside="+costInside+", costOutside="+costOutside+", sort="+
				sort+", method="+method+", remark="+remark);
		 //修改操作
		int rowsProduct= productService.edit(product);
		Integer pageIndex=1;
		
		if(rowsProduct>0){
			log.info(new Result(1,"修改成功",findPageData(pageIndex,id,null,null,null,null,null,null)));
			return new Result(1,"修改成功",findPageData(pageIndex,id,null,null,null,null,null,null));
		} else {
			log.info(new Result(0,"修改失败"));
			return new Result(0,"修改失败");
		}		
	}
	/**
	 * 删除  	杨立   2017-09-25
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
		log.info("删除提交的信息："+","+id);
		//修改操作
		int rows= productService.delete(id);
		if(rows>0){
			log.info(new Result(1,"删除成功","id="+id));
			return new Result(1,"删除成功");
		} else {
			log.info(new Result(0,"删除失败","id="+id));
			return new Result(0,"删除失败");
		}		
	}

	/**
	 * 根据id返回产品详情  杨立  2017-09-25	修改	2017-11-08
	 * @param id
	 * @return
	 */
	@RequestMapping("/detailPro.action")
	public @ResponseBody Object detailPro(Integer id,HttpServletRequest request){
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(id))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		//查询
		Product product = productService.getById(id);
		if(product!=null){
			//处理照片
			String picture = product.getPicture();
			if(picture==null&&"".equals("")){
				product.setImages(null);
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
				product.setImages(list2);
			}
			String pro_img=product.getPro_img();
			if(pro_img==null&&"".equals("")){
				product.setQrcode(null);
			}else{
				Images images1=new Images();
				images1.setName(pro_img);
				//正式服务器
				String path="http://service.nvinfo.cn:8080"+request.getContextPath()+"/upload/images/QRcode/"+pro_img;
				//String path="http://test.elvmedia.cn:8080"+request.getContextPath()+"/upload/images/"+string;
				//String path="http://192.168.1.109:8080"+request.getContextPath()+"/upload/images/"+string;
				images1.setUrl(path);
				product.setQrcode(images1);
			}
			log.info(new Result(1,"查询成功",product));
			return new Result(1,"查询成功",product);
		}else{
			log.info(new Result(0,"暂无数据"));
			return new Result(0,"暂无数据");
		}
	}
}

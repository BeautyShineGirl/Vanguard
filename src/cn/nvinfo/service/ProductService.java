package cn.nvinfo.service;

import java.util.List;

import cn.nvinfo.domain.Product;
import cn.nvinfo.tools.INList;
import cn.nvinfo.tools.ProductList;
import cn.nvinfo.utils.Pager;


/**
 * 产品信息管理
 * @author 杨立   2017-09-25
 *
 */
public interface ProductService {
	
	 // 从字典中是否可退	杨立   2017-09-25  
	List<String> getIsCancel();
	
	 // 所属景区 	杨立   2017-09-25  
	List<INList> getView();
	
	// 添加	杨立   2017-09-25
	int add(Product product);
	
	//删除	杨立   2017-09-25
	int delete(Integer id);
	//数据回显	杨立   2017-09-25
	Product getById(Integer id);
	String getViewName(Integer viewId);
	
	//修改	杨立   2017-09-25
	int edit(Product product);
	
	 //  分页查询	杨立  2017-09-26
	Pager<ProductList> getPager(Integer pageIndex, Integer pageSize,Integer id,Integer logic, String name,
			String viewName, String viewType);
	
	 // 返回景区类别	yangli	2017-10-19
	List<INList> getViewType();
	
	 //普通管理员	yangli	2017-10-19
	Pager<ProductList> getPager(Integer pageIndex, Integer pageSize,
			Integer id, Integer logic, String name, String viewName,
			String viewType,Integer staff_id);
	
}

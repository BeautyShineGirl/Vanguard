package cn.nvinfo.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import cn.nvinfo.dao.ProductDao;
import cn.nvinfo.domain.Product;
import cn.nvinfo.tools.INList;
import cn.nvinfo.tools.ProductList;
/**
 * 产品信息管理
 * @author 杨立   2017-09-25
 *
 */

@Repository
public class ProductDaoImp implements ProductDao {

	@Resource
	private SqlSessionTemplate template;

	/*
	 * 从字典中是否可退	杨立   2017-09-25  
	 * (non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#getisCancel()
	 */
	public List<String> getIsCancel() {
		return template.selectList("product.getIsCancel");
	}
	
	
	 /* 所属景区 	杨立   2017-09-25  (non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#getView()
	 */
	public List<INList> getView() {
		return template.selectList("product.getView");
	}
	
	
	 /* 添加	杨立   2017-09-25
	 * (non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#add(cn.nvinfo.domain.Product)
	 */
	public int add(Product product) {
		return template.insert("product.save", product);
	}
	
	/*删除 	杨立   2017-09-25  
	 * (non-Javadoc)
	 * @see cn.nvinfo.dao.ViewDao#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		return  template.delete("product.delete", id);
	}
	
	
	 /* 数据回显	杨立   2017-09-25(non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#getById(java.lang.Integer)
	 */
	public Product getById(Integer id) {
		return template.selectOne("product.getById",id);
	}
	
	 /* 根据id获得景区名字 杨立   2017-09-25
	 * (non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#getViewName()
	 */
	public String getViewName(Integer viewId) {
		return template.selectOne("product.getViewName",viewId);
	}

	public String getSupplierName(Integer supplierId) {
		return template.selectOne("product.getSupplierName",supplierId);
	}
	
	
	 /*  修改	杨立   2017-09-25(non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#edit(cn.nvinfo.domain.Product)
	 */
	public int edit(Product product) {
		return template.update("product.edit", product);
	}
	
	 /* 
	 *  分页查询获得总数	系统管理员	杨立  2017-09-26(non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#getAllCount(java.lang.String, java.lang.String, java.lang.String, java.lang.String, double)
	 */
	public int getAllCount(Integer id,Integer logic,String name, String viewName, String viewType) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("logic", logic);
		map.put("name", name);
		map.put("viewName", viewName);
		map.put("viewType", viewType);
		return template.selectOne("product.getAllCount",map);
	}
	
	 /* 分页查询获得当前页数据	系统管理员 	杨立  2017-09-26(non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#getPageDate(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double)
	 */
	public List<ProductList> getPageDate(Integer pageIndex, Integer pageSize,
			Integer id,Integer logic,String name, String viewName, String viewType) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pageIndex", (pageIndex-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("id", id);
		map.put("logic", logic);
		map.put("name", name);
		map.put("viewName", viewName);
		map.put("viewType", viewType);
		List<ProductList> list = template.selectList("product.getPageDate", map);
		return list;
	}
	
	 /* 返回景区类别	yangli	2017-10-19(non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#getViewType()
	 */
	public List<INList> getViewType() {
		return template.selectList("product.getViewType");
	}
	
	
	 /* 分页查询  普通管理员	获得总记录数	yangli	2017-10-19(non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#getAllCount(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Integer)
	 */
	public int getAllCount(Integer id, Integer logic, String name,
			String viewName, String viewType, Integer staff_id) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("logic", logic);
		map.put("name", name);
		map.put("viewName", viewName);
		map.put("viewType", viewType);
		map.put("staff_id", staff_id);
		return template.selectOne("product.getAllCount2",map);
	}
	
	 /* 分页查询 	 普通管理员	获得数据	yangli	2017-10-19(non-Javadoc)
	 * @see cn.nvinfo.dao.ProductDao#getPageDate(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Integer)
	 */
	public List<ProductList> getPageDate(Integer pageIndex, Integer pageSize,
			Integer id, Integer logic, String name, String viewName,
			String viewType,Integer staff_id) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pageIndex", (pageIndex-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("id", id);
		map.put("logic", logic);
		map.put("name", name);
		map.put("viewName", viewName);
		map.put("viewType", viewType);
		map.put("staff_id", staff_id);
		return template.selectList("product.getPageDate2", map);
	}

}

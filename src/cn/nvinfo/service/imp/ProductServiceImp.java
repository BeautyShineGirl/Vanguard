package cn.nvinfo.service.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.nvinfo.dao.ProductDao;
import cn.nvinfo.domain.Product;
import cn.nvinfo.service.ProductService;
import cn.nvinfo.tools.INList;
import cn.nvinfo.tools.ProductList;
import cn.nvinfo.utils.Pager;

/**
 * 产品信息管理
 * @author 杨立   2017-09-25
 *
 */
@Service
@Transactional
public class ProductServiceImp implements ProductService {

	@Resource
	private ProductDao productDao;


	/*
	 * 从字典中是否可退	杨立   2017-09-25  
	 * (non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#getisCancel()
	 */
	public List<String> getIsCancel() {
		return productDao.getIsCancel();
	}

	//所属景区 	杨立   2017-09-25  
	public List<INList> getView() {
		return productDao.getView();
	}
	
	//添加	杨立   2017-09-25
	public int add(Product product) {
		return productDao.add(product);
	}
	
	/*
	 * 删除	杨立   2017-09-25(non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#delete(java.lang.Integer)
	 */
	public int delete(Integer id) {
		return productDao.delete(id);
	}
	/*
	 * 数据回显	杨立   2017-09-25(non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#getById(java.lang.Integer)
	 */
	public Product getById(Integer id) {
		return productDao.getById(id);
	}
	/*
	 * 根据id获得景区名字 杨立   2017-09-25
	 * (non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#getViewName()
	 */
	public String getViewName(Integer viewId) {
		return productDao.getViewName(viewId);
	}

	/*
	 * 修改	杨立   2017-09-25
	 * (non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#edit(cn.nvinfo.domain.Product)
	 */
	public int edit(Product product) {
		return productDao.edit(product);
	}

	/*
	 *  分页查询	杨立  2017-09-26
	 * (non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#getPager(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double)
	 */
	public Pager<ProductList> getPager(Integer pageIndex, Integer pageSize,Integer id,Integer logic,
			String name, String viewName, String viewType) {
		Pager<ProductList> pager =new Pager<ProductList>();
		pager.setCurrPage(pageIndex);
		pager.setPageSize(pageSize);
		pager.setAllCount(productDao.getAllCount(id,logic,name,viewName,viewType));
		pager.setDatas(productDao.getPageDate(pageIndex,pageSize,id,logic,name,viewName,viewType));
		pager.setPageCount();
		return pager;
	}

	/*
	 * 返回景区类别	yangli	2017-10-19(non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#getViewType()
	 */
	public List<INList> getViewType() {
		return productDao.getViewType();
	}
	
	/*
	 * 普通管理员	yangli	2017-10-19(non-Javadoc)
	 * @see cn.nvinfo.service.ProductService#getPager(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Integer)
	 */
	public Pager<ProductList> getPager(Integer pageIndex, Integer pageSize,
			Integer id, Integer logic, String name, String viewName,
			String viewType,Integer staff_id) {
		Pager<ProductList> pager =new Pager<ProductList>();
		pager.setCurrPage(pageIndex);
		pager.setPageSize(pageSize);
		pager.setAllCount(productDao.getAllCount(id,logic,name,viewName,viewType,staff_id));
		pager.setDatas(productDao.getPageDate(pageIndex,pageSize,id,logic,name,viewName,viewType,staff_id));
		pager.setPageCount();
		return pager;
	}

	
}

package cn.nvinfo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nvinfo.domain.Department;
import cn.nvinfo.domain.Power;
import cn.nvinfo.domain.Staff;
import cn.nvinfo.service.StaffService;
import cn.nvinfo.tools.StaffDictionary;
import cn.nvinfo.tools.StaffList;
import cn.nvinfo.utils.CheckUtil;
import cn.nvinfo.utils.Pager;
import cn.nvinfo.utils.Result;

/**
 *  用户管理
 * @author 杨立   2017-09-29
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("staff")
public class StaffAction {
	private static Logger log=Logger.getLogger(StaffAction.class);
	@Resource
	private StaffService staffService;
	private Integer pageSize=10;
	/**
	 *  分页查询	杨立   2017-09-29
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findPageData.action")
	public @ResponseBody Object findPageData(Integer pageIndex){
		if(!"".equals(CheckUtil.checkArgsNotNull(pageIndex))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		log.info("分页查询提交条件："+","+pageIndex);
		Pager<StaffList> pager=staffService.getPager(pageIndex,pageSize);
		log.info(new Result(1,"查询成功",pager));
		return new Result(1,"查询成功",pager);
	}
	
	/**
	 * 添加请求，返回字典数据	杨立   2017-09-29 
	 * @return
	 */
	@RequestMapping("/addUI.action")
	public @ResponseBody Object addUI(){
		//从字典中获得部门
		List<Department> dept=staffService.getdepartment();
		//从字典中获得角色
	//	List<String> role=staffService.getRole();
		//返回权限集合
		List<Power> power=staffService.getPower();
		StaffDictionary sd=new StaffDictionary();
		sd.setDept(dept);
		//sd.setRole();
		sd.setPower(power);
		log.info(new Result(1,"查询成功",sd));
		return new Result(1,"查询成功",sd);
	}
	/**
	 * 添加  杨立   2017-09-29	未测 
	 * @param view
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/add.action")
	public @ResponseBody Object add(Staff staff,Integer pageIndex){	
		String name=staff.getName();
		String loginName=staff.getLoginName();
		String department=staff.getDepartment();
		String role=staff.getRole();
		Integer powerId=staff.getPowerId();
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(name,loginName,department,role,powerId,pageIndex))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		staff.setPassword("1234");
		//检查用户名是否已存在，如果已存在，则返回用户名已存在
		List<String> ln=staffService.checkLoginName(loginName);
		for (int i = 0; i < ln.size(); i++) {
			String string = ln.get(i);
			if(string.equals(loginName)){
				log.info(new Result(0,"该用户名已存在，添加失败"));
				return new Result(0,"该用户名已存在，添加失败");
			}
		}
		log.info("添加提交条件：name="+name+", loginName="+loginName+", department="+department+", role="+role+", powerId="+powerId+", pageIndex="+pageIndex);
		if(pageIndex==0){
			pageIndex=1;
		}
		//添加操作
		int rows= staffService.add(staff);
		Pager<StaffList> pager=staffService.getPager(pageIndex,pageSize);
		List<StaffList> datas = pager.getDatas();
		if(rows>0){
			List<Object> list=new ArrayList<Object>();
			for (int i=0;i<datas.size();i++) {
				datas.get(i);
				list.add(datas.get(i).getId());
			}
			log.info(new Result(1,"添加成功","编号的集合："+list));
			return new Result(1,"添加成功",datas);
		} else {
			log.info(new Result(0,"添加失败"));
			return new Result(0,"添加失败");
		}
	}
	/**
	 * 修改数据回显  杨立  2017-09-29
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
		//根据id获得用户信息
		Staff staff=staffService.getById(id);
		//从字典中获得部门
		List<Department> dept=staffService.getdepartment();
		//返回权限集合
		List<Power> power=staffService.getPower();
		StaffDictionary sd=new StaffDictionary();
		sd.setDept(dept);
		sd.setPower(power);
		sd.setStaff(staff);
		log.info(new Result(1,"查询成功",sd));
		return new Result(1,"查询成功",sd);
	}
	/**
	 * 修改	杨立	2017-09-29
	 * 
	 */
	@RequestMapping("/edit.action")
	public @ResponseBody Object edit(Staff staff){
		Integer id=staff.getId();
		String name=staff.getName();
		String loginName=staff.getLoginName(); 
		String department=staff.getDepartment();
		String role=staff.getRole();
		Integer powerId=staff.getPowerId();
		//验证参数
		if(!"".equals(CheckUtil.checkArgsNotNull(id,name,loginName,department,role,powerId))){
			log.info(new Result(0,"参数错误"));
			return new Result(0,"参数错误");
		}
		log.info("修改提交条件："+","+name+","+loginName+","+department+","+role+","+powerId);
		//修改操作
		int rows= staffService.edit(staff);
		Staff s=staffService.getById(id);
		if(rows>0){
			log.info(new Result(1,"修改成功","修改成功的编号："+s.getId()));
			return new Result(1,"修改成功",s);
		} else {
			log.info(new Result(0,"修改失败"));
			return new Result(0,"修改失败");
		}		
	}
	/**
	 * 删除	杨立   2017-09-29
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
		//再删除人员之前，先查询该人员是否有负责的景区，若有，则返回删除失败
		int viewCount=staffService.getView(id);
		if(viewCount!=0){
			log.info(new Result(0,"删除失败，该职员有负责的景区","id="+id));
			return new Result(0,"删除失败，该职员有负责的景区");
		}
		//修改操作
		int rows= staffService.delete(id);
		if(rows>0){
			log.info(new Result(1,"删除成功","id="+id));
			return new Result(1,"删除成功");
		} else {
			log.info(new Result(0,"删除失败","id="+id));
			return new Result(0,"删除失败");
		}		
	}

	
}

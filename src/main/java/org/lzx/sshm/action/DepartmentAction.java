package org.lzx.sshm.action;

import org.lzx.sshm.domain.Department;
import org.lzx.sshm.domain.PageBean;
import org.lzx.sshm.service.DepartmentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 部门管理的Action类
 * 
 * @author Skye
 *
 */
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {
	// 模型驱动使用的对象
	private Department department = new Department();

	public Department getModel() {
		return department;
	}

	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	private DepartmentService departmentService;

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// 提供查询方法
	public String findAll() {
		System.out.println("DepartmentAction的findByPage方法执行了……");
		PageBean<Department> pageBean = departmentService.findByPage(currPage);
		// 将pageBean存入值栈，供前端页面读取
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	// 跳转到添加部门页面的方法
	public String saveUI() {
		return "saveUI";
	}

	// 添加部门
	public String save() {
		departmentService.save(department);
		return "saveSuccess";
	}

	// 编辑部门
	public String edit() {
		department = departmentService.findById(department.getDid());
		// 模型驱动的department会被直接放入值栈中，不需要像PageBean一样手工先获取值栈再push。
		return "editSuccess";
	}
	//进入编辑页面后修改部门的执行方法
	public String update(){
		departmentService.update(department);
		return "updateSuccess";
	}
	
	//删除部门执行方法
	public String delete(){
		//1、先根据id查询
		department = departmentService.findById(department.getDid());
		//2、直接以对象为参数传给service层和dao层
		departmentService.delete(department);
		return "deleteSuccess";
	}
}

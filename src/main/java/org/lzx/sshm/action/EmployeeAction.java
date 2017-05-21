package org.lzx.sshm.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.lzx.sshm.domain.Department;
import org.lzx.sshm.domain.Employee;
import org.lzx.sshm.domain.PageBean;
import org.lzx.sshm.service.DepartmentService;
import org.lzx.sshm.service.EmployeeService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 员工管理的Action类
 * 
 * @author Skye
 *
 */
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	
	// 模型驱动使用的对象
	private Employee employee = new Employee();

	public Employee getModel() {
		return employee;
	}

	private EmployeeService employeeService;
	private DepartmentService departmentService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	// 接收当前页数
	private Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public String login() {
		System.out.println("login方法执行了……");
		Employee existEmployee = employeeService.login(employee);
		if (existEmployee == null) {
			// 登录失败
			this.addActionError("用户名或密码错误！");
			return INPUT;
		} else {
			// 登录成功，将登录信息保存到session中，并转到其他页面
			ActionContext.getContext().getSession().put("existEmployee", existEmployee);
			return SUCCESS;
		}
	}

	public String findAll() {
		PageBean<Employee> pageBean = employeeService.findByPage(currPage);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	public String saveUI() {
		// 查询所有部门，用于前端选择器选择
		List<Department> list = departmentService.findAll();
		// 集合用set 对象用push
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}

	public String save() {
		employeeService.save(employee);
		return "saveSuccess";
	}

	public String edit() {
		// 根据员工id查询员工.ModelDriven的employee不需要手动保存
		employee = employeeService.findById(employee.getEid());
		// 查询所有部门
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "editUI";
	}
	
	public String update(){		
		employeeService.update(employee);
		return "updateSuccess";
	}
	
	public String delete(){
		employee=employeeService.findById(employee.getEid());
		employeeService.delete(employee);
		return "deleteSuccess";
	}


}

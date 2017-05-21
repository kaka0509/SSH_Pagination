package org.lzx.sshm.service.impl;

import java.util.List;

import org.lzx.sshm.dao.EmployeeDao;
import org.lzx.sshm.domain.Employee;
import org.lzx.sshm.domain.PageBean;
import org.lzx.sshm.service.EmployeeService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Employee login(Employee employee) {

		Employee existEmployee = employeeDao.findByUsernameAndPassword(employee);
		return existEmployee;
	}

	/**
	 * 分页查询员工的方法
	 */
	public PageBean<Employee> findByPage(Integer currPage) {
		PageBean<Employee> pageBean = new PageBean<Employee>();
		// 当前页数
		pageBean.setCurrentPage(currPage);
		// 每页记录数
		int pageSize = 3;
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		int totalCount = employeeDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装查询结果list
		int begin = (currPage - 1) * pageSize;
		List<Employee> list = employeeDao.findByPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Employee employee) {
		employeeDao.save(employee);
		
	}

	public Employee findById(Integer eid) {
		return employeeDao.findById(eid);
	}

	public void update(Employee employee) {
		employeeDao.update(employee);
		
	}

	public void delete(Employee employee) {
		employeeDao.delete(employee);
		
	}

}

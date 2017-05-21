package org.lzx.sshm.service.impl;

import java.util.List;

import org.lzx.sshm.dao.DepartmentDao;
import org.lzx.sshm.domain.Department;
import org.lzx.sshm.domain.PageBean;
import org.lzx.sshm.service.DepartmentService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DepartmentServiceImpl implements DepartmentService {


	private DepartmentDao departmentDao;
	

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}


	public PageBean<Department> findByPage(Integer currPage) {
		PageBean<Department> pageBean = new PageBean<Department>();
		//封装当前页数
		pageBean.setCurrentPage(currPage);
		//封装每页显示记录数
		int pageSize = 3;		
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount  = departmentDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		//封装每页显示数据
		int begin = (currPage-1)*pageSize;
		List<Department> list = departmentDao.findByPage(begin,pageSize);
		pageBean.setList(list);		
		return pageBean;
	}


	public void save(Department department) {
		departmentDao.save(department);
		
	}



	public Department findById(Integer did) {
		return departmentDao.findById(did);
	}


	public void update(Department department) {
		departmentDao.update(department);
		
	}


	public void delete(Department department) {
		departmentDao.delete(department);		
	}


	//查询所有部门
	public List<Department> findAll() {
		return departmentDao.findAll();
	}




}

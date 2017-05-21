package org.lzx.sshm.service;

import java.util.List;

import org.lzx.sshm.domain.Department;
import org.lzx.sshm.domain.PageBean;

/**
 * 员工管理的业务层的接口
 * 
 * @author Skye
 *
 */

public interface DepartmentService {

	//分页查询部门的方法
	PageBean<Department> findByPage(Integer currPage);

	void save(Department department);

	
	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();
	


}

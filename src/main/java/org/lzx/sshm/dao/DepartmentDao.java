package org.lzx.sshm.dao;

import java.util.List;

import org.lzx.sshm.domain.Department;

/**
 * 员工管理的DAO接口
 * @author Skye
 *
 */
public interface DepartmentDao {

	int findCount();

	List<Department> findByPage(int begin, int pageSize);

	void save(Department department);

	Department findById(Integer did);

	void update(Department department);

	void delete(Department department);

	List<Department> findAll();	



}

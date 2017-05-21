package org.lzx.sshm.dao;

import java.util.List;

import org.lzx.sshm.domain.Employee;

/**
 * 员工管理的DAO接口
 * @author Skye
 *
 */
public interface EmployeeDao {
	

	Employee findByUsernameAndPassword(Employee employee);

	int findCount();

	List<Employee> findByPage(int begin, int pageSize);

	void save(Employee employee);

	Employee findById(Integer eid);

	void update(Employee employee);

	void delete(Employee employee);

}

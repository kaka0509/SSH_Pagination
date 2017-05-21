package org.lzx.sshm.service;

import org.lzx.sshm.dao.ProductDao;
import org.lzx.sshm.domain.Employee;
import org.lzx.sshm.domain.PageBean;
import org.lzx.sshm.domain.Product;
import org.springframework.transaction.annotation.Transactional;

/**
 * 员工管理的业务层的接口
 * 
 * @author Skye
 *
 */

public interface EmployeeService {

	Employee login(Employee employee);

	PageBean<Employee> findByPage(Integer currPage);

	void save(Employee employee);

	Employee findById(Integer eid);

	void update(Employee employee);

	void delete(Employee employee);

}

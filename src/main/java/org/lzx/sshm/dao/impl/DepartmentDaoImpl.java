package org.lzx.sshm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.lzx.sshm.dao.DepartmentDao;
import org.lzx.sshm.domain.Department;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * 员工DAO接口的实现类
 * 
 * @author Skye
 *
 */
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

	/**
	 * 查询总记录数
	 */
	public int findCount() {
		String hql = "select count(*) from Department";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		if (list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 分页查询部门
	 */
	public List<Department> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		List<Department> list = (List<Department>) this.getHibernateTemplate().findByCriteria(criteria, begin,
				pageSize);
		return list;
	}

	public void save(Department department) {
		this.getHibernateTemplate().save(department);
	}

	public Department findById(Integer did) {
		return this.getHibernateTemplate().get(Department.class, did);
	}

	public void update(Department department) {
		this.getHibernateTemplate().update(department);

	}

	public void delete(Department department) {
		this.getHibernateTemplate().delete(department);
	}

	public List<Department> findAll() {
		return (List<Department>) this.getHibernateTemplate().find("from Department");
	}

}

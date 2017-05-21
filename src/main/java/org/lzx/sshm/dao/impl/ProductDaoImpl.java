package org.lzx.sshm.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.lzx.sshm.dao.ProductDao;
import org.lzx.sshm.domain.Product;
import org.lzx.sshm.vo.Pager;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * 商品管理的Dao实现类
 * 
 * @author Skye
 *
 */
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {

	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	public Pager<Product> findByPage(Product searchModel, int pageNum, int pageSize) {
		System.out.println("dao层方法启动");
		// 声明结果集
		Pager<Product> result = null;

		// 存放查询参数
		Map<String, Object> paramMap = new HashMap<String, Object>();

		String proName = searchModel.getPname();
		Double price = searchModel.getPrice();

		StringBuilder hql = new StringBuilder("from Product where 1=1");
		StringBuilder countHql = new StringBuilder("select count(pid) from Product where 1=1 ");

		if (proName != null && !proName.equals("")) {
			hql.append(" and pname like :proName ");
			countHql.append("  and pname like :proName ");
			paramMap.put("proName", "%" + proName + "%");
		}

		if (price != null && !price.equals("")) {
			hql.append(" and price = :price ");
			countHql.append(" and price = :price ");
			paramMap.put("price", price);
		}

		// 起始索引
		int fromIndex = pageSize * (pageNum - 1);

		// 存放所有查询出的商品对象
		List<Product> productList = new ArrayList<Product>();

		Session session = null;

		// 获取被Spring托管的session
		session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();

		// 获取query对象
		Query hqlQuery = session.createQuery(hql.toString());
		Query countHqlQuery = session.createQuery(countHql.toString());

		// 设置查询参数
		setQueryParams(hqlQuery, paramMap);
		setQueryParams(countHqlQuery, paramMap);

		// 从第几条记录开始查询
		hqlQuery.setFirstResult(fromIndex);

		// 一共查询多少条记录
		hqlQuery.setMaxResults(pageSize);

		// 获取查询的结果
		productList = hqlQuery.list();

		// 获取总记录数
		List<?> countResult = countHqlQuery.list();
		int totalRecord = ((Number) countResult.get(0)).intValue();

		// 获取总页数
		int totalPage = totalRecord / pageSize;
		if (totalRecord % pageSize != 0) {
			totalPage++;
		}

		// 组装pager对象
		result = new Pager<Product>(pageSize, pageNum, totalRecord, totalPage, productList);
		System.out.println("dao层方法运行结束");
		return result;
	}

	/**
	 * 设置查询的参数
	 * 
	 * @param query
	 * @param paramMap
	 * @return
	 */
	private Query setQueryParams(Query query, Map<String, Object> paramMap) {
		if (paramMap != null && !paramMap.isEmpty()) {
			for (Map.Entry<String, Object> param : paramMap.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		return query;
	}
}

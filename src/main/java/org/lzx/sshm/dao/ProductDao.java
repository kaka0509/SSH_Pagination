package org.lzx.sshm.dao;

import org.lzx.sshm.domain.Product;
import org.lzx.sshm.vo.Pager;

/**
 * 商品管理的Dao
 * 
 * @author Skye
 *
 */
public interface ProductDao  {

	
	void save(Product product);
	
	/**
	 * 根据查询条件，查询商品分页信息
	 * 
	 * @param searchModel
	 *            封装查询条件
	 * @param pageNum
	 *            查询第几页数据
	 * @param pageSize
	 *            每页显示多少条记录
	 * @return 查询结果
	 */
	Pager<Product> findByPage(Product searchModel, int pageNum,
			int pageSize);
	

}

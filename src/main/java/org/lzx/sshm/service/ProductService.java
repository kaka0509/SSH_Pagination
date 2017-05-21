package org.lzx.sshm.service;

import org.lzx.sshm.domain.Product;
import org.lzx.sshm.vo.Pager;

/**
 * 商品管理的业务层的类
 * 
 * @author Skye
 *
 */

public interface ProductService {

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

	Pager<Product> findByPage(Product searchModel, int pageNum, int pageSize);

}

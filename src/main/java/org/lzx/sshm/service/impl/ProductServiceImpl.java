package org.lzx.sshm.service.impl;

import org.lzx.sshm.dao.ProductDao;
import org.lzx.sshm.domain.Product;
import org.lzx.sshm.service.ProductService;
import org.lzx.sshm.vo.Pager;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public Pager<Product> findByPage(Product searchModel, int pageNum, int pageSize) {
		System.out.println("service层方法启动");
		Pager<Product> result = productDao.findByPage(searchModel, pageNum, pageSize);
		return result;
	}

}

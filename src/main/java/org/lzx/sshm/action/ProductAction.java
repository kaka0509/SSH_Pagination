package org.lzx.sshm.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.lzx.sshm.domain.Product;
import org.lzx.sshm.service.ProductService;
import org.lzx.sshm.vo.Pager;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品管理的Action控制器类
 * 
 * @author Skye
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	// 模型驱动需要使用的实体类
	private Product product = new Product();

	public Product getModel() {
		return product;
	}
	
	private String responseStr;	

	public String getResponseStr() {
		return responseStr;
	}

	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}

	// Struts和Spring整合过程中按名称自动注入的业务层类
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 提供查询方法
	public String findAll() {
		System.out.println("控制器方法启动");
		// 使用struts2的servlet接口，接收request里的参数
		// 商品名字参数
		HttpServletRequest request = ServletActionContext.getRequest();
		String proName = request.getParameter("proName");
		// 获取价格
		Double price = null;
		String priceStr = request.getParameter("price");
		if (priceStr != null && !"".equals(priceStr.trim())) {
			price = Double.parseDouble(priceStr);
		}

		// 校验pageNum参数输入合法性
		String pageNumStr = request.getParameter("pageNum");
		System.out.println("前端给的pageNum是："+pageNumStr);

		int pageNum = 1; // 默认显示第几页数据
		if (pageNumStr != null && !"".equals(pageNumStr.trim())) {
			pageNum = Integer.parseInt(pageNumStr);
		}

		int pageSize = 5; // 默认每页显示多少条记录
		String pageSizeStr = request.getParameter("pageSize");
		if (pageSizeStr != null && !"".equals(pageSizeStr.trim())) {
			pageSize = Integer.parseInt(pageSizeStr);
		}

		// 组装模糊查询条件
		Product searchModel = new Product();
		searchModel.setPname(proName);
		searchModel.setPrice(price);
		System.out.println("==============Product对象==============");
		System.out.println(searchModel);
		// 调用service 获取查询结果
		Pager<Product> result = productService.findByPage(searchModel, pageNum, pageSize);

		// 将pageBean存入值栈，供前端页面读取		
		ActionContext.getContext().getValueStack().push(result);
		// 将查询条件也压回值栈，在初始化函数中为其初始化
		ActionContext.getContext().getValueStack().push(searchModel);
		System.out.println("==============Pager对象==============");
		System.out.println(result);
		System.out.println("控制器方法完成");
		return "findAll";
	}
	
	
	public String findAllJSON() {
		// 使用struts2的servlet接口，接收request里的参数
		// 商品名字参数
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String proName = request.getParameter("proName");
		// 获取价格
		Double price = null;
		String priceStr = request.getParameter("price");
		if (priceStr != null && !"".equals(priceStr.trim())) {
			price = Double.parseDouble(priceStr);
		}

		// 取得页面索引
		String pageNumStr = request.getParameter("pageNum");
	

		int pageNum = 1; // 默认显示第几页数据
		if (pageNumStr != null && !"".equals(pageNumStr.trim())) {
			pageNum = Integer.parseInt(pageNumStr);
		}

		int pageSize = 5; // 默认每页显示多少条记录
		String pageSizeStr = request.getParameter("pageSize");
		if (pageSizeStr != null && !"".equals(pageSizeStr.trim())) {
			pageSize = Integer.parseInt(pageSizeStr);
		}

		// 组装模糊查询条件
		Product searchModel = new Product();
		searchModel.setPname(proName);
		searchModel.setPrice(price);
		// 调用service 获取查询结果
		Pager<Product> result = productService.findByPage(searchModel, pageNum, pageSize);
		// 将查询结果封装成JSON字符串格式	
		responseStr = JSONObject.toJSONString(result);
		System.out.println(responseStr);
		// 利用response对象传回前端
		response.setHeader("Cache-Control", "no-cache");  
		response.setHeader("Pragma", "no-cache");  
		response.setDateHeader("Expires", 0);  
		response.setContentType("text/html;charset=utf-8");		
		try {
			Writer writer = response.getWriter();
			writer.write(responseStr);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
}

package org.lzx.sshm.domain;

import java.util.List;

/**
 * 分页封装的bean
 * 
 * @author Skye
 * @param <T>
 *
 */
public class PageBean<T> {
	// 当前页数
	private int currentPage;
	// 每页显示
	private int pageSize;
	// 总记录数
	private int totalCount;
	// 总页数
	private int totalPage;
	// 每页显示的数据集合
	private List<T> list;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}

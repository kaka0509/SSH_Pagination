<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.table1 {
	border: 1px solid #ddd;
	width: 900px;
}

thead {
	background-color: lightblue;
}
</style>
</head>
<body>
	<table border="0" width="900px">
		<tr>
			<td align="center" style="font-size: 24px; color: #999">商品显示</td>
		</tr>
	</table>

	<br />

	<table cellspacing="0" border="1" class="table1">
		<thead>
			<tr>
				<td align="center">商品编号</td>
				<td align="center">商品名称</td>
				<td align="center">商品价格</td>
			</tr>
		</thead>
		<s:iterator value="dataList" var="p">
			<tbody>
				<tr>
					<td align="center"><s:property value="#p.pid" /></td>
					<td align="center"><s:property value="#p.pname" /></td>
					<td align="center"><s:property value="#p.price" /></td>
				</tr>
			</tbody>
		</s:iterator>
	</table>
	<br />

	<!-- 因为值栈里面有pageBean存在，所以可以直接使用属性名访问其属性 -->
	<table border="0" cellspacing="0" cellpadding="0" width="900px">
		<tr>
			<td align="right"><span>第<s:property value="currentPage" />/<s:property
						value="totalPage" />页
			</span>&nbsp;&nbsp; <span>总记录数： <s:property value="totalRecord" />&nbsp;&nbsp;每页显示：<s:property
						value="pageSize" /></span>&nbsp;&nbsp; <span> <s:if
						test="currentPage != 1">
						<a
							href="${pageContext.request.contextPath}/product_findAll.action?pageNum=1">[首页]</a>&nbsp;&nbsp;
			<a
							href="${pageContext.request.contextPath}/product_findAll.action?pageNum=<s:property value = "currentPage-1"/>">[上一页]</a>&nbsp;&nbsp; 
			</s:if> <s:if test="currentPage != totalPage">
						<a
							href="${pageContext.request.contextPath}/product_findAll.action?pageNum=<s:property value = "currentPage+1"/>">[下一页]</a>&nbsp;&nbsp;
			<a
							href="${pageContext.request.contextPath}/product_findAll.action?pageNum=<s:property value = "totalPage"/>">[尾页]</a>&nbsp;&nbsp; 
			</s:if>
			</span></td>
		</tr>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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


<%
	// 获取请求的上下文
	String context = request.getContextPath();
%>
<link href="${pageContext.request.contextPath}/css/pagination.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pagination.js"></script>
<script type="text/javascript">
// 点击分页按钮以后触发的动作
function handlePaginationClick(new_page_index, pagination_container) {
	$("#proForm").attr("action", "<%=context %>/product_findAll.action?pageNum=" + (new_page_index +1));
	$("#proForm").submit();
	return false;
}

//初始化函数
$(function(){
	$("#News-Pagination").pagination(${totalRecord}, {
        items_per_page:${pageSize}, // 每页显示多少条记录
        current_page:${currentPage} - 1, // 当前显示第几页数据
        num_display_entries:2, // 连续分页主体显示的条目数
        next_text:"下一页",
        prev_text:"上一页",
        num_edge_entries:2, // 连接分页主体，显示的条目数
        callback:handlePaginationClick, //执行的回调函数，也就是去获取新的分页数据
        load_first_page:false //防止页面一直刷新( 这条非常重要！)        
	});	
	// 初始化时就获得后台发过来的前一次的查询参数
	$("#pro_name").val("${pname}");
	$("#pro_price").val("${price}");
	
});
</script>


<body>
	<div>
		<form action="<%=context %>/product_findAll.action" id="proForm" method="post">
			商品名称 <input type="text" name="proName" id="pro_name" style="width: 120px" > &nbsp; 
			商品价格 <input type="text" name="price" id="pro_price" style="width: 120px" > &nbsp; 
		<input type="submit" value="查询">
		</form>
	</div>
<c:if test="${fn:length(dataList) gt 0 }">
<table border="1px" cellspacing="0px"
				style="border-collapse: collapse">
				<thead>
					<tr height="30">
				<td align="center">商品编号</td>
				<td align="center">商品名称</td>
				<td align="center">商品价格</td>
					</tr>
				</thead>
					<c:forEach items="${dataList}" var="p">
						<tr>
							<td><c:out value="${p.pid }"></c:out></td>
							<td><c:out value="${p.pname }"></c:out></td>
							<td><c:out value="${p.price }"></c:out></td>
						</tr>
					</c:forEach>
			</table>
			<br> 
			<div id="News-Pagination"></div>
	</c:if>
	
	<div>后台传来的当前页:${currentPage}</div>
</body>
</html>
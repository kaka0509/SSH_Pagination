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
<link href="${pageContext.request.contextPath}/css/pagination.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.pagination.js"></script>
<script type="text/javascript">
$(function(){
	Query();	
});

//直接自定义一个button来发起查询
function Query(){	
	$("#btn2").click(function(){
		//根据ID从表单中获取查询需要的参数
		var pname = $("#pro_name").val(); 	
		var price = $("#pro_price").val();
		var pageIndex = $("#pageNum").val();
		//异步方式提交查询
	     $.ajax({   
	                type: "POST",
	                //返回结果转换成json,方便用data.属性的方式取值
	                dataType: "json",  
	                url: "<%=context%>/product_findAllJSON.action" ,   
	                data: "pageNum="+ pageIndex +"&proName=" + pname + "&price=" + price,  
	                success: function(result) { 
	                	//加载结果的JSON字符串
						var totalRecord = result.totalRecord;
						var currentPage = result.currentPage; // 获取当前第几页数据
						var productList = result.dataList; // 学生记录信息
						$("#div2").html(result)
	                	//生成表格内容					
						showProductData(productList);
	                        }  
	           }); 
		
		});
}

function FormSubmit(){
	//JQuery使用这种方式提交的时候 $.ajax不生效，会直接用原始submit刷新对象
	//需要装JQuery-Form插件
	$("#proForm").submit(function(e){
		
	});
}

	//加载学生的数据
	function showProductData(proList) {
		//每次加载新页面清空原来的内容
		$("#proDataBody").empty();
		var proDataHtml = "";
		$.each(proList, function(idx, obj) {
			proDataHtml += "<tr>";
			proDataHtml += "<td>" + obj.pid + "</td>"; // 获取商品编号的值
			proDataHtml += "<td>" + obj.pname + "</td>"; // 获取商品名称的值
			proDataHtml += "<td>" + obj.price + "</td>"; // 获取商品价格的值
			proDataHtml += "</tr>";
		});
		$("#proDataBody").append(proDataHtml);
	}
</script>


<body>
	<div>
		<form id="proForm">
			商品名称 <input type="text" name="proName" id="pro_name"
				style="width: 120px"> &nbsp; 商品价格 <input type="text"
				name="price" id="pro_price" style="width: 120px"> &nbsp;
			查询页码 <input type="text" name="pageNum" id="pageNum"
				style="width: 120px"> &nbsp; <br> 
		</form>
	<button id="btn2">测试</button>
	</div>
	

	
	<div id="div1">
		<h2>商品信息</h2>
	</div>
	<div id="div2">
		<h2>显示datalist</h2>
	</div>
	<br>
	<!-- 后台返回结果不为空 -->
	<table border="1px" cellspacing="0px" style="border-collapse: collapse"
		id="ProductTable">
		<thead>
			<tr height="30">
				<th width="130">商品编号</th>
				<th width="130">商品名称</th>
				<th width="130">商品价格</th>
			</tr>
		</thead>
		<tbody id="proDataBody">
		</tbody>
	</table>


</body>
</html>
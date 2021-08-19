<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%--静态包含base标签，css引入、jquery引入--%>
<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteBtn").click(function (){
				return confirm("您确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗？");
			});
			$("#clear").click(function (){
				return confirm("您确定要清空购物车吗？");
			});
			//给输入框绑定失去焦点事件
			$(".updateCount").change(function (){
				var id = $(this).attr("bookId");
				var name = $(this).parent().parent().find("td:first").text();
				var count = this.value;
				if(confirm("确定将【"+ name + "】的数量修改为" + count + "吗？")){
					//发起请求给服务器保存修改
					location.href = "${basePath}cartServlet?action=updateItem&id=" + id + "&count=" + count;
				}else{
					//defaultValue为表单项dom对象的属性。表示默认的value属性值。
					this.value = this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5">
						<a href="client/bookServlet?action=page">亲，当前购物车为空！ 去浏览商品</a>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items.entrySet()}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 80px;text-align: center" type="text" bookId="${entry.value.id}" value="${entry.value.count}"/>
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteBtn" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clear" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	
	</div>
	<%--静态包含页脚部分--%>
	<%@include file="/pages/common/footer.jsp"%>>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%--静态包含base标签，css引入、jquery引入--%>
<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>

			<c:if test="${empty sessionScope.orders}">
				<tr>
					<td colspan="4">暂无订单</td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.orders}">
				<c:forEach items="${sessionScope.orders}" var="order">
					<tr>
						<td>${order.createTime}</td>
						<td>${order.price}</td>
						<td>
							<c:choose>
								<c:when test="${order.status == 0}">
									未发货
								</c:when>
								<c:when test="${order.status == 1}">
									已发货
								</c:when>
								<c:when test="${order.status == 2}">
									已签收
								</c:when>
							</c:choose>
						</td>
						<td><a href="orderServlet?action=orderDetails&condition=client&status=${order.status}&orderId=${order.orderId}">查看详情</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>

	
	</div>

	<%--静态包含页脚部分--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>
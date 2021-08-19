<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Quensty
  Date: 2021/8/6
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>orderDetails</title>
    <%--静态包含base标签，css引入、jquery引入--%>
    <%@include file="/pages/common/head.jsp"%>
    <link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap3.3.7.css"/>
    <link rel="stylesheet" type="text/css" href="static/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
        <table class="mx-auto table table-bordered table-striped">
            <tr class="text-center">
                <td>订单号</td>
                <td>序号</td>
                <td>名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>合计</td>
            </tr>
            <c:forEach items="${requestScope.orderDetails}" var="detail">
                <tr class="text-center">
                    <td>${detail.orderId}</td>
                    <td>${detail.id}</td>
                    <td>${detail.name}</td>
                    <td>${detail.count}</td>
                    <td>${detail.price}</td>
                    <td>${detail.totalPrice}</td>
                </tr>
            </c:forEach>
            <tr>
                <c:if test="${param.condition == \"client\"}">
                    <td class="text-center" colspan="2"><a class="btn btn-primary"  href="orderServlet?action=myOrders">返回订单</a></td>
                    <c:choose>
                        <c:when test="${param.status == 0}">
                            <td class="text-center text-secondary" colspan="4">未发货</td>
                        </c:when>
                        <c:when test="${param.status == 1}">
                            <td class="text-center" colspan="4"><a class="btn btn-danger" href="orderServlet?action=receiveOrder&orderId=${requestScope.orderId}">确认收货</a></td>
                        </c:when>
                        <c:when test="${param.status == 2}">
                            <td class="text-center text-secondary" colspan="4">已签收</td>
                        </c:when>
                    </c:choose>
                </c:if>
                <c:if test="${param.condition == \"manager\"}">
                    <td class="text-center" colspan="6"><a class="btn btn-primary"  href="manager/orderServlet?action=allOrders">返回订单</a></td>
                </c:if>
            </tr>
        </table>
    </div>
    <%@include file="/pages/common/footer.jsp"%>

</body>
</html>

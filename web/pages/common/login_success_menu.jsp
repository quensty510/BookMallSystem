<%--
  Created by IntelliJ IDEA.
  User: Quensty
  Date: 2021/8/3
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="orderServlet?action=myOrders">我的订单</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>--%>
<div>
    <%--若用户未登录，显示登录注册菜单--%>
    <c:if test="${empty sessionScope.user}">
        <a href="pages/user/login.jsp">登录</a>
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
    </c:if>
    <%--若用户登录成功，显示登录成功之后的菜单--%>
    <c:if test="${not empty sessionScope.user}">
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="orderServlet?action=myOrders">我的订单</a>
        <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </c:if>
</div>

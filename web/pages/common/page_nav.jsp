<%--
  Created by IntelliJ IDEA.
  User: Quensty
  Date: 2021/8/5
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="position: absolute;bottom: 5%" id="page_nav">
    <%--如果已经是第一页，不显示首页和上一页--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
        <%--<a href="#">${(requestScope.page.pageNo - 1)}</a>--%>
    </c:if>

    <%--【${requestScope.page.pageNo}】--%>
    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：总页码小于等于5，页码的范围是 1~总页码 --%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <%--针对不同情况记录begin和end的值--%>
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>

        <%--情况2：总页码大于5的情况，以总页码为10举例--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况①：当前页码为前面三个:1,2,3，页码范围是1-5--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <%--小情况②：当前页码为最后 3 个，8，9，10，页码范围是：总页码减 4 - 总页码--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>
                <%--小情况③：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"></c:set>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <%--用记录下的begin和end的值执行forEach--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${requestScope.page.pageNo}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>


    <%--若如已经到最后一页，不显示下一页及末页--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <%--<a href="#">${requestScope.page.pageNo + 1}</a>--%>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，
    ${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页

    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function (){
            $("#searchPageBtn").click(function (){
                var pageNo =  $("#pn_input").val();
                //检测页码是否合法
                if(pageNo < 1){
                    pageNo = 1;
                }else if(pageNo > ${requestScope.page.pageTotal}){
                    pageNo = ${requestScope.page.pageTotal};
                }
                /*JavaScript语言中提供了一个location地址栏对象
                * 它有一个属性href，可以获取浏览器地址栏中的地址
                * href属性可读，可写
                * */
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>
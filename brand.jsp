<%--
  Created by IntelliJ IDEA.
  User: 魔龙骑士
  Date: 2022/4/24
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>
<input type="button" value="新增" id="add"><br>
<hr>
<table border="1" cellpadding="0" width="800">
    <tr>
        <td>序号</td>
        <td>品牌名称</td>
        <td>企业品牌</td>
        <td>排序</td>
        <td>品牌介绍</td>
        <td>状态</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${brands}" var="brand" varStatus="status">
        <tr align="center">
            <td>${brand.id}</td>
            <td>${brand.brandName}</td>
            <td>${brand.companyName}</td>
            <td>${brand.ordered}</td>
            <td>${brand.description}</td>
            <c:if test="${brand.status == 1}">
                <td>启用</td>
            </c:if>
            <c:if test="${brand.status != 1}">
                <td>禁用</td>
            </c:if>
            <td><a href="/example-BrandShow/selectById?id=${brand.id}">修改</a> <a href="#">删除</a></td>
        </tr>
    </c:forEach>
</table>
<script>
    document.getElementById("add").onclick = function () {
        location.href = "/example-BrandShow/add.jsp";
    }
</script>
</body>
</html>

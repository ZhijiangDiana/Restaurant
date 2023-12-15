<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>留言板</title>

    <link rel="stylesheet" href="styles.css"> <!-- 引入外部CSS文件 -->

</head>
<body>

<div class="container">
    <c:forEach items="${sessionScope.commentList}" var="comment">
       ${comment.title}
    </c:forEach>
    aszfdkjasdziklofjhol
</div>
</body>
</html>

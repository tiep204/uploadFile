<%--
  Created by IntelliJ IDEA.
  User: tiepd
  Date: 15/08/2023
  Time: 4:20 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload/image" method="post" enctype="multipart/form-data">
  <input type="file" name="image" multiple>
  <button type="submit">bam</button>
</form>
</body>
</html>

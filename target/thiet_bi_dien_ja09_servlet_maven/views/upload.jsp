<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 8/11/2020
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Upload file</title>
</head>
<body>
    <h1>Demo Servlet upload file</h1>

    <form method="post" action="/upload-file" enctype="multipart/form-data">
        <label>Select file to upload : </label>
        <input type="file" name="file" multiple="multiple"/> <br>
        <input type="submit" value="Upload"/>

    </form>

</body>
</html>

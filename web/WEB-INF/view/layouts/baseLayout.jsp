<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
    <head>
        <title><tiles:getAsString name="title"/></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>
    <body>
        <div>
            <!-- navBar -->
            <tiles:insertAttribute name="navbar"/>
            <!-- header -->
            <tiles:insertAttribute name="header"/>
            <!-- body -->
            <tiles:insertAttribute name="body"/>
            <!-- footer -->
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>

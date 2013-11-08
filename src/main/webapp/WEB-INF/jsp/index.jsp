<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todooz</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

    <style type="text/css">
        body {
            margin: 5px 0 50px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="navbar navbar-default">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Todooz</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <form action="/search" class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" name="query" class="form-control" placeholder="Search">
                </div>
            </form>
            <a href="/add" class="btn btn-default navbar-btn navbar-right">
                <span class="glyphicon glyphicon-plus"></span>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-7 col-lg-offset-1">
            <legend>All tasks</legend>

            <c:forEach var="task" items="${tasks}">
                <div>
                    <p><fmt:formatDate value="${task.date}" pattern="dd MMM yyyy"/></p>
                    <span class="lead">${task.title}</span>
                    <c:forEach var="tag" items="${task.tagArray}">
                        <code>${tag}</code>
                    </c:forEach>
                    <p>${task.text}</p>
                </div>
            </c:forEach>

        </div>
        <div class="col-lg-3">
            <div class="panel panel-default">
                <div class="panel-heading">Quick links</div>
                <div class="panel-body">
                    <ul>
                        <li><a href="/today">Today's</a></li>
                        <li><a href="/tomorrow">Tomorrow's</a></li>
                    </ul>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Tags</div>
                <div class="panel-body">
                    <a href="/tag/java" style="font-size:14px">java</a>
                    <a href="/tag/java" style="font-size:20px">java</a>
                    <a href="/tag/java" style="font-size:16px">java</a>
                    <a href="/tag/java" style="font-size:12px">java</a>
                    <a href="/tag/java" style="font-size:10px">java</a>
                    <a href="/tag/java" style="font-size:22px">java</a>
                    <a href="/tag/java" style="font-size:12px">java</a>
                    <a href="/tag/java" style="font-size:14px">java</a>
                    <a href="/tag/java" style="font-size:18px">java</a>
                    <a href="/tag/java" style="font-size:24px">java</a>
                    <a href="/tag/java" style="font-size:12px">java</a>
                    <a href="/tag/java" style="font-size:10px">java</a>
                    <a href="/tag/java" style="font-size:14px">java</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="websocket.db.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="websocket.dao.impl.Student" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="http://v3.bootcss.com/favicon.ico">

  <title>Starter Template for Bootstrap</title>

  <!-- Bootstrap core CSS -->
  <link href="inuse_files/bootstrap.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="inuse_files/starter-template.css" rel="stylesheet">

  <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
  <!--[if lt IE 9]>
  <script src="../../assets/js/ie8-responsive-file-warning.js"></script>
  <![endif]-->
  <script src="inuse_files/ie-emulation-modes-warning.js"></script>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<style>
  .panel .panel-default{
    width: 20%;
  }

</style>
<body>

  <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">后台管理</a>
      </div>
      <div id="navbar" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
          <li class="active">
            <a href="#">Home</a>
          </li>
          <li>
            <a href="#about">About</a>
          </li>
          <li>
            <a href="#contact">Contact</a>
          </li>
        </ul>
      </div>
      <!--/.nav-collapse --> </div>
  </nav>
<div class="alert alert-success" role="alert">
        <strong id="connetionMessage">Well done!</strong>
      </div>
  <div class="container">

    <div class="starter-template">

        <div class="form-group">
        <button type="submit" class="btn btn-default" onclick="send()">下一个</button>
        </div>
    </div>
    
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">报名表单</h3>
    </div>
    <div class="panel-body">
        
    </div>
    <table class="table .table-condensed">
    		<tr>
	    		<td>ID</td>
	    		<td>姓名</td>
	    		<td>是否答辩</td>
    		</tr>
    <%
    	DBOption db = new DBOption();
    	List<Student> list = db.list();
    	Student student = null;
    	for(int i = 0; i < list.size(); i++ ){
    		student = list.get(i);
    	
    	
    %>
    	<tr>
    		<td><%=student.getId() %></td>
    		<td><%=student.getName() %></td>
    		<td><%=student.getIsselect() %></td>
    		<td><a href="Update?id=<%=student.getId() %>&option=2">置顶</a></td>
    		<td><a href="Update?id=<%=student.getId() %>&option=1">上</a></td>
    		<td><a href="Update?id=<%=student.getId() %>&option=0">下</a></td>
    	</tr>
	    <%
	    	}
	    %>
    </table>
</div>


  </div>
  <!-- /.container -->

  <!-- Bootstrap core JavaScript
    ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="inuse_files/jquery.js"></script>
  <script src="inuse_files/bootstrap.js"></script>
  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <script src="inuse_files/ie10-viewport-bug-workaround.js"></script>
  
</body>
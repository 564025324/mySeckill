<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>秒杀列表页</title>
<%@ include file="common/head.jsp"%>
</head>

<body>
	<!-- 页面部分 -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>秒杀列表</h2>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>名称</th>
							<th>库存</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>详情页</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="sk" items="${list}">
						<tr>
							<td>${sk.name}</td>
							<td>${sk.number}</td>
							<td><fmt:formatDate value="${sk.starttime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${sk.endtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<%--<td><fmt:formatDate value="${sk.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
							<td><a class="btn btn-info" href="/mySeckill/seckill/${sk.seckillId}/detail" target="_blank">link</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>

<!--  jQuery文件，务必在bootcss.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.js"></script>
<!-- 最新的 Bootstrap 核心javascript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
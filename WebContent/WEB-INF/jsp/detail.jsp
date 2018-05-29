<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>秒杀产品信息页</title>
<%@ include file="common/head.jsp"%>

<link rel="stylesheet" href="/mySeckill/resource/css/font.css" type="text/css"/>
</head>

<body>
	<!-- 页面部分 -->
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>${seckill.name }</h1>
			</div>
			<div class="personFontStyleColorRed"><h2>测试css</h2></div>
			<div class=""><h2>测试css2</h2></div>
			
			<div class="panel-body">
				<h2 class="text-danger">
					<!-- 显示time图标 -->
					<span class="glyphicon glyphicon-time"></span>
					<!-- 显示倒计时 -->
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>
	<!-- 登陆弹出层，输入电话 -->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>秒杀电话:
					</h3>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" id="killPhoneKey" placeholder="请填写手机号"
								class="form-control">
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<!-- 验证信息 -->
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span> submit
					</button>
				</div>

			</div>
		</div>
	</div>
</body>

<!--  jQuery文件，务必在bootcss.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.js"></script>
<!-- 最新的 Bootstrap 核心javascript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery cookie操作插件 -->
<script
	src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jQuery countDown 倒计时插件 -->
<script
	src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script src="/mySeckill/resource/script/seckill.js"
	type="text/javascript"></script>
<!-- 开始编写交互逻辑 -->
<script type="text/javascript">
	$(function() {
		//使用 EL 表达式传入参数
		seckill.detail.init({
			seckill_id : ${seckill.seckillId },
			start_time : ${seckill.starttime.time },
			end_time : ${seckill.endtime.time }
		})
	});
</script>

=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@ include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>秒杀产品信息页</title>
<%@ include file="common/head.jsp"%>
</head>

<body>
	<!-- 页面部分 -->
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>${seckill.name }</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<!-- 显示time图标 -->
					<span class="glyphicon glyphicon-time"></span>
					<!-- 显示倒计时 -->
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>
	<!-- 登陆弹出层，输入电话 -->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>秒杀电话:
					</h3>
				</div>
				
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" id="killphoneKey" name="killphoneKey" placeholder="请填写手机号" class="form-control"/>
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
				<!-- 验证信息 -->
				<span id="killPhoneMessage" class="glyphicon"></span>
				<button type="button" id="killPhoneBtn" class="btn btn-success">
					<span class="glyphicon glyphicon-phone"></span>
					submit
				</button>
				</div>
				
			</div>
		</div>
	</div>
</body>

<!--  jQuery文件，务必在bootcss.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.js"></script>
<!-- 最新的 Bootstrap 核心javascript 文件 -->
<script	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery cookie操作插件 -->
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jQuery countDown 倒计时插件 -->
<script src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<!-- <script type="text/javascript" src="/resource/script/test.js"></script> -->
<script src="/seckill/resource/script/seckill.js" type="text/javascript"></script>
<!-- 开始编写交互逻辑 -->
<script type="text/javascript">
	$(function() {
		//使用 EL 表达式传入参数
		seckill.detail.init({
			seckillId : ${seckill.seckillId}
// 		,
// 			startTime : ${seckill.starttime}, // 通过seckill.startTime.time 可以拿到毫秒的时间
// 			endTime ：${seckill.endtime}
		})
	});
</script>

>>>>>>> parent of 3dfc53f... 因项目名修改对，路径进行部分调整。
</html>
// 存放主要交互逻辑js代码
// javascript 模块化
var seckill = {
	// 封装 秒杀相关ajax的url
	URL : {
		now : function(){
			return '/seckill/time/now';
		}
	},
	validatePhone : function(phone) {
		if (phone != null && phone.length == 11 && !isNaN(phone) && phone.charAt(0)=='1' ){
			return true;
		} else {
			return false;
		}
	},
	countDown:function(seckillId,nowTime,startTime,endTime){
		
	},
	// 详情页秒杀逻辑
	detail : {
		
		// 详情页初始化 -- params 前端传过来的参数
		init : function(params) {
			// 手机验证和登陆 ， 计时交互
			// 规划交互逻辑流程
			// 在cookie中查找手机号
			
			var killPhone = $.cookie('killPhone');
			 var start_time = params['start_time'];
			 var end_time = params['end_time'];
			 var seckill_id = params['seckill_id'];
			// 验证手机号
			if (!seckill.validatePhone(killPhone)) {
				// 绑定phone
				// 控制输出
				var killPhoneModal = $('#killPhoneModal');
				// 显示弹出层
				killPhoneModal.modal({
					show : true,// 显示弹出层
					backdrop : 'static',// 禁止位置关闭
					keyboard : false //关闭键盘事件
				// 关闭键盘事件
				});

				$('#killPhoneBtn').click(
					function() {
						console.log("提交手机号码按钮被点击");
						var inputPhone = $("#killPhoneKey").val();
						
						
						if (seckill.validatePhone(inputPhone)) {
							// 电话写入cookie : expires-有效期7天 
							$.cookie('killPhone', inputPhone,{expirse : 7, path : '/mySeckill' });
							// 刷新页面 
							window.location.reload();
						} else {
							$('#killPhoneMessage')
									.hide().html('<label class="label label-danger">手机号格式错误！</label>').show(3);
						}
					}
				);
			}

			// 已经登录
			// 用户计时交互
			
			$.get(seckill.URL.now(),{},function (result){
				if(result && result['success']){
					var nowTime = result['data'];
					// 时间判断
					seckill.countDown(seckill_id,nowTime,start_time,end_time);
				}else{
					console.log('result- now = '+ result);
				}
			});
			
			

		}
	}
}
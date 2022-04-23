-- 数据库脚本初始化

-- 创建数据库
-- create database seckill CHARSET utf8;
-- 使用数据库
use seckill;
-- 创建秒杀库存表
create table seckill(
	`seckill_id` bigint not NULL AUTO_INCREMENT COMMENT '商品库存id',
	`name` varchar(120) not NULL COMMENT '商品名称',
	`number` int not NULL COMMENT '库存数量',
	`create_time` TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP() comment '创建时间',
	`start_time` TIMESTAMP not NULL comment '秒杀开始时间',
	`end_time` TIMESTAMP not NULL comment '秒杀结束时间', 
	primary key (seckill_id),
	key idx_create_time(create_time),
	key idx_start_time(start_time),
	key idx_end_time(end_time)
) engine=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 comment='库存秒杀表';

-- 初始化数据
insert into seckill(name,number,start_time,end_time)
values ('1000元秒杀Iphone6',100,'2018-01-01 00:00:00','2018-01-02 00:00:00'),
	   ('100元秒杀Ipad',50,'2018-03-01 00:00:00','2018-03-02 00:00:00'),
	   ('500元秒杀小米7',52,'2018-06-01 00:00:00','2018-06-02 00:00:00'),
	   ('800元秒杀Thinkpad T480',2,'2018-05-01 00:00:00','2018-06-02 00:00:00');
	   
	   
-- 秒杀成功明细表
-- 用户登录认证相关信息
create table success_killed(
	`seckill_id` BIGINT not NULL AUTO_INCREMENT comment '秒杀商品id',
	`user_phone` BIGINT not NULL comment '用户手机号',
	`state` tinyint not NULL default -1 comment '秒杀状态标识： -1 无效/0 成功/1已付款/2已发货',
	`create_time` TIMESTAMP not NULL comment '秒杀时间',
	primary key (seckill_id,user_phone)
) ENGINE=InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET=utf8 comment='秒杀成功明细表';


	   
-- 了解数据库的控制台
-- mysql -uroot -proot

----------------------------
-- 常见问题：1.通过windows进入mysql命令界面(或者通过图形界面连接时)，输入密码闪退的问题(提示 2003can't connect localhost )
	   -- 2.解决办法：
         -- 尝试重启Mysql服务；密码输入错误会导致闪退情况，
	     -- 更改mysql的root用户密码：固通过输入命令  mysqld --skip-grant-tables  回车，此时就跳过了mysql的用户验证。注意输入此命令之后命令行就无法操作了，此时可以再打开一个新的命令行。注意：在输入此命令之前先在任务管理器中结束mysqld.exe进程，确保mysql服务器端已结束运行。mysql数据库忘记密码时如何修改，然后直接输入mysql，不需要带任何登录参数直接回车就可以登陆上数据库。更改root密码，输入update user set password=password('123456') where user='root' and host='localhost';
	     -- 通过重新安装mysql程序，首先卸载程序，再删除regedit注册表中mysql文件及文件夹，在删除C:目录下隐藏文件夹Program Date中Mysql文件夹，关闭服务-Mysql服务重启机器，再次安装。




	   
	   
	   
	   
	   
	   
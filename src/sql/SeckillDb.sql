/** 创建数据库 */
IF DB_ID('seckill') IS NULL
CREATE DATABASE seckill 

USE seckill
/** 创建表：seckill、SuccessKilled */
IF OBJECT_ID(N'seckill..seckill') IS NOT NULL DROP TABLE dbo.seckill
CREATE TABLE [dbo].[seckill]
(
	[seckill_id] [int] NOT NULL IDENTITY(10000, 1),
	[name] [varchar] (120) COLLATE Chinese_PRC_CI_AS NOT NULL,
	[number] [int] NOT NULL,
	[create_time] [datetime] NOT NULL,
	[start_time] [datetime] NOT NULL,
	[end_time] [datetime] NOT NULL
	) ON [PRIMARY]
GO

IF OBJECT_ID(N'seckill..success_killed') IS NOT NULL DROP TABLE dbo.success_killed
CREATE TABLE [dbo].[success_killed]
(
	[seckill_id] [int] NOT NULL IDENTITY(10000, 1),
	[user_phone] [bigint] NOT NULL,
	[state] [tinyint] NOT NULL,
	[create_time] [timestamp] NOT NULL
	) ON [PRIMARY]
GO

--------------
-- 创建触发器实例表，用以监控对seckill表的增删改操作
IF OBJECT_ID(N'seckill..seckill_logbak') IS NOT NULL DROP TABLE dbo.seckill_logbak
CREATE TABLE [dbo].[seckill_logbak]
(
	[id] [int] NOT NULL IDENTITY(1, 1),
	[seckill_id] [int] NOT NULL,
	[name] [varchar] (120) COLLATE Chinese_PRC_CI_AS NOT NULL,
	[number] [int] NOT NULL,
	[create_time] [timestamp] NOT NULL,
	[start_time] [datetime] NOT NULL,
	[end_time] [datetime] NOT NULL,
	[mod_time] [datetime] NOT NULL,
	[oper_flag] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL
	) ON [PRIMARY]
GO
---------------------------------------------------------------------------------------------------------
--- 创建触发器

USE [seckill]
GO
/****** Object:  Trigger [dbo].[addSkTrigger2]    Script Date: 2018/5/29 11:44:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].tr_seckill ON [dbo].[seckill]
FOR UPDATE,INSERT,DELETE
AS 
BEGIN
	SET NOCOUNT ON;
	DECLARE @num_insert VARCHAR(20),@num_delete VARCHAR(20)
	SELECT @num_insert=COUNT(1) FROM Inserted 
	SELECT @num_delete=COUNT(1) FROM Deleted 

	PRINT '触发器tr_seckill ：Inserted 的数量 = ' + @num_insert ;
	PRINT '触发器tr_seckill ：Deleted 的数量 = ' + @num_delete ;
	
	IF ( ((SELECT COUNT(*) FROM Inserted) >=1) AND ((SELECT COUNT(*) FROM Deleted)>=1) )  -- 更新操作
	BEGIN
			INSERT INTO dbo.seckill_logbak
					( seckill_id ,
					  name ,
					  number ,
					  start_time ,
					  end_time ,
					  create_time ,
					  mod_time ,
					  oper_flag )
			SELECT a.seckill_id ,
				   a.name ,
				   a.number ,
				   a.create_time ,
				   a.start_time ,
				   a.end_time,
				   GETDATE(),
				   'update'
			FROM Inserted a
	END
	
    ELSE IF NOT EXISTS (SELECT * FROM Inserted)   -- 删除操作
	BEGIN
			INSERT INTO dbo.seckill_logbak
					( seckill_id ,
					  name ,
					  number ,
					  start_time ,
					  end_time ,
					  create_time ,
					  mod_time ,
					  oper_flag
					)
			SELECT a.seckill_id ,
				   a.name ,
				   a.number ,
				   a.create_time ,
				   a.start_time ,
				   a.end_time,
				   GETDATE(),
				   'delete'
			FROM Deleted a
	END
    ELSE IF NOT EXISTS(SELECT * FROM Deleted ) -- 新增操作
	BEGIN
			INSERT INTO dbo.seckill_logbak
					( seckill_id ,
					  name ,
					  number ,
					  start_time ,
					  end_time ,
					  create_time ,
					  mod_time ,
					  oper_flag
					)
			SELECT a.seckill_id ,
				   a.name ,
				   a.number ,
				   a.create_time ,
				   a.start_time ,
				   a.end_time,
				   GETDATE(),
				   'insert'
			FROM Inserted a
	END
END

/****  
  注意在逻辑虚拟表deleted和inserted在执行批量操作时是多个记录同时存在而不是一条条的循环执行！ 
*****/
----------------------------------------------------------------------------------

-- 数据引入
-- 1.seckill
SELECT * FROM dbo.seckill



INSERT INTO dbo.seckill
        ( name ,
          number ,
          create_time ,
          start_time ,
          end_time
        )
VALUES  ( '199元秒杀华为matebook' , -- name - varchar(120)
          100 , -- number - int
          GETDATE() , -- create_time - datetime
          '2018-05-29 12:00:00' , -- start_time - datetime
          '2018-05-29 12:02:00'  -- end_time - datetime
        )


TRUNCATE TABLE dbo.seckill_logbak
		SELECT * FROM dbo.seckill
		SELECT * FROM dbo.seckill_logbak

		UPDATE dbo.seckill SET number=8 WHERE number=9
		DELETE FROM dbo.seckill WHERE seckill_id='10006'

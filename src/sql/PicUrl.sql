-- 网站地址库
CREATE TABLE `urldb` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `url` varchar(300) NOT NULL,
  `url_type` char(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11787 DEFAULT CHARSET=utf8;

-- 图片url库表
CREATE TABLE `url_pic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32455 DEFAULT CHARSET=utf8;

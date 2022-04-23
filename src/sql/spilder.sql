
use spilder;

/*

truncate  table spilder.url_pic;
truncate table spilder.urldb;

select 'urldb',count(1) from spilder.urldb
union select 'url_pic',count(1) from url_pic;

select url,count(1) from spilder.url_pic group by url having count(1)>2 order by url;
select url,count(1) from spilder.urldb group by url having count(1)>2 order by url;

insert into spilder.urldb(url, url_type,analysised) value ('https://www.mmonly.cc/mmtp/','1','0');
 */

-- 网站地址库
drop table urldb;
CREATE TABLE `urldb`
(
    `id`       bigint(100)  NOT NULL AUTO_INCREMENT,
    `url`      varchar(300) NOT NULL,
    `url_type` char(2)      NOT NULL,
    `analysised` tinyint(1)  NOT NULL DEFAULT 0,
    `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11787
  DEFAULT CHARSET = utf8;

-- 图片url库表
drop table url_pic;
CREATE TABLE `url_pic`
(
    `id`  bigint(20)   NOT NULL AUTO_INCREMENT,
    `url` varchar(500) NOT NULL,
    `downloaded` tinyint(1) NOT NULL DEFAULT 0,
    `create_time` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 32455
  DEFAULT CHARSET = utf8;

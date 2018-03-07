--创建student数据库
CREATE DATABASE `student`;
--删除student数据库
drop database student;

--创建test_c数据库
create database test_c;
--创建数据表
create table duty(
  sequence int auto_increment primary key,
  company_name varchar(20),
  student_name varchar(4),
  is_sign_in bit,
  class_date datetime
)auto_increment = 100;
--重命名字段名称、
alter table duty change company_name company varchar(20);
--修改字段的长度
alter table duty modify column student_name varchar(20);
--修改字段是否可为空
alter table duty modify column is_sign_in bit;
--增加字段
alter table duty add column sex varchar(2);
--插入数据
insert into duty(company,student_name,is_sign_in,class_date,sex)
values('NNIT','马玲',1,'2018-03-03',false);
insert into duty(company,student_name,is_sign_in,class_date,sex)
values('NNIT','郑佳',0,'2018-01-03',false);
insert into duty(company,student_name,is_sign_in,class_date,sex)
values('NNIT','杨阳',1,'2018-02-03',true);
insert into duty(company,student_name,is_sign_in,class_date,sex)
values('NNIT','吕金典',0,'2018-01-03',true);
insert into duty(company,student_name,is_sign_in,class_date,sex)
values('NNIT','孙明',1,'2018-02-03',true);
insert into duty(company,student_name,is_sign_in,class_date,sex)
values('NNIT','李青',0,'2018-03-03',true);
insert into duty(company,student_name,is_sign_in,class_date,sex)
values('NNIT','万晨',1,'2018-01-03',true);
insert into duty(company,student_name,is_sign_in,class_date,sex)
values('NNIT','张硕',0,'2018-03-03',true);

--删除数据
delete from duty where is_sign_in = 1;
--更新数据
update duty set 
  company = 'IBI',
  is_sign_in = 1,
  class_date = '2018-03-07',
  sex = true
where student_name = '吕金典';

--查询数据表--asc:升序排列；desc:降序排列
select * from duty;
--按照sequence列降序排列
select * from duty order by sequence desc;
--按照is_sign_in列升序排列
select * from duty order by is_sign_in asc;
--统计符合条件数据的条数
select count(1) as '符合条件数目' from duty where class_date='2018-03-03' 
and sex=true;

select 
(case when is_sign_in then '是' else '否' end) 是否签到, 
count(1) 人数 
from duty
where class_date='2018-03-03'
group by is_sign_in;
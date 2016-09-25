#commi、rollback只能用于DML操作，即insert、update、delet;
#rollback操作撤销上一个commit、rollback之后的事务。

create table test
(
 PROD_ID varchar(10) not null,
 PROD_DESC varchar(25)  null,
 COST decimal(6,2)  null
);

#禁止自动提交
set autocommit=0;

#设置事务特性,必须在所有事务开始前设置
#set transaction read only;  #设置事务只读
set transaction read write;  #设置事务可读、写

#开始一次事务
start transaction;
insert into test
values('4456','mr right',46.97);
commit;     #位置1

insert into test
values('3345','mr wrong',54.90);
rollback;    #回到位置1，(位置2)；上次commit处

insert into test
values('1111','mr wan',89.76);
rollback;    #回到位置2，上次rollback处

#测试保存点savepoint
savepoint point1;
update test
set PROD_ID=1;
rollback to point1;  #回到保存点point1

release savepoint point1; #删除保存点

drop table test;
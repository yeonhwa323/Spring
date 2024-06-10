-- password : java1234
SELECT USER();
SELECT DATABASE();
USE was_db;

   create table dept(
      deptno int not null primary key , -- auto_increment
      dname varchar(14) not null,
      loc   varchar(13)
   );

   INSERT INTO dept VALUES (10,   'ACCOUNTING',   'NEW YORK');
   INSERT INTO dept VALUES (20,   'RESEARCH',   'DALLAS');
   INSERT INTO dept VALUES (30,   'SALES',   'CHICAGO');
   INSERT INTO dept VALUES (40,   'OPERATIONS',   'BOSTON'); 
   
commit;

select * from dept;


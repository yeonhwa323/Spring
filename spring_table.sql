--240522
select * from dept;

       CREATE TABLE tbl_board
    (
        bno number(10)
      , title varchar2(200) not null
      , content varchar2(2000) not null
      , writer varchar2(50) not null
      , regdate date default sysdate
      , updatedate date default sysdate
    );
    
alter table tbl_board add constraint pk_tblboard_bno primary key(bno);
    
CREATE SEQUENCE seq_board;  
     
select * from tbl_board;

--0523
SELECT rowid, e.*
FROM emp e
ORDER BY ename DESC;

--실행할 때 인덱스를 사용해서 실행
SELECT e.*
FROM emp e
ORDER BY ename DESC;

--hint 설명쿼리
select
 /* INDEX_DESC(tbl_board pk_tblboard_bno) */
 *
from tbl_board
where bno > 0
--order by bno desc;




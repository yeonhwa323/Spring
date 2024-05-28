--240528
select * from notices;

commit;
--240527
SELECT d.deptno, dname, empno, ename, job, hiredate, sal, grade
FROM emp e JOIN dept d ON d.deptno = e.deptno
           JOIN salgrade s ON sal BETWEEN losal AND hisal
ORDER BY d.deptno ASC
;
-- SL06_NLMVC
5. 테이블 생성
   --           공지사항(게시판)
   CREATE TABLE NOTICES
   (
      "SEQ" VARCHAR2(10 BYTE),  -- 글번호
      "TITLE" VARCHAR2(200 BYTE), -- 제목
      "WRITER" VARCHAR2(50 BYTE), -- 작성자
      "CONTENT" VARCHAR2(4000 BYTE), -- 내용
      "REGDATE" TIMESTAMP (6),  -- 작성일
      "HIT" NUMBER,  -- 조회수
      "FILESRC" VARCHAR2(500 BYTE) -- 첨부파일
   );
   -- Table NOTICES이(가) 생성되었습니다.
   
   --            회원
   DROP TABLE "MEMBER"
   CREATE TABLE "MEMBER"
   (   
       "ID" VARCHAR2(50 BYTE),  -- UID -> ID 수정
       "PWD" VARCHAR2(50 BYTE), 
       "NAME" VARCHAR2(50 BYTE), 
       "GENDER" VARCHAR2(10 BYTE), 
       "BIRTH" VARCHAR2(10 BYTE), 
       "IS_LUNAR" VARCHAR2(10 BYTE), 
       "CPHONE" VARCHAR2(15 BYTE), 
       "EMAIL" VARCHAR2(200 BYTE), 
       "HABIT" VARCHAR2(200 BYTE), 
       "REGDATE" DATE
   );
   -- Table "MEMBER"이(가) 생성되었습니다.


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

BEGIN
    FOR i IN 1 .. 158
    LOOP 
      INSERT INTO tbl_board (bno, title, content, writer)
      VALUES ( seq_board.nextval ,  'PL SQL-' || i, 'PL SQL-' || i, '홍길동' ) ;
    END LOOP;  
END;
COMMIT;

BEGIN
    FOR i IN 1 .. 158
    LOOP 
      IF MOD(i,5)=0 OR MOD(i, 17)=0 THEN
        UPDATE tbl_board
        SET title = '자바-' || i        
        WHERE bno = i;
      END IF; 
    END LOOP;  
END;
COMMIT;


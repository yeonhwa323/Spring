--240603

SELECT * FROM INQUIRY;

SELECT * FROM BUY_INQUIRY;

SELECT * FROM ETC_INQUIRY;

SELECT * FROM GENERAL_INQUIRY;

SELECT * FROM member;
--

SELECT id, pwd, name, enabled, regdate, authority 
FROM  member  m LEFT JOIN member_authorities auth ON m.id = auth.username
WHERE m.id = 'admin';


INSERT INTO member_authorities VALUES ( 'admin' , 'ROLE_MANAGER' );
INSERT INTO member_authorities VALUES ( 'admin' , 'ROLE_ADMIN' );
INSERT INTO member_authorities VALUES ( 'admin' , 'ROLE_USER' );

INSERT INTO member_authorities VALUES ( 'yeon' , 'ROLE_MANAGER' );
INSERT INTO member_authorities VALUES ( 'yeon' , 'ROLE_USER' );

INSERT INTO member_authorities VALUES ( 'hong' , 'ROLE_USER' );
COMMIT;
-------
  CREATE TABLE member_authorities(
   username     varchar2(50) not null      
  , authority    varchar2(50) not null 
  , constraint fk_member_authorities_username 
               FOREIGN KEY(username) 
               REFERENCES member(id)
  );

select * from member_authorities;
desc member;
member ���̺��� pwd �÷��� varchar(50) -> varchar(100) ����

ALTER TABLE MEMBER MODIFY PWD VARCHAR2(100) ;
-----------
select * 
from member;

delete from member;
commit;

id == username
pwd == password
enabled

ALTER TABLE member
ADD ( enabled char(1) default '1');

ALTER TABLE MEMBER
ADD CONSTRAINT pk_member_id PRIMARY KEY(id);
-------------------
SELECT *
FROM  user_constaints
where table_name = 'MEMBER';
--����
ALTER TABLE MEMBER
DROP CONSTRAINT ck_notices_point;

--240530
select * from MEMBER ; notices where title LIKE '%TEST%';

select point 
from member 
where id = 'yeon';

update member
set point = 1
where id = 'yeon';

DELETE FROM NOTICES WHERE SEQ = 15;

commit;

   -- ����Ʈ Į�� �߰�
   ALTER TABLE member
   ADD (  point number(10) default(0) );
   --  title  ���ϼ� �������� ����.
   ALTER TABLE notices
   ADD CONSTRAINT ck_notices_title UNIQUE(title);
   -- point �÷����� 3 �̻� X ���� ���� ( Ʈ  Ȯ�� )
   ALTER TABLE member
   ADD CONSTRAINT ck_notices_point  CHECK ( point < 3 );
   
   ALTER TABLE member
DROP CONSTRAINT ck_notices_point;

ALTER TABLE notices
DROP CONSTRAINT ck_notices_title;

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

5. ���̺� ����
   --           ��������(�Խ���)
   CREATE TABLE NOTICES
   (
      "SEQ" VARCHAR2(10 BYTE),  -- �۹�ȣ
      "TITLE" VARCHAR2(200 BYTE), -- ����
      "WRITER" VARCHAR2(50 BYTE), -- �ۼ���
      "CONTENT" VARCHAR2(4000 BYTE), -- ����
      "REGDATE" TIMESTAMP (6),  -- �ۼ���
      "HIT" NUMBER,  -- ��ȸ��
      "FILESRC" VARCHAR2(500 BYTE) -- ÷������
   );
   -- Table NOTICES��(��) �����Ǿ����ϴ�.
   
   --            ȸ��
   DROP TABLE "MEMBER"
   CREATE TABLE "MEMBER"
   (   
       "ID" VARCHAR2(50 BYTE),  -- UID -> ID ����
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
   -- Table "MEMBER"��(��) �����Ǿ����ϴ�.


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

--������ �� �ε����� ����ؼ� ����
SELECT e.*
FROM emp e
ORDER BY ename DESC;

--hint ��������
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
      VALUES ( seq_board.nextval ,  'PL SQL-' || i, 'PL SQL-' || i, 'ȫ�浿' ) ;
    END LOOP;  
END;
COMMIT;

BEGIN
    FOR i IN 1 .. 158
    LOOP 
      IF MOD(i,5)=0 OR MOD(i, 17)=0 THEN
        UPDATE tbl_board
        SET title = '�ڹ�-' || i        
        WHERE bno = i;
      END IF; 
    END LOOP;  
END;
COMMIT;


set serverout on;

create or replace procedure procHospitalList(
    pBegin  in number,
    pEnd    in number,
    pSigu   in tblcompany.address%type,
    pXcoor  in number,
    pYcoor  in number, 
    pSearch in tblhospital.hosname%type,
    pAlign  in varchar2,
    pResult out sys_refcursor
)
is
begin

    if pAlign = 'distance' then
        
        open pResult for 
        select hpseq, hosname, tel, address, avgstar from
            (select rownum as rnum, hpseq, hosname, tel, address, avgstar from (select h.hpseq, h.hosname, c.tel, c.address, 
                (select avg(star) from tblHosReview where hpseq = h.hpseq) as avgstar
                    from tblHospital h inner join tblCompany c on h.cseq = c.cseq
                        where h.statseq = 1 and c.address like (pSigu || '%') and h.hosname like ('%' || pSearch ||'%')
                            order by (select sqrt(power(pXcoor-c.xcoor,2) + power(pYcoor-c.ycoor,2)) from dual) asc NULLS last)) 
            where rnum between pBegin and pEnd;
                
    elsif pAlign = 'avgstar' then
        
        open pResult for 
        select hpseq, hosname, tel, address, avgstar from
            (select rownum as rnum, hpseq, hosname, tel, address, avgstar from (select h.hpseq, h.hosname, c.tel, c.address, 
                (select avg(star) from tblHosReview where hpseq = h.hpseq) as avgstar
                    from tblHospital h inner join tblCompany c on h.cseq = c.cseq
                        where h.statseq = 1 and c.address like (pSigu || '%') and h.hosname like ('%' || pSearch || '%')
                            order by avgstar desc NULLS last)) 
            where rnum between pBegin and pEnd;
    else 
    
         open pResult for select null as hpseq, null as hosname, null as tel, null as address, null as avgstar from dual;
         
    end if;
end;
 


declare
    vresult SYS_REFCURSOR;
    hpseq number;
    hosname varchar2(100);
    tel varchar2(50);
    address varchar2(300);
    avgstar number;
begin
    procHospitalList(1, 10, '', 0, 0, '', 'distance', vresult);
    
    loop
        fetch vresult into hpseq, hosname, tel, address, avgstar;
        exit when vresult%notfound;
        
        dbms_output.put_line(hpseq || ',' || hosname);
    
    end loop;
    
end;

-- 테이블 오타수정
alter table tblOpen rename column opneseq to openseq;
alter table tblHosDate rename column opneseq to openseq;

-- 시퀀스 생성
create sequence seqHosReview start with 2;
create sequence seqResHos start with 2;
create sequence seqQues start with 2;
create sequence seqQuesAns start with 2;
create sequence seqHosDate start with 994;


-- 데이터에서 평일, 주말 제거!!!
update tblHosDate set openseq = 6 where openseq = 9;
insert into tblHosDate values (SEQHOSDATE.nextval, 1, 2);
insert into tblHosDate values (SEQHOSDATE.nextval, 1, 3);
insert into tblHosDate values(SEQHOSDATE.nextval, 1, 4);
insert into tblHosDate values(SEQHOSDATE.nextval, 1, 5);
insert into tblHosDate values(SEQHOSDATE.nextval, 1, 6);
insert into tblHosDate values(SEQHOSDATE.nextval, 2, 2);
insert into tblHosDate values(SEQHOSDATE.nextval, 2, 3);
insert into tblHosDate values(SEQHOSDATE.nextval, 2, 4);
insert into tblHosDate values(SEQHOSDATE.nextval, 2, 5);



-- 병원 진료 예약
alter table tblResHos MODIFY visitseq DEFAULT 3; 


-- 병원 문의게시판
alter table tblQues add attachfile varchar2(50) null;
alter table tblQues rename column filed to content;


insert into tblQues values (seqQues.nextVal, 1, '늘푸른병원 휴무 공지', '늘푸른 병원의 휴무일은 일요일입니다.', 'company1234', default, 'n', null);
insert into tblQues values (seqQues.nextVal, 1, '문의글 작성시 주의사항', '주의사항입니다.', 'company1234', default, 'n', 'company.png');
insert into tblQues values (seqQues.nextVal, 1, '진료문의', '안녕하세요. 혹시 새 진료도 가능한가요?', 'hong1234', default, 'n', 'bird.png');
insert into tblQues values (seqQues.nextVal, 1, '진료문의', '안녕하세요. 혹시 새 진료도 가능한가요?', 'hong1234', default, 'n', null);
insert into tblQues values (seqQues.nextVal, 1, '안녕하세요.', '안녕하세요. 혹시 새 진료도 가능한가요?', 'hong1234', default, 'n', null);


create view vwQues as
select q.*, nickname from tblQues q inner join
    (select i.id, nickname from tblId i inner join tblUser u on i.id = u.id 
    union
    select i.id, hosname from tblId i 
        inner join tblCompany c on i.id = c.id 
        inner join tblHospital h on c.cseq = h.cseq) u 
on q.id = u.id;



create or replace procedure procQues (
    pCseq in number,
    pId in varchar2,
    pBegin in number,
    pEnd in number,
    pSearchKey in varchar2,
    pSearchValue in varchar2,
    pResult out sys_refcursor
)
is
begin

    if pSearchKey = 'title' then

        open pResult for
        select hqseq, title, postdate, attachFile, nickname, secret, type, answer from
            (select rownum as rnum, k.* from
                (select * from
                    (select * from
                        (select hqseq, title, postdate, attachFile, nickname, secret,  0 as type, 'n' as answer from vwQues 
                            where cseq = pCseq and id = pId order by postdate desc)
                    union 
                    select * from 
                        (select q.hqseq, q.title, q.postdate, q.attachFile, q.nickname, secret, 1, case when a.qaseq is not null then 'y' else 'n' end as answer
                            from vwQues q left outer join tblQuesAns a on q.hqseq = a.hqseq
                                where q.cseq = pCseq and q.id <> pId order by postdate desc))
                    where title like ('%'|| pSearchValue ||'%')
                    order by type, postdate desc) k)
        where rnum between pBegin and pEnd;
    
    elsif pSearchKey = 'content' then
        
        open pResult for
        select hqseq, title, postdate, attachFile, nickname, secret, type, answer from
            (select rownum as rnum, k.* from
                (select * from
                    (select * from
                        (select hqseq, title, postdate, attachFile, nickname, content, secret, 0 as type, 'n' as answer from vwQues
                            where cseq = pCseq and id = pId order by postdate desc)
                    union 
                    select * from 
                        (select q.hqseq, q.title, q.postdate, q.attachFile, q.nickname, q.content, q.secret, 1, case when a.qaseq is not null then 'y' else 'n' end as answer
                            from vwQues q left outer join tblQuesAns a on q.hqseq = a.hqseq
                                where q.cseq = pCseq and q.id <> pId order by postdate desc))
                    where content like ('%'|| pSearchValue ||'%') 
                    order by type, postdate desc) k)
        where rnum between pBegin and pEnd;
    
    elsif pSearchKey = 'nickname' then
        
        open pResult for
        select hqseq, title, postdate, attachFile, nickname, secret, type, answer from
            (select rownum as rnum, k.* from
                (select * from
                    (select * from
                        (select id, hqseq, title, postdate, attachFile, nickname, secret, 0 as type, 'n' as answer from vwQues
                            where cseq = pCseq and id = pId order by postdate desc)
                    union 
                    select * from 
                        (select q.id , q.hqseq, q.title, q.postdate, q.attachFile, q.nickname, q.secret, 1, case when a.qaseq is not null then 'y' else 'n' end as answer
                            from vwQues q left outer join tblQuesAns a on q.hqseq = a.hqseq
                                where q.cseq = pCseq and q.id <> pid order by postdate desc))
                where nickname like ('%'|| pSearchValue ||'%')
                    order by type, postdate desc) k)
        where rnum between pBegin and pEnd;
        
    else 
         open pResult for select null as hqseq, null as title, null as postdate, null as attachFile, null as nickname, null as secret, null as type, null as answer from dual;
    end if;

end;
/

declare 
    vresult sys_refcursor; 
    hqseq number;
    title varchar2(100);
    postdate date;
    attachFile varchar2(100);
    nickname varchar2(100);
    secret varchar2(10);
    aa number;
    answer varchar2(10);
begin
    procQues(1, 'company1234', 1, 20, 'title', '', vresult);
 
    loop
    fetch vresult into hqseq, title, postdate, attachFile, nickname, secret, aa, answer;
    exit when vresult%notfound;
        
        dbms_output.put_line(hqseq || ',' || title || ',' || postdate || ',' || attachFile || ',' || nickname || ',' ||secret|| ',' || aa || ',' || answer);
    
    end loop;
    
end;

create view vwUserCompany as
select i.id, '0' as type from tblId i inner join tblUser u on i.id = u.id 
union
select i.id, '1' from tblId i 
    inner join tblCompany c on i.id = c.id 
    inner join tblHospital h on c.cseq = h.cseq;


create sequence seqSche start with 6;




create or replace view vwbefres
as
select
    a.name,
    rh.uniqueness,
    rh.rhseq,
    (select hosname from tblHospital h where h.hpseq = rh.hpseq) as hpname,
    rh.resdate,
    ua.useq,
    (select id from tblUser u where u.useq = ua.useq) as id
from tblUserAni ua
    inner join tblAnimal a
        on ua.aseq = a.aseq
            inner join tblResHos rh
                on ua.uaseq = rh.uaseq;
                
create sequence seqUser;

-- 1. 시퀀스

create sequence seqWdiary
create sequence SEQWKAN
create sequence seqCdiary


-- 2.뷰

CREATE OR REPLACE VIEW vwCdiary
as 
select
d.cdseq, d.title, d.content, d.datetune , d.id, d.pseq, d.aseq, d.picture, 
ani.name, ani.
age, info.pic,
pd.pname,
ing.component, d.pdate , d.usage, d.amount, 
pp.purpose, p.explain,sg.surgery, v.vaccination
from tblcdiary d
join tblpresc p on d.pseq = p.pseq
join tblreshos rh on p.rhseq = rh.rhseq
join tblpurpose pp on rh.pseq = pp.pseq
join tblPID pid on p.pseq = pid.pseq
join tblDosage d on d.dseq = pid.dseq
join tblProIng pro on pro.proingseq = pid.proingseq
join tblProduct pd on pd.pseq = pro.pseq
join tblIngred ing on ing.iseq = pro.iseq
left join tblPvaccin pv on p.pseq = pv.pseq
left join tblVaccin v on v.vseq = pv.vseq
left join tblPSurgery s on p.pseq = s.pseq
left join tblSurgery sg on sg.suseq = s.suseq
join tblAnimal ani on ani.aseq = d.aseq
join tblAniInfo info on info.aseq = ani.aseq;

/* 28. 커뮤니티 */
CREATE TABLE tblCommu (
	commuseq NUMBER NOT NULL, /* 커뮤니티번호 */
	title VARCHAR2(300) NOT NULL, /* 제목 */
	id VARCHAR2(20) NOT NULL, /* 아이디 */
	postDay DATE DEFAULT sysdate NOT NULL, /* 작성일 */
	readcount NUMBER DEFAULT 0 NOT NULL, /* 조회수 */
	attachFile VARCHAR2(30), /* 첨부파일명 */
	orgattachFile VARCHAR2(30), 
	typeseq NUMBER, /* 커뮤니티유형번호 */
	Field VARCHAR2(3000) NOT NULL, /* 내용 */
    
    CONSTRAINT PK_TBLCOMMU PRIMARY KEY (commuseq),
    CONSTRAINT tblCommu_id_fk FOREIGN KEY (id) REFERENCES tblId (id),
    CONSTRAINT tblCommu_typeseq_fk FOREIGN KEY (typeseq) REFERENCES tblComType (typeseq)

);

-- orgattachFile 컬럼 추가


alter table tblfaq add faqdate date DEFAULT sysdate not null;

/* 48. FAQ */
CREATE TABLE tblFaq (
	faqseq NUMBER NOT NULL, /* FAQ번호 */
	id VARCHAR2(20) NOT NULL, /* 아이디 */
	mainQ VARCHAR2(3000) NOT NULL, /* 질의 */
	mainA VARCHAR2(3000) NOT NULL, /* 응답 */
	faqdate date default sysdate not null,    	

    CONSTRAINT PK_TBLFAQ PRIMARY KEY (faqseq),
    CONSTRAINT tblFaq_id_fk FOREIGN KEY (id) REFERENCES tblId (id)
    
);

-- faqdate 컬럼 추가


CREATE SEQUENCE "SEQCOMMU"  
   MINVALUE 1 
   MAXVALUE 9999999999999999999999999999 
   INCREMENT BY 1 
   START WITH 1 
   CACHE 20 
   NOORDER  
   NOCYCLE ;


-- SEQCOMMU 시퀀스 생성

CREATE SEQUENCE "SEQANSWER"  
   MINVALUE 1 
   MAXVALUE 9999999999999999999999999999 
   INCREMENT BY 1 
   START WITH 1 
   CACHE 20 
   NOORDER  
   NOCYCLE ;

-- SEQANSWER 시퀀스 생성

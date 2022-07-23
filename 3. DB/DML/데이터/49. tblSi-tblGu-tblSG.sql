drop table tblSG;
drop table tblGu;
drop table tblSi;



/* 49. 시 */
CREATE TABLE tblSi (
   siseq NUMBER NOT NULL, /* 시번호 */
   name VARCHAR2(24) NOT NULL, /* 이름 */
    CONSTRAINT PK_tblSi   PRIMARY KEY (siseq)
);


/* 50. 구 */
CREATE TABLE tblGu (
   guseq NUMBER NOT NULL, /* 구번호 */
   name VARCHAR2(30) NOT NULL, /* 이름 */
    CONSTRAINT PK_tblGu PRIMARY KEY (guseq)
);

select * from tblGu order by name;

/* 52. 지역테이블 */
CREATE TABLE tblSG (
   sgseq NUMBER NOT NULL, /* 지역번호 */
   siseq NUMBER NOT NULL, /* 시번호 */
   guseq NUMBER NOT NULL, /* 구번호 */
    
    CONSTRAINT PK_tblSG PRIMARY KEY (sgseq),
    CONSTRAINT tblSG_siseq_fk FOREIGN KEY (siseq) REFERENCES tblSi (siseq),
    CONSTRAINT FK_tblGu_TO_tblSG_guseq_fk FOREIGN KEY (guseq) REFERENCES tblGu (guseq)
);


insert into tblSi values( 1	,	'서울특별시' );
insert into tblSi values( 2	,	'부산광역시' );
insert into tblSi values( 3	,	'대구광역시' );
insert into tblSi values( 4	,	'인천광역시' );
insert into tblSi values( 5	,	'광주광역시' );
insert into tblSi values( 6	,	'대전광역시' );
insert into tblSi values( 7	,	'울산광역시' );
insert into tblSi values( 8	,	'세종특별자치시'	);
insert into tblSi values( 9	,	'경기도' );
insert into tblSi values( 10	,	'강원도' );
insert into tblSi values( 11	,	'충청북도'	);
insert into tblSi values( 12	,	'충청남도'	);
insert into tblSi values( 13	,	'전라북도'	);
insert into tblSi values( 14	,	'전라남도'	);
insert into tblSi values( 15	,	'경상북도'	);
insert into tblSi values( 16	,	'경상남도'	);
insert into tblSi values( 17	,	'제주특별자치도'	);
				 
				 
insert into tblGu values( 1	, '종로구' );	
insert into tblGu values( 2	, '중구' );	
insert into tblGu values( 3	, '용산구' );	
insert into tblGu values( 4	, '성동구' );	
insert into tblGu values( 5	, '광진구' );	
insert into tblGu values( 6	, '동대문구' );	
insert into tblGu values( 7	, '중랑구' );	
insert into tblGu values( 8	, '성북구' );	
insert into tblGu values( 9	, '강북구' );	
insert into tblGu values( 10	, '도봉구' );	
insert into tblGu values( 11	, '노원구' );	
insert into tblGu values( 12	, '은평구' );	
insert into tblGu values( 13	, '서대문구' );	
insert into tblGu values( 14	, '마포구' );	
insert into tblGu values( 15	, '양천구' );	
insert into tblGu values( 16	, '강서구' );	
insert into tblGu values( 17	, '구로구' );	
insert into tblGu values( 18	, '금천구' );	
insert into tblGu values( 19	, '영등포구' );	
insert into tblGu values( 20	, '동작구' );	
insert into tblGu values( 21	, '관악구' );	
insert into tblGu values( 22	, '서초구' );	
insert into tblGu values( 23	, '강남구' );	
insert into tblGu values( 24	, '송파구' );	
insert into tblGu values( 25	, '강동구' );	
insert into tblGu values( 26	, '서구' );	
insert into tblGu values( 27	, '동구' );	
insert into tblGu values( 28	, '영도구' );	
insert into tblGu values( 29	, '부산진구' );	
insert into tblGu values( 30	, '동래구' );	
insert into tblGu values( 31	, '남구' );	
insert into tblGu values( 32	, '북구' );	
insert into tblGu values( 33	, '해운대구' );	
insert into tblGu values( 34	, '사하구' );	
insert into tblGu values( 35	, '금정구' );	
insert into tblGu values( 36	, '강서구' );	
insert into tblGu values( 37	, '연제구' );	
insert into tblGu values( 38	, '수영구' );	
insert into tblGu values( 39	, '사상구' );	
insert into tblGu values( 40	, '기장군' );


insert into tblSG values(1,1,1);	
insert into tblSG values(2,1,2);	
insert into tblSG values(3,1,3);	
insert into tblSG values(4,1,4);	
insert into tblSG values(5,1,5);	
insert into tblSG values(6,1,6);	
insert into tblSG values(7,1,7);	
insert into tblSG values(8,1,8);	
insert into tblSG values(9,1,9);	
insert into tblSG values(10,1,10);	
insert into tblSG values(11,1,11);	
insert into tblSG values(12,1,12);	
insert into tblSG values(13,1,13);	
insert into tblSG values(14,1,14);	
insert into tblSG values(15,1,15);	
insert into tblSG values(16,1,16);	
insert into tblSG values(17,1,17);	
insert into tblSG values(18,1,18);	
insert into tblSG values(19,1,19);	
insert into tblSG values(20,1,20);	
insert into tblSG values(21,1,21);	
insert into tblSG values(22,1,22);	
insert into tblSG values(23,1,23);	
insert into tblSG values(24,1,24);	
insert into tblSG values(25,2,25);	
insert into tblSG values(26,2,2);	
insert into tblSG values(27,2,26);	
insert into tblSG values(28,2,27);	
insert into tblSG values(29,2,28);	
insert into tblSG values(30,2,29);	
insert into tblSG values(31,2,30);	
insert into tblSG values(32,2,31);	
insert into tblSG values(33,2,32);	
insert into tblSG values(34,2,33);	
insert into tblSG values(35,2,34);	
insert into tblSG values(36,2,35);	
insert into tblSG values(37,2,36);	
insert into tblSG values(38,2,37);	
insert into tblSG values(39,2,38);	
insert into tblSG values(40,2,39);	
insert into tblSG values(41,2,40);	

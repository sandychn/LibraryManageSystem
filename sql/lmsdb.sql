CREATE DATABASE lmsdb;

USE lmsdb;

CREATE TABLE lib_user
(
	uName     VARCHAR(20)   NOT NULL PRIMARY KEY, 
	uPassword VARCHAR(16)   NOT NULL,
	uIdentity INT           NOT NULL CHECK(uIdentity IN (1, 2, 3, 4))
) CHARSET=utf8;

CREATE TABLE book_class
(
    bClassId   INT          PRIMARY KEY,
    bClassName NVARCHAR(20) NOT NULL
) CHARSET=utf8;

CREATE TABLE book
(
    bISBN    CHAR(13)       PRIMARY KEY CHECK(bISBN LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	bName    NVARCHAR(40)   NOT NULL,
	bAuthor  NVARCHAR(20)   NOT NULL,
	bPress   NVARCHAR(40)   NOT NULL,
	bClassId INT            NOT NULL,
	bCount   INT            NOT NULL CHECK(bCount >= 0),
	bSum     INT            NOT NULL CHECK(bSum >= 0),
	FOREIGN KEY (bClassId) REFERENCES book_class(bClassId) 
) CHARSET=utf8;

CREATE TABLE book_lending
(
    bISBN   CHAR(13),
	uName   NVARCHAR(20),
	blTime  DATETIME NOT NULL,
	blDue   INT      NOT NULL,
	brTime  DATETIME
) CHARSET=utf8;

INSERT INTO lib_user VALUES ('USEr1', '123456', 1);
INSERT INTO lib_user VALUES ('USEr2', '123456', 2);
INSERT INTO lib_user VALUES ('USEr3', '123456', 3);
INSERT INTO lib_user VALUES ('USEr4', '123456', 4);

INSERT INTO book_class VALUES (1, '农业科学');
INSERT INTO book_class VALUES (2, '哲学');
INSERT INTO book_class VALUES (3, '医学');
INSERT INTO book_class VALUES (4, '政治法律');
INSERT INTO book_class VALUES (5, '军事');
INSERT INTO book_class VALUES (6, '经济');
INSERT INTO book_class VALUES (7, '文化教育');
INSERT INTO book_class VALUES (8, '语言文字');
INSERT INTO book_class VALUES (9, '文学');
INSERT INTO book_class VALUES (10, '艺术');
INSERT INTO book_class VALUES (11, '航天航空');
INSERT INTO book_class VALUES (12, '工业技术');
INSERT INTO book_class VALUES (13, '生物科学');
INSERT INTO book_class VALUES (14, '环境科学');
INSERT INTO book_class VALUES (15, '天文学');

INSERT INTO book VALUES ('9787545528060', '未来医学科', '小多（北京）文化传媒有限公司', '天地出版社', 3, 7, 7);
INSERT INTO book VALUES ('9787500854296', '男人的必修课，女人的箴言书', '张笑恒', '中国工人出版社', 2, 10, 10);
INSERT INTO book VALUES ('9789571189680', '新時代的醫學人文', '戴正德/石曜堂', '五南圖書出版股份有限公司', 3, 10, 10);
INSERT INTO book VALUES ('9787811168730', '医学文献检索', '谢志耘', '北京大学医学出版社', 3, 5, 5);
INSERT INTO book VALUES ('9789863185956', '說英語Fun遊台灣', 'Andrew Crosthwaite', '寂天文化事業股份有限公司', 8, 2, 2);
INSERT INTO book VALUES ('9787502474560', '单质硼及其应用', '倪坤', '冶金工业出版社', 11, 3, 3);
INSERT INTO book VALUES ('9787504668974', '世界前沿技术发展报告:2014', '中国科学技术协会国际联络部', '中国科技术出版社', 8, 4, 4);

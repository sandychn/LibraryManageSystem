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
    FOREIGN KEY (bClassId)  REFERENCES book_class(bClassId) 
) CHARSET=utf8;

CREATE TABLE book_lending
(
    bISBN   CHAR(13),
    uName   NVARCHAR(20),
    blTime  DATETIME NOT NULL,
    blDue   INT      NOT NULL,
    brTime  DATETIME
) CHARSET=utf8;

INSERT INTO lib_user VALUES ('user1', '123456', 1);
INSERT INTO lib_user VALUES ('user2', '123456', 2);
INSERT INTO lib_user VALUES ('user3', '123456', 3);
INSERT INTO lib_user VALUES ('user4', '123456', 4);

INSERT INTO book_class VALUES (1, 'ũҵ��ѧ');
INSERT INTO book_class VALUES (2, '��ѧ');
INSERT INTO book_class VALUES (3, 'ҽѧ');
INSERT INTO book_class VALUES (4, '���η���');
INSERT INTO book_class VALUES (5, '����');
INSERT INTO book_class VALUES (6, '����');
INSERT INTO book_class VALUES (7, '�Ļ�����');
INSERT INTO book_class VALUES (8, '��������');
INSERT INTO book_class VALUES (9, '��ѧ');
INSERT INTO book_class VALUES (10, '����');
INSERT INTO book_class VALUES (11, '���캽��');
INSERT INTO book_class VALUES (12, '��ҵ����');
INSERT INTO book_class VALUES (13, '�����ѧ');
INSERT INTO book_class VALUES (14, '������ѧ');
INSERT INTO book_class VALUES (15, '����ѧ');

INSERT INTO book VALUES ('9787545528060', 'δ��ҽѧ��', 'С�ࣨ�������Ļ���ý���޹�˾', '��س�����', 3, 7, 7);
INSERT INTO book VALUES ('9787500854296', '���˵ı��޿Σ�Ů�˵�������', '��Ц��', '�й����˳�����', 2, 10, 10);
INSERT INTO book VALUES ('9789571189680', '�r�����t�W����', '������/ʯ����', '���ψD������ɷ����޹�˾', 3, 10, 10);
INSERT INTO book VALUES ('9787811168730', 'ҽѧ���׼���', 'л־��', '������ѧҽѧ������', 3, 5, 5);
INSERT INTO book VALUES ('9789863185956', '�fӢ�ZFun�[̨��', 'Andrew Crosthwaite', '�����Ļ��I�ɷ����޹�˾', 8, 2, 2);
INSERT INTO book VALUES ('9787502474560', '��������Ӧ��', '����', 'ұ��ҵ������', 11, 3, 3);
INSERT INTO book VALUES ('9787504668974', '����ǰ�ؼ�����չ����:2014', '�й���ѧ����Э��������粿', '�й���ѧ����������', 8, 4, 4);

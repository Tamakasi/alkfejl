INSERT INTO USER (ID, VERSION, NAME, PASSWORD, EMAIL, ROLE) VALUES (7, 0, 'admin', 'admin', 'adminmail', 'ADMIN');

INSERT INTO USER (ID, VERSION, NAME, PASSWORD, EMAIL, ROLE) VALUES (8, 0, 'test1', 'test1', 'test1','USER');

INSERT INTO USER (ID, VERSION, NAME, PASSWORD, EMAIL, ROLE) VALUES (9, 0, 'test2', 'test2', 'test2', 'USER');

INSERT INTO FOLDER (ID, VERSION, COLOR, DELETED, DESCRIPTION, NAME) VALUES (300, 0,'green',FALSE,'test2_folder1', 'Testfolder1');

INSERT INTO FOLDER (ID, VERSION, COLOR, DELETED, DESCRIPTION, NAME) VALUES (301, 0,'red',FALSE,'test2_folder2', 'Testfolder2');

INSERT INTO USER_FOLDERS (USER_ID, FOLDERS_ID) VALUES (9, 300);
INSERT INTO USER_FOLDERS (USER_ID, FOLDERS_ID) VALUES (9, 301);

INSERT INTO TASK (ID, VERSION, DEADLINE, DELETED, DESCRIPTION, PRIORITY, STATE) VALUES (300, 0, TO_TIMESTAMP('2011-08-11 13:50', 'YYYY-MM-DD HH24:MI'), FALSE, 'noice', 5, 'created');
INSERT INTO TASK (ID, VERSION, DEADLINE, DELETED, DESCRIPTION, PRIORITY, STATE) VALUES (301, 0, TO_TIMESTAMP('2017-08-12 08:50', 'YYYY-MM-DD HH24:MI'), FALSE, 'not so bad', 9, 'completed');
INSERT INTO TASK (ID, VERSION, DEADLINE, DELETED, DESCRIPTION, PRIORITY, STATE) VALUES (302, 0, TO_TIMESTAMP('2017-01-03 01:30', 'YYYY-MM-DD HH24:MI'), FALSE, 'fontos af', 1, 'dropped');

INSERT INTO FOLDER_TASKS (FOLDER_ID, TASKS_ID) VALUES (300, 300);
INSERT INTO FOLDER_TASKS (FOLDER_ID, TASKS_ID) VALUES (300, 301);
INSERT INTO FOLDER_TASKS (FOLDER_ID, TASKS_ID) VALUES (301, 302);




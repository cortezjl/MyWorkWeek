delete from user_role;
-- delete from Role;
delete from user;
-- insert into Role values (1, 'Administrator'),(2, 'Manager'),(3, 'Front Of House'), (4, 'Back of House'),(5,'Busser');
insert into user values (1, 'System', 'Administrator', 'Admin', 'password', '1988/05/08');
insert into user_role values (1, 'System Administrator', 1);
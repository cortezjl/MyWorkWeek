delete from Role;
delete from user;
insert into user values (1, 'System', 'Administrator', 'admin', 'password', '2000/01/01', '2019/01/01', '9999/12/31');
insert into role values (1, 'Administrator', 1, 'admin');
insert into user values (2, 'Tammy', 'Johnson', 'TJohnson', 'password', '1992/10/02', '2017/10/18', '9999/12/31');
insert into role values (2, 'Manager', 2, 'TJohnson');
insert into role values (3, 'Administrator', 2, 'TJohnson');
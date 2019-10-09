delete from Role;
delete from user;
insert into user values (1, 'System', 'Administrator', 'Admin', 'password', '1988/05/08');
insert into role values (1, 'Administrator', 1, 'Admin');
insert into user values (2, 'Tammy', 'Johnson', 'TJohnson', 'password', '1992/10/02');
insert into role values (2, 'Manager', 2, 'TJohnson');
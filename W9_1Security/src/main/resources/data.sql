
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('manh', '47bce5c74f589f4867dbd57e9ca9f808', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('thai', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 

insert into sec_role (roleName)
values ('ROLE_ADMIN');
 
insert into sec_role (roleName)
values ('ROLE_CREAM');

insert into sec_role (roleName)
values ('ROLE_PICKLE');

insert into sec_role (roleName)
values ('ROLE_USER');
 
insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (1, 2);
 insert into user_role (userId, roleId)
values (1, 3);

insert into user_role (userId, roleId)
values (1, 4);



insert into user_role (userId, roleId)
values (2, 2);

insert into user_role (userId, roleId)
values (3, 4);

insert into user_role (userId, roleId)
values (4, 4);




INSERT INTO accountantTable (name, salary, yearsExperience) VALUES
('William', 65000.20, 3),
('Janet', 75000.50, 5),
('Michael', 85000.75, 8),
('Jessica', 55000.25, 2),
('Daniel', 70000.50, 4);





insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('manh', '47bce5c74f589f4867dbd57e9ca9f808', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('thai', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Tod', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into sec_role (roleName)
values ('ROLE_BOSS');

insert into sec_role (roleName)
values ('ROLE_GUEST');
 


insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (4, 2);


insert into user_role (userId, roleId)
values (2, 1);

insert into user_role (userId, roleId)
values (3, 2);



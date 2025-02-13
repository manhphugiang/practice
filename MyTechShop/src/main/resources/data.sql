
INSERT INTO item (description, name, price, quantity) VALUES
('super powerfull potato laptop', 'Laptop', 2999.99, 23),
('super powerfull apple hardware', 'Apple', 29929.99, 44),
('super powerfull ipad m99', 'Ipad', 99.99, 12),
('super powerfull iphone 99', 'Iphone', 69.99, 124),
('super powerfull pixel phone', 'Pixel', 269.99, 31);

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



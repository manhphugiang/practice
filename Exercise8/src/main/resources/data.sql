INSERT INTO CONTACTSDATA (name, address, phoneNumber, email, role) VALUES 
('Manh Phu Giang', '213 elgin', '91212121212', 'giangma@sheridancollege.ca', 'Guest'),
('John', 'us', '123412341234', 'johgn@ius.com', 'Member'),
('Smith', 'canada', '23', 'thai@ius.com', 'Member'),
('Wang', 'Europe', '5645', 'dd@ius.com', 'Admin'),
('Thai', 'us', '23 Bloor', 'rg34g@ius.com', 'Guest');


insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('manh', '47bce5c74f589f4867dbd57e9ca9f808', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('thai', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 



insert into sec_role (roleName)
values ('ROLE_ADMIN');
 
insert into sec_role (roleName)
values ('ROLE_MEMBER');

insert into sec_role (roleName)
values ('ROLE_GUEST');


insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (2, 2);
 insert into user_role (userId, roleId)
values (3, 3);








insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('manh', '47bce5c74f589f4867dbd57e9ca9f808', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('thai', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('nghi', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('tai', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('huy', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 

insert into sec_role (roleName)
values ('ROLE_VENDER');
 


insert into sec_role (roleName)
values ('ROLE_GUEST');


insert into user_role (userId, roleId)
values (1, 1);
 
insert into user_role (userId, roleId)
values (2, 1);

 insert into user_role (userId, roleId)
values (3, 2);


 insert into user_role (userId, roleId)
values (4, 2);

 insert into user_role (userId, roleId)
values (5, 2);

 insert into user_role (userId, roleId)
values (6, 2);



INSERT INTO ticket (name, price, creditCardNumber, ticketType, email, cvv)
VALUES
    ('thai', 50.99, 1234567890123456, 'General Admission','thai@gmail.com', 123),
    ('thai', 100.99, 9876543210987654, 'VIP','thai@gmail.com', 456),
    ('manh', 30.99, 1111222233334444, 'Student Discount','manh@gmail.com', 789),
    ('manh', 75.99, 2222333344445555, 'General Admission','manh@gmail.com', 234),
    ('nghi', 120.99, 3333444455556666, 'VIP','nghi@gmail.com', 567),
    ('tai', 25.99, 4444555566667777, 'Student Discount','tai@gmail.com', 890),
    ('nghi', 60.99, 5555666677778888, 'General Admission','nghi@gmail.com', 345),
    ('tai', 95.99, 6666777788889999, 'VIP','tai@gmail.com', 678),
    ('huy', 40.99, 7777888899990000, 'Student Discount','huy@gmail.com', 901),
    ('huy', 85.99, 8888999900001111, 'General Admission','huy@gmail.com', 234);

INSERT INTO easy_drinks (drink_name, main1, amount1, main2, amount2, directions) VALUES 
('Blackthorn', 'tonic water', 1.5, 'pineapple juice', 1, 'stir with ice, strain into cocktail glass with lemon twist'),
('Blue Moon', 'soda', 1.5, 'blueberry juice', .75, 'stir with ice, strain into cocktail glass with lemon twist'),
('Oh My Gosh', 'peach nectar', 1, 'pineapple juice', 1, 'stir with ice, strain into shot glass'),
('Lime Fizz', 'Sprite', 1.5, 'lime juice', .75, 'stir with ice, strain into cocktail glass'),
('Kiss on the Lips', 'cherry juice', 2, 'apricot nectar', 7, 'serve over ice with straw'),
('Hot Gold', 'peach nectar', 3,'orange juice', 6, 'pour hot orange juice in mug and add peach nectar'),
('Lone Tree', 'soda', 1.5, 'cherry juice', .75, 'stir with ice, strain into cocktail glass'),('Greyhound', 'soda', 1.5, 'grapefruit juice', 5, 'serve over ice, stir well'),
('Indian Summer', 'apple juice', 2, 'hot tea', 6, 'add juice to mug and top off with hot tea'),
('Bull Frog', 'iced tea', 1.5, 'lemonade', 5, 'serve over ice with lime slice'),
('Soda and It', 'wsoda', 2, 'grape juice', 1, 'shake in cocktail glass, no ice');







insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('bartender', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('customer',  '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 

insert into sec_role (roleName)
values ('ROLE_bartender');
 
insert into sec_role (roleName)
values ('ROLE_CUSTOMER');

insert into sec_role (roleName)
values ('ROLE_PICKLE');

insert into sec_role (roleName)
values ('ROLE_USER');
 
insert into user_role (userId, roleId)
values (1, 1);

insert into user_role (userId, roleId)
values (3, 2);

insert into user_role (userId, roleId)
values (2, 1);
insert into user_role (userId, roleId)
values (2, 2);
insert into user_role (userId, roleId)
values (2, 3);
insert into user_role (userId, roleId)
values (2, 4);

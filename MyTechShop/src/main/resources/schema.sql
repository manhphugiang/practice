create table item
(
  id           BIGINT NOT NULL Primary Key AUTO_INCREMENT,
name varchar(255) ,
price double,
description varchar(255),
quantity int
);



create table SEC_USER
(
  userId           BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userName         VARCHAR(36) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  ENABLED           BIT NOT NULL 
) ;


create table SEC_ROLE
(
  roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
) ;


create table USER_ROLE
(
  ID      BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

alter table USER_ROLE
  add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (userId)
  references SEC_USER (userId);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (roleId)
  references SEC_ROLE (roleId);

  
  
  
  
CREATE TABLE Item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE Cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE CartItem (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_id BIGINT,
    cart_id BIGINT,
    quantity INT NOT NULL,
    CONSTRAINT FK_Item_CartItem FOREIGN KEY (item_id) REFERENCES Item(id),
    CONSTRAINT FK_Cart_CartItem FOREIGN KEY (cart_id) REFERENCES Cart(id)
);

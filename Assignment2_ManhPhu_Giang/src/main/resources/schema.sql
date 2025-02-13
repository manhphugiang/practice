CREATE TABLE store (
    id INT NOT NULL  AUTO_INCREMENT, 
    storeName VARCHAR(999) PRIMARY KEY, 
    location VARCHAR(1209), 
    numberEmployee INT,
    storeManager VARCHAR(999))
    ;


CREATE TABLE product 
(   id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    productName VARCHAR(1090), 
    storeName VARCHAR(999),
    productNumber DOUBLE,
    price DOUBLE);

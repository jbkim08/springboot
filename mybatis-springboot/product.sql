DROP TABLE IF EXISTS Products;

CREATE TABLE Products
(
    prod_id     INT     		PRIMARY KEY AUTO_INCREMENT,
    prod_name   VARCHAR(255)    NOT NULL,
    prod_price  INT             NOT NULL
);

INSERT INTO Products (prod_name, prod_price) values ('베베숲 물티슈', 2700);
INSERT INTO Products (prod_name, prod_price) values ('여름 토퍼', 35180);
INSERT INTO Products (prod_name, prod_price) values ('페이크 삭스', 860);
INSERT INTO Products (prod_name, prod_price) values ('우산', 2900);
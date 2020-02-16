DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE PRODUCT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  category VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  buy_price DOUBLE DEFAULT NULL,
  sell_price DOUBLE DEFAULT NULL,
  quantity INT DEFAULT NULL
);
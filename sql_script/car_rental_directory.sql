CREATE DATABASE  IF NOT EXISTS `car_rental_directory`;
USE `car_rental_directory`;

CREATE TABLE `car` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `daily_price` int NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `reservation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `car_id` int NOT NULL,
  `reservation_startdate` date NOT NULL,
  `reservation_enddate` date NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `total_amount` int NOT NULL,
  FOREIGN KEY (`car_id`) REFERENCES car(`id`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `car` VALUES 
	(1,'Mercedes C class','https://www.topgear.com/sites/default/files/2023/10/Medium-44235-CLA250e.jpg', 100, 'active'),
	(2,'Mercedes A class','https://www.topgear.com/sites/default/files/images/news-article/2018/02/172fac6209645a4ca455b5fd020e7384/17c833_009.jpg', 90, 'active'),
    (3,'Mercedes E class','https://www.topgear.com/sites/default/files/cars-car/image/2023/07/E-Class.jpeg', 110, 'active'),
    (4,'mercedes S class','https://www.topgear.com/sites/default/files/cars-car/carousel/2021/05/210302sclassjl_0084.jpg', 120, 'active'),
    (5,'mercedes B class','https://www.topgear.com/sites/default/files/2023/03/15%20Mercedes%20B-Class.jpg', 80, 'active');
    
INSERT INTO `reservation` VALUES (1, 1, '2024-01-28', '2024-01-29', 'John', 'john@email.com', 'address', '0123456789', 200);
INSERT INTO `reservation` VALUES (2, 2, '2024-01-29', '2024-01-30', 'Adam', 'adam@email.com', 'address', '0123456789', 180);
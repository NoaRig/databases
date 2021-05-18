/*TOPPINGS*/
INSERT INTO TOPPINGS VALUES(1,'Pepperoni',1.25,0.2,100,2,2.75,3.5,4.5);
INsERT INTO TOPPINGS VALUES(2,'Sausage',1.25,0.15,100,2.5,3,3.5,4.25);
INSERT INTO TOPPINGS VALUES(3,'Ham',1.5,0.15,78,2,2.5,3.25,4);
INSERT INTO TOPPINGS VALUES(4,'Chicken',1.75,0.25,56,1.5,2,2.25,3);
INSERT INTO TOPPINGS VALUES(5,'Green Pepper',0.5,0.02,79,1,1.5,2,2.5);
INSERT INTO TOPPINGS VALUES(6,'Onion',0.5,0.02,85,1,1.5,2,2.75);
INSERT INTO TOPPINGS VALUES(7,'Roma tomato',0.75,0.03,86,2,3,3.5,4.5);
INSERT INTO TOPPINGS VALUES(8,'Mushrooms',0.75,0.1,52,1.5,2,2.5,3);
INSERT INTO TOPPINGS VALUES(9,'Black Olives',0.6,0.1,39,0.75,1,1.5,2);
INSERT INTO TOPPINGS VALUES(10,'Pineapple',1,0.25,15,1,1.25,1.75,2);
INSERT INTO TOPPINGS VALUES(11,'Jalapenos',0.5,0.05,64,0.5,0.75,1.25,1.75);
INSERT INTO TOPPINGS VALUES(12,'Banana Peppers',0.5,0.05,36,0.6,1,1.3,1.75);
INSERT INTO TOPPINGS VALUES(13,'Regular Cheese',1.5,0.12,250,2,3.5,5,7);
INSERT INTO TOPPINGS VALUES(14,'Four Cheese Blend',2,0.15,150,2,3.5,5,7);
INSERT INTO TOPPINGS VALUES(15,'Feta Cheese',2,0.18,75,1.75,3,4,5.5);
INSERT INTO TOPPINGS VALUES(16,'Goat Cheese',2,0.2,54,1.6,2.75,4,5.5);
INSERT INTO TOPPINGS VALUES(17,'Bacon',1.5,0.25,89,1,1.5,2,3);
/*BASE PRICE*/
INSERT INTO BASE_PRICE VALUES(1,.5,3,'Thin','small');
INSERT INTO BASE_PRICE VALUES(2,.75,3,'Original','small');
INSERT INTO BASE_PRICE VALUES(3,1,3.5,'Pan','small');
INSERT INTO BASE_PRICE VALUES(4,2,4,'Gluten-Free','small');
INSERT INTO BASE_PRICE VALUES(5,1,5,'Thin','medium');
INSERT INTO BASE_PRICE VALUES(6,1.5,5,'Original','medium');
INSERT INTO BASE_PRICE VALUES(7,2.25,6,'Pan','medium');
INSERT INTO BASE_PRICE VALUES(8,3,6.25,'Gluten-Free','medium');
INSERT INTO BASE_PRICE VALUES(9,1.25,8,'Thin','Large');
INSERT INTO BASE_PRICE VALUES(10,2,8,'Original','Large');
INSERT INTO BASE_PRICE VALUES(11,3,9,'Pan','Large');
INSERT INTO BASE_PRICE VALUES(12,4,9.5,'Gluten-Free','Large');
INSERT INTO BASE_PRICE VALUES(13,2,10,'Thin','X-Large');
INSERT INTO BASE_PRICE VALUES(14,3,10,'Original','X-Large');
INSERT INTO BASE_PRICE VALUES(15,4.5,11.5,'Pan','X-Large');
INSERT INTO BASE_PRICE VALUES(16,6,12.5,'Gluten-Free','X-Large');
/*DISCOUNT*/
INSERT INTO DISCOUNT VALUES(1,'employee');
INSERT INTO DISCOUNT VALUES(2,'Lunch Special Medium');
INSERT INTO DISCOUNT VALUES(3,'Lunch Special Large');
INSERT INTO DISCOUNT VALUES(4,'Speciality Pizza');
INSERT INTO DISCOUNT VALUES(5,'Gameday Special');
/*DOLLAR DISCOUNT*/
INSERT INTO DOLLAR_DISCOUNT VALUES(1,2);
INSERT INTO DOLLAR_DISCOUNT VALUES(2,3);
INSERT INTO DOLLAR_DISCOUNT VALUES(1.5,4);
/*PERCENT DISCOUNT*/
INSERT INTO PERCENT_DISCOUNT VALUES(15,1);
INSERT INTO PERCENT_DISCOUNT VALUES(20,5);
/*ORDER ONE*/
INSERT INTO CUST_ORDER VALUES(1,'Dine In',3.68,'2020-03-05 12:03:00',13.50);
INSERT INTO PIZZA VALUES(1,13.50,3.68,'Completed',9,1);
INSERT INTO CHOICE_OF VALUES(1,1,13);
INSERT INTO CHOICE_OF VALUES(0,1,1);
INSERT INTO CHOICE_OF VALUES(0,1,2);
INSERT INTO PIZZA_DISCOUNT VALUES(1,3);
INSERT INTO DINE_IN VALUES(1,14);
INSERT INTO SEAT_NUMBER VALUES(1,1);
INSERT INTO SEAT_NUMBER VALUES(1,2);
INSERT INTO SEAT_NUMBER VALUES(1,3);
/*ORDER TWO*/
INSERT INTO CUST_ORDER VALUES(2,'Dine In',3.23,'2020-03-03 12:05:00',10.60);
INSERT INTO PIZZA VALUES(2,10.60,3.23,'Completed',7,2);
INSERT INTO CHOICE_OF VALUES(0,2,15);
INSERT INTO CHOICE_OF VALUES(0,2,9);
INSERT INTO CHOICE_OF VALUES(0,2,7);
INSERT INTO CHOICE_OF VALUES(0,2,8);
INSERT INTO CHOICE_OF VALUES(0,2,12);
INSERT INTO PIZZA_DISCOUNT VALUES(2,2);
INSERT INTO PIZZA_DISCOUNT VALUES(2,4);
INSERT INTO DINE_IN VALUES(2,4);
INSERT INTO SEAT_NUMBER VALUES(2,1);
/*ORDER THREE*/
INSERT INTO CUST_ORDER VALUES(3,'Dine In',1.4,'2020-03-03 12:05:00',6.75);
INSERT INTO PIZZA VALUES(3,6.75,1.40,'Completed',2,3);
INSERT INTO CHOICE_OF VALUES(0,3,13);
INSERT INTO CHOICE_OF VALUES(0,3,4);
INSERT INTO CHOICE_OF VALUES(0,3,12);
INSERT INTO DINE_IN VALUES(3,4);
INSERT INTO SEAT_NUMBER VALUES(3,2);
/*ORDER FOUR CUSTOMER 0NE*/
INSERT INTO CUSTOMER VALUES(1,'Andrew','Wilkes-Krier','864-254-5861');
INSERT INTO CUST_ORDER VALUES(4,'Pickup',19.80,'2020-03-03 21:30:00',64.50);
INSERT INTO PIZZA VALUES(4,10.75,3.30,'Completed',10,4);
INSERT INTO CHOICE_OF VALUES(0,4,13);
INSERT INTO CHOICE_OF VALUES(0,4,1);
INSERT INTO PIZZA VALUES(5,10.75,3.30,'Completed',10,4);
INSERT INTO CHOICE_OF VALUES(0,5,13);
INSERT INTO CHOICE_OF VALUES(0,5,1);
INSERT INTO PIZZA VALUES(6,10.75,3.30,'Completed',10,4);
INSERT INTO CHOICE_OF VALUES(0,6,13);
INSERT INTO CHOICE_OF VALUES(0,6,1);
INSERT INTO PIZZA VALUES(7,10.75,3.30,'Completed',10,4);
INSERT INTO CHOICE_OF VALUES(0,7,13);
INSERT INTO CHOICE_OF VALUES(0,7,1);
INSERT INTO PIZZA VALUES(8,10.75,3.30,'Completed',10,4);
INSERT INTO CHOICE_OF VALUES(0,8,13);
INSERT INTO CHOICE_OF VALUES(0,8,1);
INSERT INTO PIZZA VALUES(9,10.75,3.30,'Completed',10,4);
INSERT INTO CHOICE_OF VALUES(0,9,13);
INSERT INTO CHOICE_OF VALUES(0,9,1);
INSERT INTO PICKUP_CUSTOMER VALUES(1);
INSERT INTO PICKUP VALUES(4,1);
/*ORDER FIVE*/
INSERT INTO CUST_ORDER VALUES(5,'Delivery',16.86,'2020-03-05 19:11:00',45.50);
INSERT INTO PIZZA VALUES(10,14.50,5.59,'Completed',14,5);
INSERT INTO CHOICE_OF VALUES(0,10,1);
INSERT INTO CHOICE_OF VALUES(0,10,2);
INSERT INTO CHOICE_OF VALUES(0,10,14);
INSERT INTO PIZZA VALUES(11,17.00,5.59,'Completed',14,5);
INSERT INTO CHOICE_OF VALUES(1,11,3);
INSERT INTO CHOICE_OF VALUES(1,11,10);
INSERT INTO CHOICE_OF VALUES(0,11,14);
INSERT INTO PIZZA VALUES(12,14.00,5.68,'Completed',14,5);
INSERT INTO CHOICE_OF VALUES(0,12,11);
INSERT INTO CHOICE_OF VALUES(0,12,17);
INSERT INTO CHOICE_OF VALUES(0,12,14);
INSERT INTO ORDER_DISCOUNT VALUES(5,5);
INSERT INTO PIZZA_DISCOUNT VALUES(11,4);
INSERT INTO DELIVERY_CUSTOMER VALUES(1,'115 Party Blvd','Anderson','SC','29621');
INSERT INTO DELIVERY VALUES(5,1);
/*ORDER SIX CUSTOMER TWO*/
INSERT INTO CUST_ORDER VALUES(6,'Pickup',7.85,'2020-03-02 17:30:00',16.85);
INSERT INTO PIZZA VALUES (13,16.85,7.85,'Completed',16,6);
INSERT INTO CHOICE_OF VALUES(0,13,5);
INSERT INTO CHOICE_OF VALUES(0,13,6);
INSERT INTO CHOICE_OF VALUES(0,13,7);
INSERT INTO CHOICE_OF VALUES(0,13,8);
INSERT INTO CHOICE_OF VALUES(0,13,9);
INSERT INTO CHOICE_OF VALUES(0,13,16);
INSERT INTO PIZZA_DISCOUNT VALUES(13,4);
INSERT INTO CUSTOMER VALUES(2,'Matt','Engers','864-474-9953');
INSERT INTO PICKUP_CUSTOMER VALUES(2);
INSERT INTO PICKUP VALUES(6,2);
/*ORDER SEVEN CUSTOMER THREE*/
INSERT INTO CUST_ORDER VALUES(7,'Delivery',3.20,'2020-03-02 18:17:00',13.25);
INSERT INTO PIZZA VALUES (14,13.25,3.20,'Completed',9,7);
INSERT INTO CHOICE_OF VALUES(0,14,4);
INSERT INTO CHOICE_OF VALUES(0,14,5);
INSERT INTO CHOICE_OF VALUES(0,14,6);
INSERT INTO CHOICE_OF VALUES(0,14,8);
INSERT INTO CHOICE_OF VALUES(0,14,14);
INSERT INTO CUSTOMER VALUES(3,'Frank','Turner','864-232-8944');
INSERT INTO DELIVERY_CUSTOMER VALUES(3,'6745 Wessex St','Anderson','SC','29621');
INSERT INTO DELIVERY VALUES(7,3);
/*ORDER EIGHT CUSTOMER FOUR*/
INSERT INTO CUST_ORDER VALUES(8,'Delivery',6.30,'2020-03-06 20:32:00',24.00);
INSERT INTO PIZZA VALUES (15,12.00,3.75,'Completed',9,8);
INSERT INTO CHOICE_OF VALUES(1,15,14);
INSERT INTO PIZZA VALUES(16,12.00,2.55,'Completed',9,8);
INSERT INTO CHOICE_OF VALUES(0,16,13);
INSERT INTO CHOICE_OF VALUES(1,16,1);
INSERT INTO ORDER_DISCOUNT VALUES(8,1);
INSERT INTO CUSTOMER VALUES(4,'Milo','Aukerman','864-878-5679');
INSERT INTO DELIVERY_CUSTOMER VALUES(4,'8879 Suburban Home','Anderson','SC','29621');
INSERT INTO DELIVERY VALUES(8,4);






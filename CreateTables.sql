SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE TOPPINGS 
(
Topping_ID int NOT NULL, 
Name varchar(100) NOT NULL, 
Price_to_customer decimal(6, 2) NOT NULL, 
cost_to_business decimal(6, 2) NOT NULL, 
Inventory_level int DEFAULT 0, 
Amount_used_P decimal(3,2) NOT NULL, 
Amount_used_M decimal(3,2) NOT NULL, 
Amount_used_L decimal(3,2) NOT NULL, 
Amount_used_XL decimal(3,2) NOT NULL, 
UNIQUE (Name), 
PRIMARY KEY (Topping_ID)
);

CREATE TABLE BASE_PRICE 
(
Base_Price_ID int NOT NULL, 
Base_Cost decimal(6, 2) NOT NULL, 
Price decimal(6, 2) NOT NULL, 
Crust_Type varchar(100) NOT NULL, 
Pizza_Size char(50) NOT NULL, 
PRIMARY KEY (Base_Price_ID)
);

CREATE TABLE CUSTOMER 
(
Customer_ID int NOT NULL, 
First_Name varchar(100) NOT NULL, 
Last_Name varchar(100) NOT NULL, 
Phone_Number varchar(20) NOT NULL, 
PRIMARY KEY (Customer_ID)
);

CREATE TABLE PIZZA 
(
Pizza_ID int NOT NULL, 
Associated_Price decimal(6, 2) NOT NULL, 
Associated_Cost decimal(6, 2) NOT NULL, 
status char(50) NOT NULL, 
Base_Price_ID int NOT NULL, 
Order_ID int NOT NULL, 
PRIMARY KEY (Pizza_ID), 
FOREIGN KEY (Base_Price_ID) REFERENCES BASE_PRICE(Base_Price_ID), 
FOREIGN KEY (Order_ID) REFERENCES CUST_ORDER(Order_ID)
);

CREATE TABLE CUST_ORDER 
(
Order_ID int NOT NULL, 
Order_Type char(50) NOT NULL, 
Total_Costs_To_Business decimal(6, 2) NOT NULL, 
Time_Stamp Timestamp NOT NULL, 
Total_Price_To_Customer decimal(6, 2) NOT NULL, 
PRIMARY KEY (Order_ID)
);

CREATE TABLE CHOICE_OF 
(
Extra_Quantity int DEFAULT 0, 
Pizza_ID int NOT NULL, 
Topping_ID int NOT NULL, 
PRIMARY KEY (Topping_ID, Pizza_ID), 
FOREIGN KEY (Pizza_ID) REFERENCES PIZZA(Pizza_ID), 
FOREIGN KEY (Topping_ID) REFERENCES TOPPINGS(Topping_ID)
);

CREATE TABLE PIZZA_DISCOUNT 
(
Pizza_ID int NOT NULL, 
Discount_ID int NOT NULL, 
PRIMARY KEY (Discount_ID, Pizza_ID), 
FOREIGN KEY (Pizza_ID) REFERENCES PIZZA(Pizza_ID), 
FOREIGN KEY (Discount_ID) REFERENCES DISCOUNT(Discount_ID)
);

CREATE TABLE ORDER_DISCOUNT 
(
Order_ID int NOT NULL, 
Discount_ID int NOT NULL, 
PRIMARY KEY (Discount_ID, Order_ID), 
FOREIGN KEY (Order_ID) REFERENCES CUST_ORDER(Order_ID), 
FOREIGN KEY (Discount_ID) REFERENCES DISCOUNT(Discount_ID)
);

CREATE TABLE DISCOUNT 
(
Discount_ID int NOT NULL, 
Discount_Name varchar(100) NOT NULL,
UNIQUE (Discount_Name), 
PRIMARY KEY (Discount_ID)
);

CREATE TABLE PERCENT_DISCOUNT 
(
Percent_Off int NOT NULL, 
Discount_ID int NOT NULL, 
PRIMARY KEY (Discount_ID), 
FOREIGN KEY (Discount_ID) REFERENCES DISCOUNT(Discount_ID)
);

CREATE TABLE DOLLAR_DISCOUNT 
(
Dollar_Off decimal(3,2) NOT NULL, 
Discount_ID int NOT NULL, 
PRIMARY KEY (Discount_ID), 
FOREIGN KEY (Discount_ID) REFERENCES DISCOUNT(Discount_ID)
);

CREATE TABLE DINE_IN 
(
Dine_Order_ID int NOT NULL, 
Table_Number int NOT NULL, 
PRIMARY KEY (Dine_Order_ID), 
FOREIGN KEY (Dine_Order_ID) REFERENCES CUST_ORDER(Order_ID)
);

CREATE TABLE SEAT_NUMBER 
(
Dine_Order_ID int NOT NULL, 
Seat_Number int NOT NULL, 
PRIMARY KEY (Dine_Order_ID, Seat_Number), 
FOREIGN KEY (Dine_Order_ID) REFERENCES DINE_IN(Dine_Order_ID)
);

CREATE TABLE PICKUP 
(
Pickup_Order_ID int NOT NULL, 
Pickup_Customer_ID int NOT NULL, 
PRIMARY KEY (Pickup_Order_ID), 
FOREIGN KEY (Pickup_Order_ID) REFERENCES CUST_ORDER(Order_ID), 
FOREIGN KEY (Pickup_Customer_ID) REFERENCES CUSTOMER(Customer_ID)
);

CREATE TABLE PICKUP_CUSTOMER 
(
Pickup_Customer_ID int NOT NULL, 
PRIMARY KEY (Pickup_Customer_ID)
);

CREATE TABLE DELIVERY 
(
Delivery_Order_ID int NOT NULL, 
Delivery_Customer_ID int NOT NULL, 
PRIMARY KEY (Delivery_Order_ID)
);

CREATE TABLE DELIVERY_CUSTOMER 
(
Delivery_Customer_ID int NOT NULL, 
Street varchar(100) NOT NULL, 
City varchar(100) NOT NULL, 
State varchar(100) NOT NULL, 
Zip varchar(100) NOT NULL, 
PRIMARY KEY (Delivery_Customer_ID)
);

SET FOREIGN_KEY_CHECKS=1;

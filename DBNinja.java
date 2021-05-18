package cpsc4620.antonspizza;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/*
This file is where most of your code changes will occur
You will write the code to retrieve information from the database, or save information to the database

The class has several hard coded static variables used for the connection, you will need to change those to your connection information

This class also has static string variables for pickup, delivery and dine-in. If your database stores the strings differently (i.e "pick-up" vs "pickup") changing these static variables will ensure that the comparison is checking for the right string in other places in the program. You will also need to use these strings if you store this as boolean fields or an integer.


*/

/**
 * A utility class to help add and retrieve information from the database
 */

public final class DBNinja {
    //enter your user name here
    private static String user = "antnsPzr_qucb";
    //enter your password here
    private static String password = "noahjonathan4620";
    //enter your database name here
    private static String database_name = "antonsPizzeria_2gx0";
    //Do not change the port. 3306 is the default MySQL port
    private static String port = "3306";
    private static Connection conn;

    //Change these variables to however you record dine-in, pick-up and delivery, and sizes and crusts
    public final static String pickup = "Pickup";
    public final static String delivery = "Delivery";
    public final static String dine_in = "Dine In";

    public final static String size_s = "small";
    public final static String size_m = "medium";
    public final static String size_l = "Large";
    public final static String size_xl = "X-Large";

    public final static String crust_thin = "Thin";
    public final static String crust_orig = "Original";
    public final static String crust_pan = "Pan";
    public final static String crust_gf = "Gluten-Free";



    /**
     * This function will handle the connection to the database
     * @return true if the connection was successfully made
     * @throws SQLException
     * @throws IOException
     */
    private static boolean connect_to_db() throws SQLException, IOException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println ("Could not load the driver");

            System.out.println("Message     : " + e.getMessage());


            return false;
        }

        conn = DriverManager.getConnection("jdbc:mysql://mysql1.cs.clemson.edu:"+port+"/"+database_name, user, password);
        return true;
    }

    /**
     *
     * @param o order that needs to be saved to the database
     * @throws SQLException
     * @throws IOException
     * @requires o is not NULL. o's ID is -1, as it has not been assigned yet. The pizzas do not exist in the database
     *          yet, and the topping inventory will allow for these pizzas to be made
     * @ensures o will be assigned an id and added to the database, along with all of it's pizzas. Inventory levels
     *          will be updated appropriately
     */
    public static void addOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
		/* add code to add the order to the DB. Remember to add the pizzas and discounts as well, which will involve multiple tables. Customer should already exist. Toppings will need to be added to the pizzas.

		It may be beneficial to define more functions to add an individual pizza to a database, add a topping to a pizza, etc.

		Note: the order ID will be -1 and will need to be replaced to be a fitting primary key.

		You will also need to add timestamps to your pizzas/orders in your database. Those timestamps are not stored in this program, but you can get the current time before inserting into the database

		Remember, when a new order comes in the ingredient levels for the topping need to be adjusted accordingly. Remember to check for "extra" of a topping here as well.

		You do not need to check to see if you have the topping in stock before adding to a pizza. You can just let it go negative.
		*/

    int ID = -5;
      String query = "Select MAX(Order_ID) From CUST_ORDER;";
      Statement stmt = conn.createStatement();
      try { // START OF TRY STATEMENT
          ResultSet rset = stmt.executeQuery(query);
          while(rset.next())
          {
            ID = rset.getInt(1);
          }
      }
      /******************************************************************************
        This Is my catch block in there are any messups in the code I have created.
        it will propmpt the user with an error message.
      ******************************************************************************/
      catch (SQLException e) { //start of catch statement
          System.out.println("Error getting max customer ID");
          while (e != null) {
              System.out.println("Message     : " + e.getMessage());
              e = e.getNextException();
          }
          //don't leave your connection open!
          conn.close();
      }
      ID+=1;

      int PID = -5;
        String query9 = "Select MAX(Pizza_ID) From PIZZA;";
        Statement pidstmt = conn.createStatement();
        try { // START OF TRY STATEMENT
            ResultSet rset = pidstmt.executeQuery(query9);
            while(rset.next())
            {
              PID = rset.getInt(1);
            }
        }
        /******************************************************************************
          This Is my catch block in there are any messups in the code I have created.
          it will propmpt the user with an error message.
        ******************************************************************************/
        catch (SQLException e) { //start of catch statement
            System.out.println("Error getting max customer ID");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            //don't leave your connection open!
            conn.close();
        }
        PID+=1;


      String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      int toppingID = 0;
              Discount d = getDiscount(o.getID());

              o = new Order(ID,o.getCustomer(),o.getType());

              String query1 = "insert into CUST_ORDER (Order_ID, Order_Type, Total_Costs_To_Business, Time_Stamp, Total_Price_To_Customer) values ("+ID+",'"+o.getType()+"', '0', '"+ timeStamp +"', '0');";
              Statement q1 = conn.createStatement();

        try { // START OF TRY STATEMENT
            int rset = q1.executeUpdate(query1);
        }
        /******************************************************************************
          This Is my catch block in there are any messups in the code I have created.
          it will propmpt the user with an error message.
        ******************************************************************************/
        catch (SQLException e) { //start of catch statement
            System.out.println("Error Inserting Order Values");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
        }



      String query2 =  "INSERT INTO PIZZA (Pizza_ID, Associated_Price, Associated_Cost, status, Base_Price_ID, Order_ID) values ("+PID+",(Select Base_Price_ID from BASE_PRICE where Pizza_Size = 'small' and Crust_Type = 'Thin'),0.0,'In Progress',(Select Base_Price_ID from BASE_PRICE where Pizza_Size = 'small' and Crust_Type = 'Thin'), "+ID+");";
        Statement stmtP = conn.createStatement();

        try {
                      int rset = stmtP.executeUpdate(query2);

                  }
                  catch (SQLException e) {
                      System.out.println("Error inserting Pizza ID");
                      while (e != null) {
                          System.out.println("Message     : " + e.getMessage());
                          e = e.getNextException();
                      }
                  }



        conn.close();

    }



    /**
     *
     * @param c the new customer to add to the database
     * @throws SQLException
     * @throws IOException
     * @requires c is not null. C's ID is -1 and will need to be assigned
     * @ensures c is given an ID and added to the database
     */
    public static void addCustomer(ICustomer c) throws SQLException, IOException
    {
        connect_to_db();
		/*add code to add the customer to the DB.
		Note: the ID will be -1 and will need to be replaced to be a fitting primary key
		Note that the customer is an ICustomer data type, which means c could be a dine in, carryout or delivery customer
		*/

    int ID = -5;
    String address, phoneNumber, name, customerAddition, addcustomerAddition, query;
    /******************************************************************************
      This is my SELECT statement for ADDCUSTOMER
    ******************************************************************************/
    query = "Select MAX(Customer_ID) From CUSTOMER;";
        Statement stmt = conn.createStatement();
            try { // START OF TRY STATEMENT
                ResultSet rset = stmt.executeQuery(query);
                while(rset.next())
                {
                  ID = rset.getInt(1);
                }
            } // END OF TRY STATMMENT
            catch (SQLException e) { //start of catch statement
                System.out.println("Error getting max customer ID");
                while (e != null) {
                    System.out.println("Message     : " + e.getMessage());
                    e = e.getNextException();
                }
                conn.close();
            } // END OF CATCH STATEMENT
            ID+=1;
            /******************************************************************************
            This section of code is how I add a customer for delivery. I insert These
            values into the CUSTOMER and DELIVERY_CUSTOMER database.
            ******************************************************************************/
            if (c instanceof DeliveryCustomer) {
              DeliveryCustomer cust = (DeliveryCustomer) c;
              address = cust.getAddress();
              phoneNumber = cust.getPhone();
              name = cust.getName();
              /******************************************************************************
                This is my INSERT statement for ADDCUSTOMER
              ******************************************************************************/
              customerAddition = "INSERT INTO CUSTOMER VALUES ("+ID+", '"+name+"', '"+phoneNumber+"');";
              /******************************************************************************
                This is my INSERT statement for ADDCUSTOMER
              ******************************************************************************/
              addcustomerAddition = "INSERT INTO DELIVERY_CUSTOMER VALUES ("+ID+",'"+address+"');";

              Statement stmt2 = conn.createStatement();
              try { // START OF TRY STATEMENT
                int rset2 = stmt2.executeUpdate(customerAddition);
                int rset3 = stmt2.executeUpdate(addcustomerAddition);
              } // END OF TRY STATEMENT
              /******************************************************************************
                This Is my catch block in there are any messups in the code I have created.
                it will propmpt the user with an error message.
              ******************************************************************************/
              catch (SQLException e) { //start of catch statement
                  System.out.println("Error adding customer");
                  while (e != null) {
                      System.out.println("Message     : " + e.getMessage());
                      e = e.getNextException();
                  }
                  conn.close();
              }// END OF CATCH STATEMENT
            }
            /******************************************************************************
              This section of code is how I add a customer for dineout. I insert These
              values into the CUSTOMER database.
            ******************************************************************************/
            else if(c instanceof DineOutCustomer) {
              DineOutCustomer cust = (DineOutCustomer) c;
              phoneNumber = cust.getPhone();
              name = cust.getName();
              /******************************************************************************
                This is my iNSERT statement for ADDCUSTOMER
              ******************************************************************************/
              customerAddition = "INSERT INTO CUSTOMER VALUES ("+ID+", '"+name+"', '"+phoneNumber+"' '');";
              Statement stmt2 = conn.createStatement();
              try { // START OF TRY STATEMENT
                int rset2 = stmt2.executeUpdate(customerAddition);
              } // END OF TRY STATEMENT
              /******************************************************************************
                This Is my catch block in there are any messups in the code I have created.
                it will propmpt the user with an error message.
              ******************************************************************************/
              catch (SQLException e) { //start of catch statement
                  System.out.println("Error adding customer");
                  while (e != null) {
                      System.out.println("Message     : " + e.getMessage());
                      e = e.getNextException();
                  }
                  conn.close();
              } // END OF CATCH STATEMENT
            }

        conn.close();
    }

    /**
     *
     * @param o the order to mark as complete in the database
     * @throws SQLException
     * @throws IOException
     * @requires the order exists in the database
     * @ensures the order will be marked as complete
     */
     public static void CompleteOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
        /*add code to mark an order as complete in the DB. You may have a boolean field for this, or maybe a completed time timestamp. However you have it, */
        String up = "UPDATE PIZZA SET status = 'Completed' WHERE Order_ID = ?; ";

        PreparedStatement stmt;

        try{
          stmt = conn.prepareStatement(up);
          stmt.clearParameters();
          stmt.setInt(1,o.getID());

          int rset = stmt.executeUpdate();

        }

        catch(SQLException e){
          System.out.println("Error updating Order Status in CompleteOrder");
          while (e != null){
            System.out.println("Message     : " + e.getMessage());
            e = e.getNextException();
          }

         }

        conn.close();
    }
    /**
     *
     * @param t the topping whose inventory is being replenished
     * @param toAdd the amount of inventory of t to add
     * @throws SQLException
     * @throws IOException
     * @requires t exists in the database and toAdd > 0
     * @ensures t's inventory level is increased by toAdd
     */
    public static void AddToInventory(Topping t, double toAdd) throws SQLException, IOException
    {
        connect_to_db();
		/*add code to add toAdd to the inventory level of T. This is not adding a new topping, it is adding a certain amount of stock for a topping. This would be used to show that an order was made to replenish the restaurants supply of pepperoni, etc*/

    String ID = t.getName();
            /******************************************************************************
            This is my select statement for ADDTOINVENTORY
            ******************************************************************************/
            String query = "UPDATE TOPPINGS SET Inventory_level = Inventory_level +" + toAdd + "where Name = '" + ID + "';";

            Statement stmt = conn.createStatement();
            try { // START OF TRY STATEMENT
                int rset = stmt.executeUpdate(query);
              } // END OF TRY STATEMENT
            /******************************************************************************
            This Is my catch block in there are any messups in the code I have created.
            it will propmpt the user with an error message.
            ******************************************************************************/
            catch (SQLException e) { //start of catch statement
                System.out.println("Error updating inventory");
                while (e != null) {
                    System.out.println("Message     : " + e.getMessage());
                    e = e.getNextException();
                }
                conn.close();
            } // END OF CATCH STATEMENT

        conn.close();
    }


    /*
        A function to get the list of toppings and their inventory levels. I have left this code "complete" as an example of how to use JDBC to get data from the database. This query will not work on your database if you have different field or table names, so it will need to be changed

        Also note, this is just getting the topping ids and then calling getTopping() to get the actual topping. You will need to complete this on your own

        You don't actually have to use and write the getTopping() function, but it can save some repeated code if the program were to expand, and it keeps the functions simpler, more elegant and easy to read. Breaking up the queries this way also keeps them simpler. I think it's a better way to do it, and many people in the industry would agree, but its a suggestion, not a requirement.
    */

    /**
     *
     * @return the List of all toppings in the database
     * @throws SQLException
     * @throws IOException
     * @ensures the returned list will include all toppings and accurate inventory levels
     */
    public static ArrayList<Topping> getInventory() throws SQLException, IOException
    {
        //start by connecting
        connect_to_db();
        ArrayList<Topping> ts = new ArrayList<Topping>();
        //create a string with out query, this one is an easy one
        /******************************************************************************
          This is my select statement for GETINVENTORY
        ******************************************************************************/
        String query = "Select Topping_ID, Name, Inventory_Level, cost_to_business From TOPPINGS;";

        Statement stmt = conn.createStatement();
        try { // START OF TRY STATEMENT
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
					/*Use getInt, getDouble, getString to get the actual value. You can use the column number starting with 1, or use the column name as a string

					NOTE: You want to use rset.getInt() instead of Integer.parseInt(rset.getString()), not just because it's shorter, but because of the possible NULL values. A NUll would cause parseInt to fail

					If there is a possibility that it could return a NULL value you need to check to see if it was NULL. In this query we won't get nulls, so I didn't. If I was going to I would do:

					int ID = rset.getInt(1);
					if(rset.wasNull())
					{
						//set ID to what it should be for NULL, and whatever you need to do.
					}

					NOTE: you can't check for NULL until after you have read the value using one of the getters.

					*/
                int ID = rset.getInt(1);
                ts.add(getTopping(ID));
            }
        } // END OF TRY STATEMENT
        /******************************************************************************
          This Is my catch block in there are any messups in the code I have created.
          it will propmpt the user with an error message.
        ******************************************************************************/
        catch (SQLException e) { //start of catch statement
            System.out.println("Error loading inventory");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return ts;
        } //END OF CATCH STATMENT


        //end by closing the connection
        conn.close();
        return ts;
    }

    /**
     *
     * @return a list of all orders that are currently open in the kitchen
     * @throws SQLException
     * @throws IOException
     * @ensures all currently open orders will be included in the returned list.
     */
    public static ArrayList<Order> getCurrentOrders() throws SQLException, IOException
    {
        connect_to_db();
        String comp = "Completed";
        ArrayList<Order> os = new ArrayList<Order>();
		/*add code to get a list of all open orders. Only return Orders that have not been completed. If any pizzas are not completed, then the order is open.*/
    String query = "SELECT p.Order_ID, Associated_Cost, Order_Type, Customer_ID, Cust_Name, Phone_Number FROM PIZZA p JOIN CUST_ORDER c on p.Order_ID = c.Order_ID JOIN CUSTOMER WHERE status != '"+ comp +"';";
        int id = 0;
        double ass_cost = 0;
        String hold = "";
        String cust = "";
        String num = "";
        PreparedStatement stmt;


        try{
            stmt = conn.prepareStatement(query);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()){
                id = rset.getInt(1);
                ass_cost = rset.getDouble(2);
                hold = rset.getString(3);
                cust = rset.getString(5);
                num = rset.getString(6);
                os.add(getOrder(id, hold, ass_cost, cust, num));
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading inventory");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();


        }
        conn.close();

        return os;
    }

    /**
     *
     * @param size the pizza size
     * @param crust the type of crust
     * @return the base price for a pizza with that size and crust
     * @throws SQLException
     * @throws IOException
     * @requires size = size_s || size_m || size_l || size_xl AND crust = crust_thin || crust_orig || crust_pan || crust_gf
     * @ensures the base price for a pizza with that size and crust is returned
     */
    public static double getBasePrice(String size, String crust) throws SQLException, IOException
    {
        connect_to_db();
        double bp = 0.0;
        //add code to get the base price for that size and crust pizza Depending on how you store size and crust in your database, you may have to do a conversion
        /******************************************************************************
          This is my select statement for GETBASEPRICE
        ******************************************************************************/
        String BasePriceQuery = "SELECT Price FROM BASE_PRICE WHERE Pizza_Size = '"+size+"' AND Crust_Type = '"+crust+"';";
        PreparedStatement stmt;
        try { // START OF TRY STATEMENT
            stmt = conn.prepareStatement(BasePriceQuery);
            ResultSet rset = stmt.executeQuery();
            while(rset.next()) {
              bp = rset.getDouble(1);
            }
        } // END OF TRY STATEMENT
        /******************************************************************************
          This Is my catch block in there are any messups in the code I have created.
          it will propmpt the user with an error message.
        ******************************************************************************/
        catch (SQLException e) { //start of catch statement
            System.out.println("Error loading base price");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return bp;
        }// END OF CATCH STATEMENT

        conn.close();
        return bp;
    }

    /**
     *
     * @return the list of all discounts in the database
     * @throws SQLException
     * @throws IOException
     * @ensures all discounts are included in the returned list
     */
    public static ArrayList<Discount> getDiscountList() throws SQLException, IOException
    {
        ArrayList<Discount> discs = new ArrayList<Discount>();
        connect_to_db();
        //add code to get a list of all discounts
        /******************************************************************************
          This is my select statement for GETDISCOUNTLIST
        ******************************************************************************/
        String DiscountQuery = "SELECT Discount_ID FROM DISCOUNT;";

        Statement stmt = conn.createStatement();
        try { // START OF TRY STATEMENT
            ResultSet rset = stmt.executeQuery(DiscountQuery);

            while(rset.next()) {
              int ID = rset.getInt(1);
              discs.add(getDiscount(ID));
            }
          }// END OF TRY STATEMENT
        /******************************************************************************
        This Is my catch block in there are any messups in the code I have created.
        it will propmpt the user with an error message.
        ******************************************************************************/
        catch (SQLException e) { //start of catch statement
            System.out.println("Error loading discounts");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return discs;
        } // END OF CATCH STATEMENT


        conn.close();
        return discs;
    }

    /**
     *
     * @return the list of all delivery and carry out customers
     * @throws SQLException
     * @throws IOException
     * @ensures the list contains all carryout and delivery customers in the database
     */
    public static ArrayList<ICustomer> getCustomerList() throws SQLException, IOException
    {
        connect_to_db();
        ArrayList<ICustomer> custs = new ArrayList<ICustomer>();
        //add code to get a list of all customers
        /******************************************************************************
          This is my select statement for GETCUSTOMERLIST
        ******************************************************************************/
        String custQuery = "SELECT Customer_ID, Cust_Name, Phone_Number from CUSTOMER;";

        Statement stmt = conn.createStatement();
        try { // START OF TRY STATEMENT
            ResultSet rset = stmt.executeQuery(custQuery);

            while(rset.next()) {
              int ID = rset.getInt(1);
              custs.add(getCustomer(ID));
            }
          } // END OF TRY STATEMENT
        /******************************************************************************
        This Is my catch block in there are any messups in the code I have created.
        it will propmpt the user with an error message.
        ******************************************************************************/
        catch (SQLException e) { //start of catch statement
            System.out.println("Error loading customers");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return custs;
        } // END OF CATCH STATEMENT

        conn.close();
        return custs;
    }



	/*
	Note: The following incomplete functions are not strictly required, but could make your DBNinja class much simpler. For instance, instead of writing one query to get all of the information about an order, you can find the primary key of the order, and use that to find the primary keys of the pizzas on that order, then use the pizza primary keys individually to build your pizzas. We are no longer trying to get everything in one query, so feel free to break them up as much as possible

	You could also add functions that take in a Pizza object and add that to the database, or take in a pizza id and a topping id and add that topping to the pizza in the database, etc. I would recommend this to keep your addOrder function much simpler

	These simpler functions should still not be called from our menu class. That is why they are private

	We don't need to open and close the connection in these, since they are only called by a function that has opened the connection and will close it after
	*/


    private static Topping getTopping(int ID) throws SQLException, IOException
    {

        //add code to get a topping
		//the java compiler on unix does not like that t could be null, so I created a fake topping that will be replaced
        Topping t = new Topping("fake", 0.25, 100.0, -1);
        /******************************************************************************
          This is my select statement for GETTOPPING
        ******************************************************************************/
		String query = "Select Name, Price_to_customer, Inventory_level From TOPPINGS where Topping_ID = " + ID + ";";

        Statement stmt = conn.createStatement();
        try { // START OF TRY STATEMENT
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
					String tname = rset.getString(1);
					double price = rset.getDouble(2);
					double inv = rset.getDouble(3);

					t = new Topping(tname, price, inv, ID);
			}

		} // END OF TRY STATEMENT
    /******************************************************************************
      This Is my catch block in there are any messups in the code I have created.
      it will propmpt the user with an error message.
    ******************************************************************************/
		catch (SQLException e) { //start of catch statement
            System.out.println("Error loading Topping");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return t;
        }// END OF CATCH STATEMENT

        return t;

    }

    private static Discount getDiscount(int ID)  throws SQLException, IOException
    {

        //add code to get a discount

        Discount D = new Discount("fake", 0.25, 100.00, 4);
        /******************************************************************************
          This is my select statement for GETDISCOUNT
        ******************************************************************************/
        String query = "SELECT Discount_Name, Percent_Off, Dollar_Off FROM DISCOUNT WHERE DISCOUNT.Discount_ID = "+ID+";";

        Statement stmt = conn.createStatement();
    try { // START OF TRY STATEMENT
        ResultSet rset = stmt.executeQuery(query);

        while(rset.next())
        {
          String dname = rset.getString(1);
          double percent = rset.getDouble(2);
          Double dollar = rset.getDouble(3);
          D = new Discount(dname, percent, dollar, ID);
        }

      } // END OF TRY STATEMENT
    /******************************************************************************
    This Is my catch block in there are any messups in the code I have created.
    it will propmpt the user with an error message.
    ******************************************************************************/
    catch (SQLException e) { //start of catch statement
        System.out.println("Error loading Discount");
        while (e != null) {
            System.out.println("Message     : " + e.getMessage());
            e = e.getNextException();
        }
        conn.close();
        return D;
    } // END OF CATCH

        return D;

    }

    private static Pizza getPizza(int ID)  throws SQLException, IOException
    {

        //add code to get Pizza Remember, a Pizza has toppings and discounts on it
        Pizza P = new Pizza(0,"","",0.0);
        /******************************************************************************
          This is my select statement for GETPIZZA
        ******************************************************************************/
        String PizzaQuery = "SELECT Base_Price_ID, Pizza_Size, Crust_Type, Price FROM PIZZA as P, BASE_PRICE AS BP WHERE P.Base_Price_ID = "+ID+" AND P.Base_Price_ID = BP.Base_Price_ID;";
        Statement stmt = conn.createStatement();

        try { // START OF TRY STATEMENT
            ResultSet rset = stmt.executeQuery(PizzaQuery);
            while (rset.next()) {
                String sz = rset.getString(3);
                String crus = rset.getString(4);
                int BID = rset.getInt(5);
                P = new Pizza(ID, sz, crus, BID);
            }
        } // END OF TRY STATEMENT
        /******************************************************************************
          This Is my catch block in there are any messups in the code I have created.
          it will propmpt the user with an error message.
        ******************************************************************************/
        catch (SQLException e){
            System.out.println("Error loading Pizza");
            while (e != null){
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            conn.close();
            return P;
        } // END OF CATCH
        return P;
    }

    /******************************************************************************
      This section of code helps me with getting the customers in the database.
    ******************************************************************************/
    private static ICustomer getCustomer(int ID)  throws SQLException, IOException
    {

        //add code to get customer
        ICustomer C = new DineOutCustomer(-1, "fake", "nunumber");
        /******************************************************************************
          This is my select statement for CUSTOMER
        ******************************************************************************/
        String query = "SELECT Cust_Name, Phone_Number, Address FROM CUSTOMER, PICKUP_CUSTOMER LEFT OUTER JOIN DELIVERY_CUSTOMER ON PICKUP_CUSTOMER.Pickup_Customer_ID = DELIVERY_CUSTOMER.Delivery_Customer_ID WHERE Customer_ID = "+ID+";";

        Statement stmt = conn.createStatement();

        try { // START OF TRY STATEMENT
        ResultSet rset = stmt.executeQuery(query);
        while(rset.next()){

          /******************************************************************************
            This section of code displays an error if the user inputted a Null name.
          ******************************************************************************/
          String name = rset.getString(1);
          if(rset.wasNull()) {
            System.out.println("ERROR: name is NULL"); // ERROR MESSAGE
}
          /******************************************************************************
          This section of code displays an error if the use inputted a null phone value
          ******************************************************************************/
          String phone = rset.getString(2);
          if(rset.wasNull()) {
            System.out.println("ERROR: phone is NULL"); // ERROR MESSAGE
}
          String address = rset.getString(3);
          /******************************************************************************
            This section of code selects dineout customer if there was no address value
            entered into the code,
          ******************************************************************************/
          if(rset.wasNull()){
            C = new DineOutCustomer(ID, name, phone); //dineout customer
          }

          /******************************************************************************
            This section of code is selects Delivery Customer if there was an address
            value. this way we can tell who is who based on input.
          ******************************************************************************/
          else {
            C = new DeliveryCustomer(ID, name, phone, address); //delivery customer
          }
        }

      } // END OF TRY STATEMENT
        /******************************************************************************
          This Is my catch block in there are any messups in the code I have created.
          it will propmpt the user with an error message.
        ******************************************************************************/
        catch (SQLException e) { //start of catch statement
        System.out.println("Error loading Customer");
        while (e != null) {
            System.out.println("Message     : " + e.getMessage());
            e = e.getNextException();
        }
        conn.close();
        return C;
      } // END OF CATCH

        return C;


    }

    private static Order getOrder(int ID,String gg, double ass_cost, String cust, String num)  throws SQLException, IOException
    {

        //"Something" is the customer field, "where" is the phone number,
        //add code to get an order. Remember, an order has pizzas, a customer, and discounts on it
        ICustomer temp_Cust = new DineOutCustomer(ID, cust, num);;
        Order O = new Order(ID, temp_Cust, gg);

/*      //  ArrayList<Integer> gg = new ArrayList<Integer>(1);
        String cust_type = "" ;
        String cust_type_query = "SELECT Order_Type FROM CUST_ORDER WHERE Order_ID = ?;";
         String cust_id_query = "SELECT Customer_ID, Cust_Name, Phone_Number FROM CUSTOMER WHERE Customer_ID = ?;";
        String pizza_ID_Query = "SELECT Pizza_ID FROM PIZZA WHERE Order_ID = ?;";
       // String discount_ID_Query = "SELECT Discount_ID FROM DISCOUNT WHERE Discount_ID = ?;";

        PreparedStatement stmt4;

        try{
          stmt4 = conn.prepareStatement(cust_type_query);
          stmt4.clearParameters();
          stmt4.setInt(1,ID);

          ResultSet cust_type_Set = stmt4.executeQuery();

          while(cust_type_Set.next()){
            cust_type = cust_type_Set.getString(1);
          }
        }
        catch(SQLException e){
           System.out.println("ERROR When Getting Customer type, 'getOrder()'");
           while(e != null){
               System.out.println("Message      :  " + e.getMessage());
               e = e.getNextException();
           }
           return O;
        }
        if(cust_type == "Dine In"){
           temp_Cust = new DineInCustomer(ID, gg, -1);
           O =  new Order(-1, temp_Cust, "nope");


        }
        else if(cust_type == "Pickup"){
          temp_Cust = new DineOutCustomer(-1, "nope", "");
          O = new Order(-1, temp_Cust, "priest");



        }
        else if(cust_type == "Delivery"){
           temp_Cust = new DeliveryCustomer(-1, "nope", "poop","more");
           O = new Order(-1, temp_Cust, "help");


        }
        else{
         // Order O = new Order(-1, -1, "nope");

        }

        PreparedStatement stmt;

        try{
            stmt = conn.prepareStatement(cust_id_query);
            stmt.clearParameters();
            stmt.setInt(2,ID);

            ResultSet cust_id_Set = stmt.executeQuery();

            while(cust_id_Set.next()){
                int customer_ID = cust_id_Set.getInt(1);
            }
        }

        catch(SQLException e){
            System.out.println("ERROR When Getting Customer ID, 'getOrder()'");
            while(e != null){
                System.out.println("Message      :  " + e.getMessage());
                e = e.getNextException();
            }
            return O;
        }

        PreparedStatement stmt2;

        try{
            stmt2 = conn.prepareStatement(pizza_ID_Query);
            stmt2.clearParameters();
            stmt2.setInt(3, ID);

            ResultSet pizza_id_Set = stmt2.executeQuery();

            while(pizza_id_Set.next()){
                int pizza_id = pizza_id_Set.getInt(1);
            }
        }


        catch(SQLException e){
            System.out.println("Error When getting PIZZA_ID, 'getOrder()'");
            while(e != null){
                System.out.println("Message      :  " + e.getMessage());
                e = e.getNextException();
            }
            return O;
        }*/
        return O;
    }
}

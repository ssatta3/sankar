package Server;

import java.net.*;
import java.io.*;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.*;
//import java.io.*;
 
 
import java.net.*;

import java.io.*;

 

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.util.*;

//import java.io.*;

 

 

import java.net.*;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
//import java.io.*;
 

public class Server1 {
    private static ArrayList<CustomerInfo> allCustomerArray = new ArrayList<CustomerInfo>();
    private static ArrayList<CustomerInfo> onlineCustomerArray = new ArrayList<CustomerInfo>();
    private static ArrayList<Products> availableProducts = new ArrayList<Products>();
    private static ArrayList<Products> currentInventory = new ArrayList<Products>();
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static Server1 mainServer;

    public static void main(String[] args) throws IOException {
    int portNumber;
    mainServer = new Server1();
         
        portNumber = 4444;
        init_accounts();
        InitialiseProducts();
        new Thread( new ServerConsole(mainServer)).start();
        try { 
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception " + e);
            System.exit(1);
        }
        while (true) {
            try{
                clientSocket = serverSocket.accept();

            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Exception " + e);
                System.exit(1);
            }
            new Thread( new ClientThread(mainServer, clientSocket,allCustomerArray)).start();
        }

    }
    // initialize some accounts
    static void init_accounts() {
        // customer 1 
        CustomerInfo tempinfo = new CustomerInfo();
        tempinfo.setCustomerNumber(1);
        tempinfo.setCustomerName("Jack Sparrow");
        tempinfo.setCustomerEmail("jack.sparrow@caribbean.com");
        tempinfo.setCustomerAddress("1 Black Pearl, Caribbean city");
        tempinfo.setCustomerPass("itsCaptain");
        allCustomerArray.add(tempinfo);
        // customer 2 
        //allCustomerArray[1] = new CustomerInfo();
        tempinfo = new CustomerInfo();
        tempinfo.setCustomerNumber(2);
        tempinfo.setCustomerName("Willy Wonka");
        tempinfo.setCustomerEmail("willy.wonka@cfactory.com");
        tempinfo.setCustomerAddress("1000 factory dr,  Chocolate city");
        tempinfo.setCustomerPass("GoldTicket");
        allCustomerArray.add(tempinfo);
        // customer 3 
        tempinfo = new CustomerInfo();
        tempinfo.setCustomerNumber(3);
        tempinfo.setCustomerName("Dean Corso");
        tempinfo.setCustomerEmail("dean.corso@rarebooks.com");
        tempinfo.setCustomerAddress("665 ninth road, Gates city ");
        tempinfo.setCustomerPass("MumboJumbo");
        allCustomerArray.add(tempinfo);
        // customer 4 
        tempinfo = new CustomerInfo();
        tempinfo.setCustomerNumber(4);
        tempinfo.setCustomerName("Donnie Brasco");
        tempinfo.setCustomerEmail("donnie.brasco@imafia.com");
        tempinfo.setCustomerAddress("665 Pistone way, Ruggiero city ");
        tempinfo.setCustomerPass("4getBoutIt");
        allCustomerArray.add(tempinfo);

    }
    static void InitialiseProducts(){
    	Products p1 = new Products();
    	p1.productName = "pens";
    	p1.ProductID=1;
    	p1.count=6;
    	availableProducts.add(p1);
    	Products p2 = new Products();
    	p2.productName = "pens";
    	p2.ProductID=2;
    	p2.count=6;
    	availableProducts.add(p2);
    	Products p3 = new Products();
    	p3.productName = "pens";
    	p3.ProductID=3;
    	p3.count=6;
    	availableProducts.add(p3);
    	Products p4 = new Products();
    	p4.productName = "pens";
    	p4.ProductID=4;
    	p4.count=6;
    	availableProducts.add(p4);
    	Products p5 = new Products();
    	p5.productName = "pens";
    	p5.ProductID=5;
    	p5.count=6;
    	availableProducts.add(p5);
    	Products p6 = new Products();
    	p6.productName = "pens";
    	p6.ProductID=6;
    	p6.count=6;
    	availableProducts.add(p6);
    }
    static int add_account(CustomerInfo newCust) {
        CustomerInfo[] myInfoArray = allCustomerArray.toArray(new CustomerInfo[allCustomerArray.size()]);
        int newAcc = myInfoArray[myInfoArray.length - 1].getCustomerNumber()+1;
        newCust.setCustomerNumber(newAcc);
        allCustomerArray.add(newCust);
        return newAcc;
    }
    static int add_online_account(CustomerInfo onCust) {
        onlineCustomerArray.add(onCust);
        return onlineCustomerArray.size();
    }
    static int remove_online_account(CustomerInfo onCust) {
        CustomerInfo[] myInfoArray = onlineCustomerArray.toArray(new CustomerInfo[onlineCustomerArray.size()]);
        if(myInfoArray.length == 0){
            System.out.println("NO ONE ONLINE");
            System.out.println("=================================");
        } else {
            for(int i = 0; i < myInfoArray.length; i++) {
                if(myInfoArray[i].getCustomerNumber() == onCust.getCustomerNumber()){
                    onlineCustomerArray.remove(i);
                }
            }
        }
        myInfoArray = onlineCustomerArray.toArray(new CustomerInfo[onlineCustomerArray.size()]);
        return onlineCustomerArray.size();
    }

    static void print_all_accounts() {
        CustomerInfo[] myInfoArray = allCustomerArray.toArray(new CustomerInfo[allCustomerArray.size()]);
        for(int i = 0; i < myInfoArray.length; i++) {
            System.out.println("Number: " + myInfoArray[i].getCustomerNumber());
            System.out.println("Name: " + myInfoArray[i].getCustomerName());
            System.out.println("Email: " + myInfoArray[i].getCustomerEmail());
            System.out.println("Address: " + myInfoArray[i].getCustomerAddress());
            System.out.println("Pass: " + myInfoArray[i].getCustomerPass());
            System.out.println("=================================");
        }
    }
    static void print_AvailableProducts(){
    	Products[] availableProductsArr = availableProducts.toArray(new Products[availableProducts.size()]);
    	for(int i=0;i<availableProductsArr.length;i++){
    		System.out.println("ProductName: "+ availableProductsArr[i].productName);
    		System.out.println("ProductID: " + availableProductsArr[i].ProductID);
    		System.out.println("ProductCount:  "+availableProductsArr[i].count);
    	}
    }
    static void print_online_accounts() {
        CustomerInfo[] myInfoArray = onlineCustomerArray.toArray(new CustomerInfo[onlineCustomerArray.size()]);
//        System.out.println("myInfoArray.length = " + myInfoArray.length );
        if(myInfoArray.length == 0){
            System.out.println("NO ONE ONLINE");
            System.out.println("=================================");
        } else {
            for(int i = 0; i < myInfoArray.length; i++) {
                System.out.println("Number: " + myInfoArray[i].getCustomerNumber());
                System.out.println("Name: " + myInfoArray[i].getCustomerName());
                System.out.println("Email: " + myInfoArray[i].getCustomerEmail());
                System.out.println("Address: " + myInfoArray[i].getCustomerAddress());
                System.out.println("Pass: " + myInfoArray[i].getCustomerPass());
                System.out.println("=================================");
            }
        }
    }


}

class ServerConsole implements Runnable {
    private static Server1 parent = null;
    private static int exit = 0;
    public ServerConsole(Server1 par) {
        parent = par;
    }
    public void run() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        
        Object[] opts = {"Current Logins", "All Customers","availableProducts"};
        JLabel lbl = new JLabel("Please press the buttons to get information in console ");

        panel.add(lbl);
        JTextArea txt = new JTextArea(20,40);
        txt.setEditable(false);
        PrintStream pstream = new PrintStream(new textAreaOutput(txt));
        System.setOut(pstream);
        JScrollPane scroll = new JScrollPane(txt);
        panel.add(scroll);
        while(exit == 0) {
            int opt = JOptionPane.showOptionDialog(null, panel, "Server Console",  
                                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);

            if(opt == JOptionPane.NO_OPTION){
                System.out.println("All Customers");
                parent.print_all_accounts();
            } 
            else if (opt == JOptionPane.YES_OPTION ) {
                System.out.println("Current Logins");
                parent.print_online_accounts();
            }
            else if (opt == JOptionPane.CLOSED_OPTION) {
                System.out.println("CLOSING server console... Kill the server and restart for console");
                exit = 1;
            }
            else if(opt==JOptionPane.CANCEL_OPTION){
            	System.out.println("availableProducts");
            	parent.print_AvailableProducts();
            }
        }
    }

}
class textAreaOutput extends OutputStream {
    private JTextArea textarea;
    public textAreaOutput(JTextArea txt){
        textarea = txt;
    }
    @Override
    public void write(int b) throws IOException {
        textarea.append(String.valueOf((char) b));
        textarea.setCaretPosition(textarea.getDocument().getLength());
    }
}
class ClientThread implements Runnable{

    private Socket clientSocket = null;
    private CustomerInfo cust = null;
    private CustomerInfo cust_full_info = null;
    private Server1 parent = null;
    private ArrayList<CustomerInfo> allCustomerArray = new ArrayList<CustomerInfo>();
    private static final int NONE = 0;
    private static final int LOGIN = 1;
    private static final int REGISTER = 2;
    private static final int EXIT = 3;
    private static final int GETOPT = 4;
//    private static final int LOGIN_SEND = 5;
    private static final int BAD_LOGIN = 6;
    private static final int GOOD_LOGIN = 7;
    private static final int WAIT = 8;
    private static final int BAD_REGISTER = 9;
    private static final int GOOD_REGISTER = 10;
    private static final int ADDTOCART=11;
    private ObjectOutputStream outServer = null;
    private ObjectInputStream inServer = null;
    private CustomerCart cart = null;
    
    public ClientThread(Server1 par, Socket cSock,ArrayList<CustomerInfo> allCustomers) {
        clientSocket = cSock;
        cust = new CustomerInfo();
        allCustomerArray = allCustomers;
        parent = par;
    }

    public void run() {
        try {
            outServer = new ObjectOutputStream(clientSocket.getOutputStream());
            inServer = new ObjectInputStream(clientSocket.getInputStream());
    
            CustomerInfo[] myInfoArray = null;
            int step = WAIT;
            int exit = 0;
            while(exit == 0) {
                switch(step) {
                    case LOGIN:
                                String email = cust.getCustomerEmail();
                                String pass = cust.getCustomerPass();
                                int login_success = 0;
                                myInfoArray = allCustomerArray.toArray(new CustomerInfo[allCustomerArray.size()]);
                                String temp_email;
                                String temp_pass;
                                for(int i = 0; i < myInfoArray.length; i++) {
                                    temp_email = myInfoArray[i].getCustomerEmail();
                                    temp_pass = myInfoArray[i].getCustomerPass();
                                    if(temp_email.equals(email) && temp_pass.equals(pass)){
                                        login_success = 1;
                                        parent.add_online_account(myInfoArray[i]);
                                        cust_full_info = myInfoArray[i];
                                        break;
                                    }
                                }
                                if(login_success == 1) {
                                    step = GOOD_LOGIN;
                                    cust = cust_full_info;
                                    cust.setCustomerCmd(GOOD_LOGIN);
                                    cart = new CustomerCart(cust.getCustomerNumber());
                                }
                                else {
                                    step = BAD_LOGIN;
                                    cust.setCustomerCmd(BAD_LOGIN);
                                }
                                outServer.writeObject(cust);
                                break;
                    case BAD_LOGIN:
                                step = WAIT;
                                break;
                    case GOOD_LOGIN:
                                step = WAIT;
                                break;
                    case REGISTER:
                                step = newRegister();
                                break;
                    case BAD_REGISTER:
                                step = WAIT;
                                break;
                    case GOOD_REGISTER:
                                cust_full_info = cust;
                                step = WAIT;
                                break;
                    case EXIT:
                                parent.remove_online_account(cust_full_info);
                                exit = 1;
                                break;
                    case WAIT:
                                cust = (CustomerInfo)inServer.readObject();
                                step = cust.getCustomerCmd();
                                break;
                    case ADDTOCART:
                    	        int x1=25;
                    	        cust = (CustomerInfo)inServer.readObject();
                    	        int y1 = cust.getCount();
                    	        x1= x1+y1;
                    	       System.out.println(x1);
                    	      break;
                }
            }

            outServer.close();
            inServer.close();
//            System.out.println("Server Thread Exiting session");
        } catch (IOException e) {
            //report exception somewhere.
            //e.printStackTrace();
        }  catch (Exception e) {
            System.err.println("Exception " + e);
            System.exit(1);
        } 
    }
    public int newRegister(){
        int new_acc = -1;// = parent.add_account(cust);
        CustomerInfo[] myInfoArray;
        CustomerInfo newInfo;
        int temp_acc = 0;
        int register_success = 0;
        // sanity check the new account
        int sanity = sanityCheckNewAcc();
        if(sanity == 0){ // sanity == 0 means no issues
            System.out.println("Server sanity check good");
            new_acc = parent.add_account(cust);
            myInfoArray = allCustomerArray.toArray(new CustomerInfo[allCustomerArray.size()]);
            //parent.print_all_accounts();
            for(int i = 0; i < myInfoArray.length; i++) {
                temp_acc = myInfoArray[i].getCustomerNumber();
                if(temp_acc == new_acc){
                    if(cust.getCustomerEmail().equals(myInfoArray[i].getCustomerEmail())){
                        register_success = 1;
                        System.out.println("Server register_success = " + register_success);
                        break;
                    }
                }
            }
        }
        if(register_success == 1){
//            System.out.println("Server REGISTER SUCCESS");
            cust.setCustomerCmd(GOOD_REGISTER);
            cust.setCustomerNumber(new_acc);
            // update logged in list
            parent.add_online_account(cust);
            cart  = new CustomerCart(cust.getCustomerNumber());
            try{
                outServer.writeObject(cust);
            }  catch (Exception e) {
                System.err.println("Exception " + e);
                System.exit(1);
            }
            return(GOOD_REGISTER);
        } else {
//            System.out.println("Server REGISTER UNSUCCESSFUL");
            cust.setCustomerCmd(BAD_REGISTER);
            cust.setCustomerNumber(-1);
            try{
                outServer.writeObject(cust);
            }  catch (Exception e) {
                System.err.println("Exception " + e);
                System.exit(1);
            }
            return(BAD_REGISTER);
        }
    }
    public int sanityCheckNewAcc(){
        if(cust.getCustomerEmail().equals("") || cust.getCustomerEmail().equals("BAD_EMAIL") ){
            System.out.println("Server bad email");
            return 1;
        }
        if(cust.getCustomerAddress().equals("") || cust.getCustomerAddress().equals("BAD_ADDR") ){
            System.out.println("Server bad addr");
            return 1;
        }
        if(cust.getCustomerPass().equals("") || cust.getCustomerPass().equals("BAD_PASS") ){
            System.out.println("Server bad pass");
            return 1;
        }
        if(cust.getCustomerName().equals("") || cust.getCustomerName().equals("BAD_NAME") ){
            System.out.println("Server bad name");
            return 1;
        }
        return 0;
    }
}

class CustomerInfo implements Serializable {
    private static final int NONE = 0;
    private static final int LOGIN = 1;
    private static final int REGISTER = 2;
    private static final int EXIT = 3;

    private int accNum;
    private String emailId;
    private String name;
    private String password;
    private String address;
    private int cmd;
    private int productID;
	private int count;
	ArrayList<CustomerProducts> cart;
    public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	CustomerInfo() {
        accNum = -1;
        emailId = "BAD_EMAIL";
        name = "BAD_NAME";
        password = "BAD_PASS";
        address = "BAD_ADDR";
        cmd = NONE;
    }
    public int getCustomerNumber() {
        return accNum;
    }
    public void setCustomerNumber(int num) {
        accNum = num;
    }
    public String getCustomerEmail() {
        return emailId;
    }
    public void setCustomerEmail(String email) {
        emailId = email;
    }
    public String getCustomerName() {
        return name;
    }
    public void setCustomerName(String nam) {
        name = nam;
    }
    public String getCustomerAddress() {
        return address;
    }
    public void setCustomerAddress(String addr) {
        address = addr;
    }
    public String getCustomerPass() {
        return password;
    }
    public void setCustomerPass(String pass) {
        password = pass;
    }
    public int getCustomerCmd() {
        return cmd;
    }
    public void setCustomerCmd(int num) {
        cmd = num;
    }
}

		class Products{
			String productName;
			int ProductID;
			int count;
		}
	    class CustomerProducts{
	    	int productID;
	    	int count;
	    	CustomerProducts(int productID){
	    	this.productID = productID;
	    }
	    }
    class CustomerCart{
    	int customerNumber;
    	ArrayList<CustomerProducts> cart = new ArrayList<CustomerProducts>();
    	CustomerCart(int customerNumber){
    	this.customerNumber= customerNumber;
    }
    	void addToCart(int ProductID,int count){
            for(int i=0;i<cart.size();i++)
            {
            	if(cart.get(i).productID ==ProductID)
            	{
            		cart.get(i).count +=count;
            	}else{
            		cart.add(new CustomerProducts(ProductID));
            		cart.get(i).count+=count;
            	}
            }
    	}
    }
package Server;

import java.io.*;
import java.net.*;
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
 
 
import java.io.*;

import java.net.*;

 

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.util.*;

 

 

import java.io.*;
import java.net.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Client1 {
    private static final int NONE = 0;
    private static final int LOGIN = 1;
    private static final int REGISTER = 2;
    private static final int EXIT=3;
    private static final int GETOPT = 4;
//    private static final int LOGIN_SEND = 5;
    private static final int BAD_LOGIN = 6;
    private static final int GOOD_LOGIN = 7;
    private static final int WAIT = 8;
    private static final int BAD_REGISTER = 9;
    private static final int GOOD_REGISTER = 10;
    private static final int ADDTOCART=11;
    private static final int WAIT1 = 12;
    private static Socket mmSocket;
    private static ObjectOutputStream outClient;
    private static ObjectInputStream inClient;
    private static CustomerInfo cust, ret_cust;
    private static String hostName;
    


    public static void main(String[] args) throws IOException {
        int portNumber;
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.err.println(
                "Using default hostname: loacalhost, port: 4444");
            hostName = "localhost";
            portNumber = 4444;
        } else {
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        }

        try {
            mmSocket = new Socket(hostName, portNumber);
            outClient = new ObjectOutputStream(mmSocket.getOutputStream());
            inClient = new ObjectInputStream(mmSocket.getInputStream());
            String fromServer;
            String fromUser;

            
            //CustomerInfo cust, ret_cust;
            int step = GETOPT;//getOptions();
            int exit = 0;
            cust = new CustomerInfo();
            ret_cust = new CustomerInfo();
            while (exit==0) { 
                switch(step) {
                    case GETOPT: 
//                                System.out.println("in GETOPT");
                              step = getOptions();
                              break;
                  case LOGIN: 
//                                System.out.println("in LOGIN");
                              step = loginOption(cust); 
                              break;
                  case REGISTER: 
//                                System.out.println("in REGISTER");
                              step = registerOption(cust); 
                              break;
                  case EXIT: 
//                                System.out.println("in EXIT");
                              cust.setCustomerCmd(EXIT);
                              outClient.writeObject(cust);
                              exit = 1;
                              break;
                  case NONE:
//                                System.out.println("in NONE");
                              step = GETOPT;
                              break;
                  case GOOD_LOGIN:
//                                System.out.println("in GOOD_LOGIN");
                             step= goodLogin();
                              break;
                  case GOOD_REGISTER:
//                                System.out.println("in GOOD_REGISTER");
                              step = goodRegister();
                              break;
                  case BAD_LOGIN:
//                                System.out.println("in BAD_LOGIN");
                              step = badLogin();
                              break;
                  case BAD_REGISTER:
//                                System.out.println("in BAD_REGISTER");
                              step = badRegister();
                              break;
                  case WAIT:
//                                System.out.println("client Waiting for server");
                              cust = (CustomerInfo)inClient.readObject();
//                                System.out.println("Client Server response detected");
                              step = cust.getCustomerCmd();
//                                System.out.println("Client Server response = " + step);
                                break;
                  case ADDTOCART: 
                  case WAIT1:
                	         System.out.println("waiting....");
                	         exit=1;
                	         break;


                	                  
                }
            }

            System.out.println("CLIENT GOODBYE");


            //outClient.close();
            //inClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception " + e);
            System.exit(1);
        }

    }
    public static int getOptions(){
        JPanel panel = new JPanel();
        Object[] opts = {"Login", "Register", "Exit"};
        JLabel lbl = new JLabel("Login/Register");
        int opt = JOptionPane.showOptionDialog(panel, "Do you want to Login or Register new account?", "Login/Register", 
                                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);

        if(opt == JOptionPane.NO_OPTION){
            System.out.println("REGISTERING...");
            return REGISTER;
        } 
        else if (opt == JOptionPane.YES_OPTION) {
            System.out.println("LOGGING...");
            return LOGIN;
        }
        else if (opt == JOptionPane.CANCEL_OPTION) {
            System.out.println("EXITING...");
            return EXIT;
        }
        else if (opt == JOptionPane.CLOSED_OPTION) {
            System.out.println("CLOSING CLIENT...");
            return EXIT;
        }
            System.out.println("NOTHING TO DO...");
            return LOGIN;
    }
    public static int loginOption(CustomerInfo info){
        // create and setup new window
        // JFrame frame = new JFrame("log");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        
        JTextField accEmail = new JTextField(20);
        JTextField pass = new JTextField(20);
        Object[] opts = {"Login", "Cancel"};
        JLabel lblAcc = new JLabel("Email: ");
        JLabel lblPass = new JLabel("Password: ");
        accEmail.setText("");
        pass.setText("");

        panel.add(lblAcc);
        panel.add(accEmail);
        //panel.add(Box.createVerticalStrut(15));
        panel.add(lblPass);
        panel.add(pass);
        panel.add(Box.createGlue());
        int opt = JOptionPane.showOptionDialog(null, panel, "Login",  
                                                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);

        if(opt == JOptionPane.NO_OPTION){
            info.setCustomerCmd(NONE);
            System.out.println("CANCELLING LOGGING...");
            return(NONE);
        } 
        else if (opt == JOptionPane.YES_OPTION ) {
            System.out.println("SENDING LOGGING...");
            info.setCustomerEmail(accEmail.getText());
            info.setCustomerPass(pass.getText());
            sendLogin();
            return(WAIT);
        }
        else if (opt == JOptionPane.CLOSED_OPTION) {
            System.out.println("CLOSING CLIENT LOGIN...");
            return EXIT;
        }
            System.out.println("NOTHING TO DO...");
            return(EXIT);
    }
    public static int registerOption(CustomerInfo info){
        // create and setup new window
        //JFrame frame = new JFrame("log");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.add(Box.createRigidArea(new Dimension(0,5)));
        
        JTextField name = new JTextField(20);
        JTextField email = new JTextField(20);
        JTextField addr = new JTextField(20);
        JTextField pass = new JTextField(20);
        Object[] opts = {"Register", "Cancel"};
        JLabel lblName = new JLabel("Name: ");
        JLabel lblEmail = new JLabel("Email: ");
        JLabel lblAddr = new JLabel("Address: ");
        JLabel lblPass = new JLabel("Password: ");
        name.setText("");
        email.setText("");
        addr.setText("");
        pass.setText("");

        panel.add(lblName);
        panel.add(name);
        //panel.add(Box.createVerticalStrut(15));
        panel.add(lblEmail);
        panel.add(email);
        panel.add(lblAddr);
        panel.add(addr);
        panel.add(lblPass);
        panel.add(pass);
        panel.add(Box.createGlue());
        int opt = JOptionPane.showOptionDialog(null, panel, "Register",  
                                                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);

        if(opt == JOptionPane.NO_OPTION){
            System.out.println("CANCELLING REGISTERATION...");
            return(NONE);
        } 
        else if (opt == JOptionPane.YES_OPTION ) {
            String name1 = name.getText();
            System.out.println("REGISTERING " + name1 + "...");
            try {
                cust.setCustomerPass(pass.getText());
                cust.setCustomerEmail(email.getText());
                cust.setCustomerAddress(addr.getText());
                cust.setCustomerName(name.getText());
                cust.setCustomerCmd(REGISTER);
                outClient.writeObject(cust);
            } catch (Exception e) {
                System.err.println("Exception " + e);
                System.exit(1);
            }
            return(WAIT);
        }
        else if (opt == JOptionPane.CLOSED_OPTION) {
            System.out.println("CLOSING CLIENT REGISTER...");
            return EXIT;
        }
            System.out.println("NOTHING TO DO...");
            return(EXIT);
    }
    public static int sendLogin() {
        try {

            //System.out.println("Current login info...");
            //print_account(cust);
            System.out.println("Sending login info...");
            cust.setCustomerCmd(LOGIN);
//            print_account(cust);
            outClient.writeObject(cust);
        } catch  (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception " + e);
            System.exit(1);
        }
        return EXIT;

    }
    public static void sendAddToCartRequest(CustomerInfo c)
    {
    	try {
			outClient.writeObject(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /*public static int goodLogin(){
        JPanel panel = new JPanel();
        Object[] opts = {"Logout"};
        JLabel lbl = new JLabel("Login Successful");
        int opt = JOptionPane.showOptionDialog(panel, "Welcome!! "+cust.getCustomerName(), "Login Successful", 
                                                JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);

        if(opt == JOptionPane.NO_OPTION){
            System.out.println("Logging out...");
        } 
        else if (opt == JOptionPane.CLOSED_OPTION) {
            System.out.println("CLOSING and logging out...");
        }
        return EXIT;
    }*/
    public static int goodLogin(){
    	test window = new test(cust.getCustomerNumber(),outClient,cust);
		window.frame.setVisible(true);
		return WAIT;
        
    }
    public static int goodRegister(){
        System.out.println("New ACC NUM = " + cust.getCustomerNumber());
        JPanel panel = new JPanel();
        Object[] opts = {"Logout"};
        JLabel lbl = new JLabel("Registration Successful");
        int opt = JOptionPane.showOptionDialog(panel, "Registration and login Successful", "Welcome!!", 
                                                JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);

        if(opt == JOptionPane.NO_OPTION){
            System.out.println("Logging out...");
        } 
        else if (opt == JOptionPane.CLOSED_OPTION) {
            System.out.println("CLOSING and logging out...");
        }
        return EXIT;
    }
    //public static int displayCart{
    	
    //}   
    
    
    public static int badLogin() {
        JPanel panel = new JPanel();
        Object[] opts = {"Retry", "Register", "Exit"};
        JLabel lbl = new JLabel("Bad Login");
        int opt = JOptionPane.showOptionDialog(panel, "Retry or register new account?", "Bad Login", 
                                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);

        if(opt == JOptionPane.NO_OPTION){
            System.out.println("REGISTERING...");
            return REGISTER;
        } 
        else if (opt == JOptionPane.YES_OPTION) {
            System.out.println("LOGGING...");
            return LOGIN;
        }
        else if (opt == JOptionPane.CANCEL_OPTION) {
            System.out.println("EXITING...");
            return EXIT;
        }
        else if (opt == JOptionPane.CLOSED_OPTION) {
            System.out.println("CLOSING CLIENT...");
            return EXIT;
        }
            System.out.println("NOTHING TO DO...");
            return LOGIN;
    }
    public static int badRegister() {
        JPanel panel = new JPanel();
        Object[] opts = {"Retry", "Login", "Exit"};
        JLabel lbl = new JLabel("Bad Register");
        int opt = JOptionPane.showOptionDialog(panel, "Retry or Login?", "Bad Register", 
                                                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);

        if(opt == JOptionPane.YES_OPTION){
            System.out.println("REGISTERING...");
            return REGISTER;
        } 
        else if (opt == JOptionPane.NO_OPTION) {
            System.out.println("LOGGING...");
            return LOGIN;
        }
        else if (opt == JOptionPane.CANCEL_OPTION) {
            System.out.println("EXITING...");
            return EXIT;
        }
        else if (opt == JOptionPane.CLOSED_OPTION) {
            System.out.println("CLOSING CLIENT...");
            return EXIT;
        }
            System.out.println("NOTHING TO DO...");
            return LOGIN;
    }
    public static void print_account(CustomerInfo info) {
        System.out.println("Printing Info ==========" );
            System.out.println("Number: " + info.getCustomerNumber());
            System.out.println("Name: " + info.getCustomerName());
            System.out.println("Email: " + info.getCustomerEmail());
            System.out.println("Address: " + info.getCustomerAddress());
            System.out.println("Pass: " + info.getCustomerPass());
            System.out.println("Cmd: " + info.getCustomerCmd());
            System.out.println("=================================");
    }

}
 class test {
	 
	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
    private int customerNumber;
    private ObjectOutputStream outClient;
    private CustomerInfo cust;
    private static final int ADDTOCART=11;
    private static final int WAIT = 8;
    private static final int WAIT1 =12;

	
	public test(int customerNumber,ObjectOutputStream outClient,CustomerInfo cust) {
		this.customerNumber = customerNumber;
		this.outClient = outClient;
		this.cust = cust;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("AddToCart");
		btnNewButton.setBounds(301, 28, 112, 23);
		btnNewButton.setName("Product1_button");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cust.setProductID(Integer.parseInt(textField.getText()));
				cust.setCount(Integer.parseInt(textField.getText()));
				cust.setCustomerCmd(ADDTOCART);
				Client1.sendAddToCartRequest(cust);
			}
		});
		
		textField = new JTextField();
		textField.setBounds(264, 29, 27, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setName("Product1_textField");
		
		JLabel lblNewLabel = new JLabel("product1");
		lblNewLabel.setBounds(58, 28, 196, 23);
		panel.add(lblNewLabel);
		lblNewLabel.setName("Product1_Label");
		
		JLabel lblProduct = new JLabel("product2");
		lblProduct.setBounds(58, 62, 196, 23);
		panel.add(lblProduct);
		lblProduct.setName("Product2_Label");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(264, 63, 27, 20);
		panel.add(textField_1);
		textField_1.setName("Product2_textField");
		
		JButton btnAddtocart = new JButton("AddToCart");
		btnAddtocart.setBounds(301, 62, 112, 23);
		panel.add(btnAddtocart);
		btnAddtocart.setName("Product2_button");
		btnAddtocart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cust.setProductID(Integer.parseInt(textField.getText()));
				cust.setCount(Integer.parseInt(textField.getText()));
				cust.setCustomerCmd(ADDTOCART);
				Client1.sendAddToCartRequest(cust);
			}
		});
		
		JLabel lblProduct_1 = new JLabel("product3");
		lblProduct_1.setBounds(58, 96, 196, 23);
		panel.add(lblProduct_1);
		lblProduct_1.setName("Product3_Label");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(264, 94, 27, 20);
		panel.add(textField_2);
		textField_2.setName("Product3_textField");
		
		JButton btnAddtocart_1 = new JButton("AddToCart");
		btnAddtocart_1.setBounds(301, 93, 112, 23);
		panel.add(btnAddtocart_1);
		btnAddtocart_1.setName("Product3_button");
		btnAddtocart_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cust.setProductID(Integer.parseInt(textField.getText()));
				cust.setCount(Integer.parseInt(textField.getText()));
				cust.setCustomerCmd(ADDTOCART);
				Client1.sendAddToCartRequest(cust);
			}
		});
		
		JLabel lblProduct_2 = new JLabel("product4");
		lblProduct_2.setBounds(58, 130, 196, 23);
		panel.add(lblProduct_2);
		lblProduct_2.setName("Product4_Label");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(264, 125, 27, 20);
		panel.add(textField_3);
		textField_3.setName("Product3_textField");
		
		JButton btnAddtocart_2 = new JButton("AddToCart");
		btnAddtocart_2.setBounds(301, 124, 112, 23);
		panel.add(btnAddtocart_2);
		btnAddtocart_2.setName("Product4_button");
		btnAddtocart_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cust.setProductID(Integer.parseInt(textField.getText()));
				cust.setCount(Integer.parseInt(textField.getText()));
				cust.setCustomerCmd(ADDTOCART);
				Client1.sendAddToCartRequest(cust);
			}
		});
		JLabel lblProduct_3 = new JLabel("product5");
		lblProduct_3.setBounds(58, 164, 196, 23);
		panel.add(lblProduct_3);
		lblProduct_3.setName("Product5_Label");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(264, 156, 27, 20);
		panel.add(textField_4);
		textField_4.setName("Product4_textField");
		
		JButton btnAddtocart_3 = new JButton("AddToCart");
		btnAddtocart_3.setBounds(301, 155, 112, 23);
		panel.add(btnAddtocart_3);
		btnAddtocart_3.setName("Product5_button");
		btnAddtocart_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cust.setProductID(5);
				cust.setCount(Integer.parseInt(textField_4.getText()));
				cust.setCustomerCmd(ADDTOCART);
				System.out.println(cust.getCount());
				try {
					outClient.writeObject(cust);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("ShowCart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowCarts window = new ShowCarts();
				window.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(179, 209, 112, 23);
		panel.add(btnNewButton_1);
	}
}
 class ShowCarts {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ShowCarts() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 424, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.setBounds(325, 28, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.setBounds(325, 62, 89, 23);
		panel.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(229, 29, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(229, 63, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(54, 32, 165, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(54, 66, 165, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(54, 106, 165, 14);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(229, 103, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Remove");
		btnNewButton_2.setBounds(325, 102, 89, 23);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(54, 142, 165, 14);
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(229, 139, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Remove");
		btnNewButton_3.setBounds(325, 136, 89, 23);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(54, 180, 165, 14);
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(229, 170, 86, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Remove");
		btnNewButton_4.setBounds(325, 170, 89, 23);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("buy");
		btnNewButton_5.setBounds(136, 205, 51, 23);
		panel.add(btnNewButton_5);
		
		JButton btnExitwithoutbuying = new JButton("Exit_Without_buying");
		btnExitwithoutbuying.setBounds(229, 205, 143, 23);
		panel.add(btnExitwithoutbuying);
	}
}


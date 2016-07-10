package Server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tests {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tests window = new tests();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tests() {
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(301, 28, 112, 23);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(264, 29, 27, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("product1");
		lblNewLabel.setBounds(58, 28, 196, 23);
		panel.add(lblNewLabel);
		
		JLabel lblProduct = new JLabel("product2");
		lblProduct.setBounds(58, 62, 196, 23);
		panel.add(lblProduct);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(264, 63, 27, 20);
		panel.add(textField_1);
		
		JButton btnAddtocart = new JButton("AddToCart");
		btnAddtocart.setBounds(301, 62, 112, 23);
		panel.add(btnAddtocart);
		
		JLabel lblProduct_1 = new JLabel("product3");
		lblProduct_1.setBounds(58, 96, 196, 23);
		panel.add(lblProduct_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(264, 94, 27, 20);
		panel.add(textField_2);
		
		JButton btnAddtocart_1 = new JButton("AddToCart");
		btnAddtocart_1.setBounds(301, 93, 112, 23);
		btnAddtocart_1.setName("product1");
		panel.add(btnAddtocart_1);
		
		JLabel lblProduct_2 = new JLabel("product4");
		lblProduct_2.setBounds(58, 130, 196, 23);
		panel.add(lblProduct_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(264, 125, 27, 20);
		panel.add(textField_3);
		
		JButton btnAddtocart_2 = new JButton("AddToCart");
		btnAddtocart_2.setBounds(301, 124, 112, 23);
		panel.add(btnAddtocart_2);
		
		JLabel lblProduct_3 = new JLabel("product5");
		lblProduct_3.setBounds(58, 164, 196, 23);
		panel.add(lblProduct_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(264, 156, 27, 20);
		panel.add(textField_4);
		
		JButton btnAddtocart_3 = new JButton("AddToCart");
		btnAddtocart_3.setBounds(301, 155, 112, 23);
		panel.add(btnAddtocart_3);
		
		JButton btnNewButton_1 = new JButton("showCartss");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCartss window = new showCartss();
				window.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(179, 209, 112, 23);
		panel.add(btnNewButton_1);
	}
}
 class showCartss {

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
	public showCartss() {
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


package Airportproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import oracle.jdbc.pool.OracleDataSource;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.util.regex.Pattern;

public class Add_supplier extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Add_supplier frame = new Add_supplier();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Add_supplier() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("ADD SUPPLIER");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(106, 11, 250, 14);
        contentPane.add(lblNewLabel);

        JLabel lblsupplierId = new JLabel("supplier ID : ");
        lblsupplierId.setBounds(10, 53, 120, 14);
        contentPane.add(lblsupplierId);

        textField = new JTextField();
        textField.setBounds(153, 50, 165, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblsupplierName = new JLabel("Supplier Name :");
        lblsupplierName.setBounds(10, 90, 120, 14);
        contentPane.add(lblsupplierName);

        textField_1 = new JTextField();
        textField_1.setBounds(153, 87, 165, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblCITY = new JLabel("Supplier city : ");
        lblCITY.setBounds(10, 129, 120, 14);
        contentPane.add(lblCITY);

        textField_2 = new JTextField();
        textField_2.setBounds(153, 126, 165, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblTelephoneNumber = new JLabel("Telephone Number : ");
        lblTelephoneNumber.setBounds(10, 166, 120, 14);
        contentPane.add(lblTelephoneNumber);

        textField_3 = new JTextField();
        textField_3.setBounds(153, 165, 165, 20);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblEmail = new JLabel("Email : ");
        lblEmail.setBounds(10, 195, 120, 14);
        contentPane.add(lblEmail);

        textField_4 = new JTextField();
        textField_4.setBounds(153, 204, 165, 20);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        JButton btnAdd = new JButton("ADD");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                supplier_add(textField.getText(), textField_1.getText(), textField_2.getText(), textField_3.getText(), textField_4.getText());
            }
        });
        btnAdd.setBounds(153, 233, 179, 23);
        contentPane.add(btnAdd);
    }

    public void supplier_add(String id, String name, String city, String telephone, String email) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("{ call procesureandfunction.add_supplier(?,?,?,?,?)}");
            cs.setString(1, id);
            cs.setString(2, name);
            cs.setString(3, city);
            cs.setString(4, telephone);
            cs.setString(5, email);
            cs.execute();
            JOptionPane.showMessageDialog(null, "Supplier Added!!!");
            cs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.print("Exception ---" + e.getStackTrace());
        }
    }
}

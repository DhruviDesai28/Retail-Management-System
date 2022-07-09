package Airportproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Add_Purchase extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox_1;
    private JComboBox<String> comboBox_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Add_Purchase frame = new Add_Purchase();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Add_Purchase() throws ClassNotFoundException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProductId = new JLabel("Product Id :");
        lblProductId.setBounds(52, 50, 76, 14);
        contentPane.add(lblProductId);

        JLabel lblEmployeeId = new JLabel("Employee Id :");
        lblEmployeeId.setBounds(52, 75, 82, 14);
        contentPane.add(lblEmployeeId);

        JLabel lblNewLabel = new JLabel("Customer Id :");
        lblNewLabel.setBounds(52, 100, 76, 14);
        contentPane.add(lblNewLabel);

        JLabel lblQuantity = new JLabel("Quantity :");
        lblQuantity.setBounds(52, 126, 92, 14);
        contentPane.add(lblQuantity);

        comboBox = new JComboBox<String>();
        comboBox.setBounds(154, 47, 93, 20);
        contentPane.add(comboBox);
        try {
            ArrayList<String> list = pids();
            for (int i = 0; i < list.size(); i++) {

                comboBox.addItem(list.get(i));
            }
        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        comboBox_1 = new JComboBox<String>();
        comboBox_1.setBounds(154, 72, 93, 20);
        contentPane.add(comboBox_1);
        try {
            ArrayList<String> list_eid = eids();
            for (int i = 0; i < list_eid.size(); i++) {

                comboBox_1.addItem(list_eid.get(i));
            }
        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        comboBox_2 = new JComboBox<String>();
        comboBox_2.setBounds(154, 99, 93, 20);
        contentPane.add(comboBox_2);
        try {
            ArrayList<String> list_cid = cids();
            for (int i = 0; i < list_cid.size(); i++) {

                comboBox_2.addItem(list_cid.get(i));
            }
        } catch (SQLException e1) {

            e1.printStackTrace();
        }

        textField = new JTextField();
        textField.setBounds(154, 123, 93, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnAdd = new JButton("ADD");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    purchase_add();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Add_Purchase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btnAdd.setBounds(184, 177, 89, 23);
        contentPane.add(btnAdd);
    }

    public ArrayList<String> eids() throws SQLException, ClassNotFoundException {
        ArrayList<String> ar = new ArrayList<String>();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
        java.sql.Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select eid from employees");
        while (rs.next()) {
            ar.add(rs.getString(1));

        }

        stmt.close();
        con.close();
        return ar;

    }

    public ArrayList<String> pids() throws SQLException, ClassNotFoundException {
        ArrayList<String> ar = new ArrayList<String>();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");

        java.sql.Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select pid from products");
        while (rs.next()) {
            ar.add(rs.getString(1));

        }

        System.out.println(ar);
        stmt.close();
        con.close();
        return ar;

    }

    public ArrayList<String> cids() throws SQLException, ClassNotFoundException {
        ArrayList<String> ar = new ArrayList<String>();

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");

        java.sql.Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select cid from customers");
        while (rs.next()) {
            ar.add(rs.getString(1));

        }

        stmt.close();
        con.close();
        return ar;

    }

    public void purchase_add() throws ClassNotFoundException {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");

            CallableStatement cs = con.prepareCall("begin ? := project2.add_purchase(?,?,?,?); end;");

            cs.registerOutParameter(1, OracleTypes.NUMBER);

            if (textField.getText().matches("^\\d+(\\.\\d+)?")) {
                cs.setString(2, comboBox_1.getSelectedItem().toString());
                System.out.println(comboBox_1.getSelectedItem().toString());
                cs.setString(3, comboBox.getSelectedItem().toString());
                cs.setString(4, comboBox_2.getSelectedItem().toString());
                cs.setInt(5, Integer.parseInt(textField.getText()));

                cs.execute();

                int result = cs.getInt(1);
                if (result == -123) {
                    JOptionPane.showMessageDialog(null, "Insufficient quantity in stock!!");
                } else {

                    JOptionPane.showMessageDialog(null, "Purchase Successfully Added!!");
                    JOptionPane.showMessageDialog(null, "Now Product Quantity of " + comboBox.getSelectedItem().toString() + "= " + result);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please Insert Integer as value for Quantity!!");

            }

            cs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something went Wrong!!");
            System.out.println(ex.getMessage());
        }
    }

}

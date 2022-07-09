package Airportproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import javax.swing.JComboBox;

public class Delete_employee extends JFrame {

    private JPanel contentPane;
    private JComboBox comboBox;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Delete_employee frame = new Delete_employee();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Delete_employee() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        comboBox = new JComboBox();
        try {
            ArrayList<String> list = eid();
            for (int i = 0; i < list.size(); i++) {

                comboBox.addItem(list.get(i));
            }
        } catch (SQLException e1) {

            e1.printStackTrace();
        } catch (Exception e1) {
            System.out.print("Exception ---" + e1.getStackTrace());
        }
        comboBox.setBounds(171, 36, 122, 20);

        contentPane.add(comboBox);
        JLabel lblPurchaseId = new JLabel("employee Id : ");
        lblPurchaseId.setBounds(82, 39, 91, 14);
        contentPane.add(lblPurchaseId);

        JButton btnNewButton = new JButton("Delete");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                delete_emp(comboBox.getSelectedItem().toString());
            }
        });
        btnNewButton.setBounds(133, 87, 143, 23);
        contentPane.add(btnNewButton);

    }

    public void delete_emp(String eid) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("{ call procesureandfunction.delete_employee(?)}");
            cs.setString(1, eid);
            cs.execute();
            JOptionPane.showMessageDialog(null, "employee Deleted : " + eid);
            cs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something went Wrong!!");
            System.out.println("\n*** SQLException caught ***\n" + ex.getMessage());
        } catch (Exception e) {
            System.out.print("Exception ---" + e.getStackTrace());
        }

    }

    public ArrayList<String> eid() throws SQLException, ClassNotFoundException {
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
}

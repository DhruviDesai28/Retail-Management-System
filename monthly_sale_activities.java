package Airportproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import javax.swing.JComboBox;

public class monthly_sale_activities extends JFrame {

    private JPanel contentPane;
    private JTextArea textArea;
    private JComboBox comboBox;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    monthly_sale_activities frame = new monthly_sale_activities();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public monthly_sale_activities() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEmployeeId = new JLabel("Employee ID : ");
        lblEmployeeId.setBounds(98, 24, 82, 14);
        contentPane.add(lblEmployeeId);
        comboBox = new JComboBox();
        try {
            ArrayList<String> list = eids();
            for (int i = 0; i < list.size(); i++) {

                comboBox.addItem(list.get(i));
            }
        } catch (SQLException e1) {
         
            e1.printStackTrace();
        } catch (Exception e1) {
            System.out.print("Exception ---" + e1.getStackTrace());
        }
        comboBox.setBounds(171, 21, 118, 20);
        contentPane.add(comboBox);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                monthly_sale(comboBox.getSelectedItem().toString());
            }
        });
        btnSubmit.setBounds(299, 20, 89, 23);
        contentPane.add(btnSubmit);

        JPanel panel = new JPanel();
        panel.setBounds(10, 49, 424, 212);
        contentPane.add(panel);
        panel.setLayout(new BorderLayout(0, 0));

        textArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);

    }

    public void monthly_sale(String eid) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.monthly_sale_activities(?); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, eid);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            String column_data = "MONTH" + "\t" + "TOTAL_SALE" + "\t" + "QUANTITY" + "\t" + "NUMBER_OF_SALE" + "\t" + "NAME\n\n";
            textArea.append(column_data);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + "\t" + rs.getString(5) + "\n");
            }
            cs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something went Wrong!!");
            System.out.println("\n*** SQLException caught ***\n" + ex.getMessage());
        } catch (Exception e) {
            System.out.print("Exception ---" + e.getStackTrace());
        }
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
}

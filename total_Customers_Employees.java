
package Airportproj;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
public class total_Customers_Employees extends JFrame{
    private JPanel contentPane;
    private JTextArea textArea;
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    total_Customers_Employees frame = new total_Customers_Employees();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public total_Customers_Employees() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnDisplay = new JMenu("Count Employee and Customer");
        menuBar.add(mnDisplay);
        
        JMenuItem mntmtotalCustomers = new JMenuItem("Total Customers");
        mntmtotalCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                total_Customers();
            }
        });
        mnDisplay.add(mntmtotalCustomers);
        
        JMenuItem mntmtotalEmployees = new JMenuItem("Total Employees");
        mntmtotalEmployees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                total_Employees();
            }
        });
        mnDisplay.add(mntmtotalEmployees);
        
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        textArea = new JTextArea();
        textArea.setColumns(10);
        contentPane.add(textArea, BorderLayout.CENTER);
    }
    public void total_Customers() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            System.out.print("connected");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.totalCustomers(); end;");  
             cs.registerOutParameter(1, OracleTypes.NUMBER);  
           cs.execute();
           int result = (int) cs.getFloat(1);
           textArea.append("Total Customers: "+result+ "\n");
            cs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something went Wrong!!");
            System.out.println("\n*** SQLException caught ***\n" + ex.getMessage());
        } catch (Exception e) {
            System.out.print("Exception ---" + e.getStackTrace());
        }
    }
    public void total_Employees() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            System.out.print("connected");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.totalEmployees(); end;");         
             cs.registerOutParameter(1, OracleTypes.NUMBER);  
           cs.execute();
           int result = (int) cs.getFloat(1);
           textArea.append("Total Employees: "+result+ "\n");
            cs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something went Wrong!!");
            System.out.println("\n*** SQLException caught ***\n" + ex.getMessage());
        } catch (Exception e) {
            System.out.print("Exception ---" + e.getStackTrace());
        }
    }
}

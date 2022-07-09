package Airportproj ;
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
public class GUI_Database extends JFrame {
    private JPanel contentPane;
    private JTextArea textArea;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI_Database frame = new GUI_Database();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public GUI_Database() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnDisplay = new JMenu("Display");
        menuBar.add(mnDisplay);
       
        JMenuItem mntmCustomers = new JMenuItem("Customers");
        mntmCustomers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                show_customer();
            }
        });
        mnDisplay.add(mntmCustomers);

        JMenuItem mntmEmployees = new JMenuItem("Products");
        mntmEmployees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                show_products();
            }
        });
        mnDisplay.add(mntmEmployees);

        JMenuItem mntmProducts = new JMenuItem("Employees");
        mntmProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                show_employees();
            }
        });
        mnDisplay.add(mntmProducts);

        JMenuItem mntmPurchases = new JMenuItem("Purchases");
        mntmPurchases.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                show_purchases();
            }
        });
        mnDisplay.add(mntmPurchases);
        
        JMenuItem mntmDiscounts = new JMenuItem("Discounts");
        mntmDiscounts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                show_discounts();
            }
        });
        mnDisplay.add(mntmDiscounts);

        JMenuItem mntmSuppliers = new JMenuItem("Suppliers");
        mntmSuppliers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                show_suppliers();
            }
        });
        mnDisplay.add(mntmSuppliers);

        JMenuItem mntmSupplies = new JMenuItem("Supplies");
        mntmSupplies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                show_supplies();
            }
        });
        mnDisplay.add(mntmSupplies);

        JMenuItem mntmLogs = new JMenuItem("Logs");
        mntmLogs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                show_logs();
            }
        });
        mnDisplay.add(mntmLogs);

        JMenu mnFunctionalities = new JMenu("Functionalities");
        menuBar.add(mnFunctionalities);

        JMenuItem mntmAddCustomer = new JMenuItem("Add Customer");
        mntmAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Add_customer().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmAddCustomer);

        JMenuItem mntmNewMenuItem = new JMenuItem("Purchase_saving");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new purchase_saving().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmNewMenuItem);

        JMenuItem mntmMonthlysaleactivities = new JMenuItem("Monthly_sale_Activities");
        mntmMonthlysaleactivities.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new monthly_sale_activities().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmMonthlysaleactivities);

        JMenuItem mntmDeletePurchase = new JMenuItem("Delete Purchase");
        mntmDeletePurchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Delete_Purchase().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmDeletePurchase);

        JMenuItem mntmDeleteCustomer = new JMenuItem("Delete Customer");
        mntmDeleteCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Delete_customer().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmDeleteCustomer);
        
        JMenuItem mntmtotalCustomerEmployee = new JMenuItem("total Customer Employee");
        mntmtotalCustomerEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new total_Customers_Employees().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmtotalCustomerEmployee);
        
        JMenuItem mntmAddpurchase = new JMenuItem("Add_Purchase");
        mntmAddpurchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Add_Purchase().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(GUI_Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        mnFunctionalities.add(mntmAddpurchase);
        
        JMenuItem mntmAddEmployee = new JMenuItem("Add Employee");
        mntmAddEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Add_employee().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmAddEmployee);
        
        JMenuItem mntmDeleteemployee = new JMenuItem("Delete employee");
        mntmDeleteemployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Delete_employee().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmDeleteemployee);
        
        JMenuItem mntmAddSupplier = new JMenuItem("Add Supplier");
        mntmAddSupplier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Add_supplier().setVisible(true);
            }
        });
        mnFunctionalities.add(mntmAddSupplier);
        
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        textArea = new JTextArea();
        textArea.setColumns(10);
        contentPane.add(textArea, BorderLayout.CENTER);
    }

    public void show_customer() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            System.out.print("connected");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.show_customers(); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            String column_name = "CID" + "\t" + "NAME" + "\t" + "TELEPHONE#" + "\t" + "VISITS_MADE" + "\t" + "LAST_VISIT_MADE\n\n";
            textArea.append(column_name);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getDate(5) + "\n");
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
      public void show_products() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.show_products(); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            String Column_name = "PID" + "\t" + "NAME" + "\t" + "qoh" + "\t"+"qoh_threshold" + "\t"+"original_price" +"\t"+ "discnt_category\n\n";
            textArea.append(Column_name);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) +"\t" +rs.getString(5) + "\t" +rs.getString(6) + "\n");
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

    public void show_employees() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.show_employees(); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            String Column_name = "EID" + "\t" + "NAME" + "\t" + "TELEPHONE#" + "\t" + "EMAIL\n\n";
            textArea.append(Column_name);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\n");
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

    public void show_purchases() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.show_purchases(); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            String column_name = "PUR#" + "\t" + "EID" + "\t" + "PID" + "\t" + "CID" + "\t" + "QTY" + "\t" + "PTIME" + "\t" + "TOTAL_PRICE\n\n";
            textArea.append(column_name);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getDate(6) + "\t" + rs.getString(7) + "\n"
                );
            }
            cs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something went Wrong!!");
            System.out.println("\n*** SQLException caught ***\n" + ex.getMessage());
        } catch (Exception ex) {
            System.out.print("Exception ---" + ex.getStackTrace());
        }
    }

    public void show_logs() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.show_logs(); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            String column_names = "LOG#" + "\t" + "USER_NAME" + "\t" + "OPERATION" + "\t" + "OP_TIME" + "\t" + "TABLE_NAME" + "\t" + "TUPLE_KEY\n\n";
            textArea.append(column_names);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getDate(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n"
                );
            }
            cs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Something went Wrong!!");
            System.out.println("\n*** SQLException caught ***\n" + ex.getMessage());
        } catch (Exception ex) {
            System.out.print("Exception ---" + ex.getStackTrace());
        }
    }
    public void show_discounts() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            System.out.print("connected");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.show_discounts(); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            String column_name = "discnt_category" + "\t" + "discnt_rate" +"\n\n";
            textArea.append(column_name);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\n");
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
    public void show_suppliers() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.show_suppliers(); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            ResultSetMetaData rsmd = rs.getMetaData();
            String col_names = rsmd.getColumnName(1) + "\t" + rsmd.getColumnName(2) + "\t" + rsmd.getColumnName(3) + "\t" + rsmd.getColumnName(4) + "\t" + rsmd.getColumnName(5) + "\n" + "\n";
            textArea.append(col_names);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\n"
                );
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

    public void show_supplies() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.show_supplies(); end;");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(1);
            ResultSetMetaData rsmd = rs.getMetaData();
            String col_names = rsmd.getColumnName(1) + "\t" + rsmd.getColumnName(2) + "\t" + rsmd.getColumnName(3) + "\t" + rsmd.getColumnName(4) + "\t" + "\t" + rsmd.getColumnName(5) + "\n" + "\n";
            textArea.append(col_names);
            while (rs.next()) {
                textArea.append(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getDate(4) + "\t" + "\t" + rs.getString(5) + "\n"
                );
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

}

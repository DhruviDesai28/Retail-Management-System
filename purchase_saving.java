package Airportproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
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

public class purchase_saving extends JFrame {

    private JPanel contentPane;
    private JTextField textField_1;
    private JComboBox comboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    purchase_saving frame = new purchase_saving();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public purchase_saving() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        comboBox = new JComboBox();
        try {
            ArrayList<String> list = pur_ids();
            for (int i = 0; i < list.size(); i++) {
                
                comboBox.addItem(list.get(i));
            }
        } catch (SQLException e1) {
           
            e1.printStackTrace();
        } catch (Exception e1) {
            System.out.print("Exception ---" + e1.getStackTrace());
        }
        comboBox.setBounds(176, 11, 74, 20);

        contentPane.add(comboBox);

        JLabel lblPurchaseId = new JLabel("Purchase ID :");
        lblPurchaseId.setBounds(62, 14, 102, 14);
        lblPurchaseId.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblPurchaseId);

        JButton btnNewButton = new JButton("calculate");
        btnNewButton.setBounds(260, 10, 120, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saving(comboBox.getSelectedItem().toString());
            }
        });
        contentPane.add(btnNewButton);

        JLabel lblTotalSaving = new JLabel("Total Saving :");
        lblTotalSaving.setBounds(72, 48, 102, 14);
        contentPane.add(lblTotalSaving);

        textField_1 = new JTextField();
        textField_1.setBounds(176, 45, 86, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

    }

    public void saving(String id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
            CallableStatement cs = con.prepareCall("begin ? := procesureandfunction.purchase_saving(?); end;");
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, id);
            cs.execute();
            float result = cs.getFloat(1);
            if (result == -1) {
                JOptionPane.showMessageDialog(null, "OOPS!! NO DATA FOUND!!");
            } else {
                textField_1.setText(result + "");
            }
            cs.close();
            con.close();
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, "OOps Can not find The Purchase ID..");
        } catch (Exception e1) {
            System.out.print("Exception ---" + e1.getStackTrace());
        }
    }
    public ArrayList<String> pur_ids() throws SQLException, ClassNotFoundException {
        ArrayList<String> ar = new ArrayList<String>();
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "system", "Nimisha123#");
        CallableStatement cs = con.prepareCall("{ call procesureandfunction.add_customer(?,?,?)}");
        java.sql.Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select pur# from purchases");
        while (rs.next()) {
            ar.add(rs.getString(1));
        }
        con.close();
        return ar;
    }
}

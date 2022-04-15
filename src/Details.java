import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Details extends JFrame implements ActionListener{
    JFrame frame, frame1;
    JTextField textbox;
    JLabel label;
    JButton button;
    JPanel panel;
    static JTable table;

    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/bms";
    String userName = "root";
    String password = "RadnaM";
    String[] columnNames = {"Flight No.", "Source", "Destination", "Class", "Charge", "Seat", "Time"};

    public void createUI()
    {
        frame = new JFrame("Database Search Result");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        textbox = new JTextField();
        textbox.setBounds(220,50,150,35);

        label = new JLabel("Flight No.: ");
        label.setFont(new Font("Osward", Font.BOLD, 25));
        label.setBounds(20, 50, 150, 35);

        button = new JButton("Search");
        button.setFont(new Font("Osward", Font.BOLD, 25));
        button.setBounds(220,150,150,35);
        button.addActionListener(this);

        frame.add(textbox);
        frame.add(label);
        frame.add(button);
        frame.setVisible(true);
        frame.setSize(500, 400);
    }

    public void actionPerformed(ActionEvent ae)
    {
        button = (JButton)ae.getSource();
        System.out.println("Showing Table Data.......");
        showTableData();
    }

    public void showTableData()
    {

        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        //TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
        //table = new JTable(model);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String textvalue = textbox.getText();
        String roll= "";
        String name= "";
        String cl = "";
        String charge = "";
        String sec = "";
        String classofflight = "";
        String timeofflight = "";
        try
        {
            Class.forName(driverName);
            Connection con = DriverManager.getConnection(url, userName, password);
            String sql = "select * from management where flightno = "+textvalue;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int i =0;
            if(rs.next())
            {
                roll = rs.getString("flightno");
                name = rs.getString("source");
                cl = rs.getString("dest");
                classofflight = rs.getString("classofflight");
                charge = rs.getString("charge");
                sec = rs.getString("seat");
                timeofflight = rs.getString("timeofflight");
                model.addRow(new Object[]{roll, name, cl, classofflight, charge, sec, timeofflight});
                i++;
            }
            if(i <1)
            {
                JOptionPane.showMessageDialog(null, "No Record Found","Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            if(i ==1)
            {
                System.out.println(i+" Record Found");
            }
            else
            {
                System.out.println(i+" Records Found");
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400,300);
    }

    public static void main(String args[])
    {
        Details sr = new Details();
        sr.createUI();
    }
}

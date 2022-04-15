import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.util.*;
import com.toedter.calendar.JDateChooser;
public class Management extends JFrame implements ActionListener{

    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b;

    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String first = "" + Math.abs(first4);

    Management(){
        setTitle("FASTJET: WELCOME");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/airplane.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(20, 0, 100, 100);
        add(l11);

        l1 = new JLabel("WELCOME TO FASTJET");
        l1.setFont(new Font("Raleway", Font.BOLD, 38));
        l1.setBounds(140,20,600,40);

        l2 = new JLabel("Flight Details");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));

        l3 = new JLabel("Flight No.:");
        l3.setFont(new Font("Raleway", Font.BOLD, 20));

        l4 = new JLabel("Source:");
        l4.setFont(new Font("Raleway", Font.BOLD, 20));

        l7 = new JLabel("Destination:");
        l7.setFont(new Font("Raleway", Font.BOLD, 20));

        l9 = new JLabel("Class:");
        l9.setFont(new Font("Raleway", Font.BOLD, 20));

        l10 = new JLabel("Charge:");
        l10.setFont(new Font("Raleway", Font.BOLD, 20));

        l11 = new JLabel("Seat:");
        l11.setFont(new Font("Raleway", Font.BOLD, 20));

        l12 = new JLabel("Time:");
        l12.setFont(new Font("Raleway", Font.BOLD, 20));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 14));

        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 14));

        t3 = new JTextField();
        t3.setFont(new Font("Raleway", Font.BOLD, 14));

        t4 = new JTextField();
        t4.setFont(new Font("Raleway", Font.BOLD, 14));

        t5 = new JTextField();
        t5.setFont(new Font("Raleway", Font.BOLD, 14));

        t6 = new JTextField();
        t6.setFont(new Font("Raleway", Font.BOLD, 14));

        t7 = new JTextField();
        t7.setFont(new Font("Raleway", Font.BOLD, 14));

        b = new JButton("Next");
        b.setFont(new Font("Raleway", Font.BOLD, 14));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);

        setLayout(null);

        add(l1);
        add(l2);
        add(l3);
        add(t1);
        add(l4);
        add(t2);
        add(l7);
        add(t3);
        add(l9);
        add(t4);
        add(l10);
        add(t5);
        add(l11);
        add(t6);
        add(l12);
        add(t7);
        add(b);

        l2.setBounds(290,80,600,30);
        l3.setBounds(100,140,100,30);
        t1.setBounds(300,140,400,30);
        l4.setBounds(100,190,200,30);
        t2.setBounds(300,190,400,30);
        l7.setBounds(100,240,200,30);
        t3.setBounds(300,240,400,30);
        l9.setBounds(100,290,200,30);
        t4.setBounds(300,290,400,30);
        l10.setBounds(100,340,200,30);
        t5.setBounds(300,340,400,30);
        l11.setBounds(100,390,200,30);
        t6.setBounds(300,390,400,30);
        l12.setBounds(100,440,200,30);
        t7.setBounds(300,440,400,30);
        b.setBounds(620,660,80,30);

        b.addActionListener(this);
        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setLocation(500,120);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        String formno = first;
        String flightno = t1.getText();
        String source = t2.getText();

        String dest = t3.getText();
        String classofflight = t4.getText();
        String charge = t5.getText();
        String seat = t6.getText();
        String timeofflight = t7.getText();

        try{
            if(t6.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Fill all the required fields");
            }else{

                Conn c1 = new Conn();
                PreparedStatement ps ;
                String str = "insert into management values(?,?,?,?,?,?,?)" ;
                ps = c1.c.prepareStatement(str);

                ps.setString(1,flightno);
                ps.setString(2,source);
                ps.setString(3,dest);
                ps.setString(4,classofflight);
                ps.setString(5,charge);
                ps.setString(6,seat);
                ps.setString(7,timeofflight);
                ps.executeUpdate();

                new Details().setVisible(true);
                setVisible(false);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Management().setVisible(true);
    }
}
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends JFrame {

    final private Font font = new Font("Open Sans", Font.BOLD, 18);
    final private Font font2 = new Font("Open Sans", Font.BOLD, 20);

    JTextField jtfName;
    JPasswordField pfPin;


public static String name;


    public void init() {

        Border border = BorderFactory.createLineBorder(new Color(0x778BEB), 3);

        // Labels
        JLabel lbTitle = new JLabel("Log into your account", SwingConstants.CENTER);
        lbTitle.setFont(font2);

        // TextFields

        jtfName = new JTextField("Name and surname");
        jtfName.setFont(font);
        jtfName.setBorder(border);
        jtfName.setBackground(new Color(0xD2DAE2));
        jtfName.setForeground(new Color(0x818E9B));


        pfPin = new JPasswordField("Password");
        pfPin.setFont(font);
        pfPin.setBorder(border);
        pfPin.setBackground(new Color(0xD2DAE2));
        pfPin.setForeground(new Color(0x818E9B));


        //Main panel

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1, 10, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
        lbTitle.setBorder(BorderFactory.createEmptyBorder(0,50,30,50));
        mainPanel.add(lbTitle);
        mainPanel.add(jtfName);
        mainPanel.add(pfPin);




        // Login Button and its actionListener
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0x778BEB));
        loginBtn.setForeground(Color.white);
        loginBtn.setFont(font);





        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name_surname = jtfName.getText();
                String pin = String.valueOf(pfPin.getPassword());

                Customer cust = getAuthCustomer(name_surname, pin);


                if(cust != null) {
                    ChoiceWindow chw = new ChoiceWindow();
                    chw.init(cust);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Name or Pin invalid!", "Try again", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 10, 5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10,50,100,50));
        buttonsPanel.add(loginBtn);


        add(mainPanel, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }



    public Customer getAuthCustomer(String name_surname, String Pin) {
        Customer cust = null;

        try {

            final String DB_URL = "jdbc:mysql://localhost:3306/loginform";
            final String USERNAME = "root";
            final String PASSWORD = "mypass123";


            Connection connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM loginform.bank_customers WHERE name_surname=? AND Pin=?";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name_surname);
            preparedStatement.setString(2, Pin);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cust = new Customer();

                cust.name_surname = resultSet.getString("name_surname");
                cust.Pin = resultSet.getString("Pin");
                cust.Pesel = resultSet.getString("Pesel");
                cust.Second_name = resultSet.getString("Second_name");
                cust.Email = resultSet.getString("Email");
                cust.Adress = resultSet.getString("Adress");
                cust.Acc_balance = resultSet.getInt("Acc_balance");
                cust.debts = resultSet.getInt("debts");
            }

            preparedStatement.close();
            connect.close();

        } catch(Exception e) {
            System.out.println("Connection to database failed!");
        }
        return cust;
    }
}

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ChoiceWindow extends JFrame {

    final private Font font = new Font("Open Sans", Font.BOLD, 14);
    final private Font font2 = new Font("Open Sans",Font.BOLD, 12);

    JTextField tfDep;
    JTextField tfWith;


        public void init(Customer cust) {

            Border border = BorderFactory.createLineBorder(new Color(0x778BEB), 3);


            JLabel jlTitle = new JLabel("Choose option", SwingConstants.CENTER);

            JButton depBtn = new JButton("Deposit $");
            JButton withdrawBtn = new JButton("Withdrawal $");
            JButton accBallBtn = new JButton("Account Balance");
            JButton debtBtn = new JButton("Debts");

            // Deposit
            tfDep = new JTextField("enter amount");
            tfDep.setBorder(border);
            tfDep.setBackground(new Color(0xD2DAE2));
            tfDep.setFont(font2);
            depBtn.setBounds(50, 100, 130, 25);
            depBtn.setBackground(new Color(0x778BEB));
            depBtn.setForeground(Color.white);
            depBtn.setFont(font);
            tfDep.setBounds(185, 100, 130, 25);




            // Withdraw
            tfWith = new JTextField("enter amount");
            tfWith.setBorder(border);
            tfWith.setBackground(new Color(0xD2DAE2));
            tfWith.setFont(font2);
            withdrawBtn.setBounds(50, 140, 130, 25);
            withdrawBtn.setBackground(new Color(0x778BEB));
            withdrawBtn.setForeground(Color.white);
            withdrawBtn.setFont(font);
            tfWith.setBounds(185, 140, 130, 25);

            // Account ballance
            accBallBtn.setBounds(117, 180, 170,30);
            accBallBtn.setBounds(117, 180, 170,30);
            accBallBtn.setBackground(new Color(0x778BEB));
            accBallBtn.setForeground(Color.white);
            accBallBtn.setFont(font);

            // Debts
            debtBtn.setBounds(117, 220, 130,30);
            debtBtn.setBackground(new Color(0x778BEB));
            debtBtn.setForeground(Color.white);
            debtBtn.setFont(font);



            this.setTitle("Choice window");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(380, 400);
            this.setLayout(null);
            this.setResizable(false);
            this.setVisible(true);

            this.add(depBtn);
            this.add(tfDep);
            this.add(tfWith);
            this.add(withdrawBtn);
            this.add(accBallBtn);
            this.add(debtBtn);


            accBallBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(ChoiceWindow.this, "your acc balance: " + cust.Acc_balance + "$", "Account balance", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            debtBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(ChoiceWindow.this, "you have " + cust.debts + " $ in debt.", "Debts", JOptionPane.INFORMATION_MESSAGE);

                }
            });


                depBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            cust.Acc_balance += Integer.parseInt(tfDep.getText());
                            JOptionPane.showMessageDialog(ChoiceWindow.this, "Successful operation", "Deposit", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(ChoiceWindow.this, "Enter value to deposit!", "Deposit", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });


            withdrawBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (cust.Acc_balance > 0) {
                            cust.Acc_balance -= Integer.parseInt(tfWith.getText());
                            JOptionPane.showMessageDialog(ChoiceWindow.this, "Successful operation", "Withdrawal", JOptionPane.INFORMATION_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(ChoiceWindow.this, "You have no money to withdraw!", "Deposit", JOptionPane.WARNING_MESSAGE);
                    } catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ChoiceWindow.this, "Enter value to withdraw!", "Deposit", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        }
    }


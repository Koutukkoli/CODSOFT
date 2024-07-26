import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    private JFrame f;
    private double bal;
    private JTextField amountField;

    public ATM() {
        bal = 0;
        f = new JFrame("ATM");

        f.getContentPane().setBackground(Color.BLACK);

        JLabel title = new JLabel("ATM", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(16.0f));
        title.setBounds(50, 20, 200, 30);
        title.setForeground(Color.WHITE);

        JLabel amountLabel = new JLabel("Enter amount:");
        amountLabel.setBounds(50, 60, 200, 30);
        amountLabel.setForeground(Color.WHITE);

        amountField = new JTextField();
        amountField.setBounds(50, 90, 200, 30);

        JButton depBtn = new JButton("Deposit");
        depBtn.setBounds(50, 130, 200, 30);
        depBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        JButton withBtn = new JButton("Withdraw");
        withBtn.setBounds(50, 170, 200, 30);
        withBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        JButton chkBalBtn = new JButton("Check Balance");
        chkBalBtn.setBounds(50, 210, 200, 30);
        chkBalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(50, 250, 200, 30);
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        f.add(title);
        f.add(amountLabel);
        f.add(amountField);
        f.add(depBtn);
        f.add(withBtn);
        f.add(chkBalBtn);
        f.add(exitBtn);

        f.setSize(300, 400);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private void deposit() {
        String amtStr = amountField.getText();
        if (amtStr != null && !amtStr.isEmpty()) {
            try {
                double amt = Double.parseDouble(amtStr);
                if (amt > 0) {
                    bal += amt;
                    amountField.setText("");
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(f, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void withdraw() {
        String amtStr = amountField.getText();
        if (amtStr != null && !amtStr.isEmpty()) {
            try {
                double amt = Double.parseDouble(amtStr);
                if (amt > 0 && amt <= bal) {
                    bal -= amt;
                    amountField.setText("");
                } else if (amt > bal) {
                    JOptionPane.showMessageDialog(f, "Insufficient balance", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(f, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(f, "Your current balance is: $" + bal);
    }

    public static void main(String[] args) {
        new ATM();
    }
}


package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


/**
 *
 * @author Jeff Bryan
 * CMIS 242
 * Project 2 ATM
 */
public class ATM extends JFrame {

    
    static final int WINDOWWIDTH = 350, WINDOWHEIGHT = 250,
                     TEXTWIDTH = 250, TEXTHEIGHT = 30;
                    

    
    private JButton withdraw = new JButton("Withdraw");
    private JButton deposit = new JButton("Deposit");
    private JButton transfer = new JButton("Transfer To");
    private JButton balance = new JButton("Balance");
    private JRadioButton checkingRadio = new JRadioButton("Checking");
    private JRadioButton savingsRadio = new JRadioButton("Savings");
    private JTextField entry = new JTextField("");
    private ButtonGroup radios = new ButtonGroup();
    private JOptionPane frame = new JOptionPane();

   
    private static Account checking = new Account().new Checking();
    private static Account savings = new Account().new Savings();

    
    private static DecimalFormat decForm = new DecimalFormat("$0.00");

  
    public static void makeAccounts(double checkingStartingBalance,
                                    double savingsStartingBalance) {

        checking.setBalance(checkingStartingBalance);
        savings.setBalance(savingsStartingBalance);
    }

    // Error handles invalid inputs
    
    public void errorValidNumber() {
        JOptionPane.showMessageDialog(frame, "Please enter a valid amount. " +
                "\n Withdrawals are processed in $20.00 increments.");
    }

    /**
     *  Listeners
     */

    
    class WithdrawButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
             
                if (getEntryValue() > 0 && getEntryValue() % 20 == 0) {
                    
                    if (checkingRadio.isSelected()) {
                        checking.withdraw(getEntryValue());
                        JOptionPane.showMessageDialog(frame, decForm.format(getEntryValue()) +
                                " withdrawn from Checking.");
                    } else if (savingsRadio.isSelected()) {
                        savings.withdraw(getEntryValue());
                        JOptionPane.showMessageDialog(frame, decForm.format(getEntryValue()) +
                                " withdrawn from Savings.");
                    }
                    clearEntryValue();
                } else errorValidNumber();
                clearEntryValue();
            } catch (InsufficientFunds insufficientFunds) {
                
            }
        }
    }

    
    class DepositButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (getEntryValue() > 0) {
                
                if (checkingRadio.isSelected()) {
                    checking.deposit(getEntryValue());
                    JOptionPane.showMessageDialog(frame, decForm.format(getEntryValue()) +
                            " deposited into Checking.");
                } else if (savingsRadio.isSelected()) {
                    savings.deposit(getEntryValue());
                    JOptionPane.showMessageDialog(frame, decForm.format(getEntryValue()) +
                            " deposited into Savings.");
                }
                clearEntryValue();
            } else errorValidNumber();
            clearEntryValue();
        }
    }

    
    class TransferToButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                if (getEntryValue() > 0) {
                    
                    if (checkingRadio.isSelected()) {
                        
                        savings.transferFrom(getEntryValue());
                        checking.transferTo(getEntryValue());
                        JOptionPane.showMessageDialog(frame, decForm.format(getEntryValue()) +
                                " transferred from Savings to Checking.");
                    } else if (savingsRadio.isSelected()) {
                        checking.transferFrom(getEntryValue());
                        savings.transferTo(getEntryValue());
                        JOptionPane.showMessageDialog(frame, decForm.format(getEntryValue()) +
                                " transferred from Checking to Savings.");
                    }
                    clearEntryValue();
                } else errorValidNumber();
                clearEntryValue();
            } catch (InsufficientFunds insufficientFunds) {
                
            }
        }
    }

    
    class BalanceButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (checkingRadio.isSelected()) {
                JOptionPane.showMessageDialog(frame,
                        "Your checking account balance is: \n" +
                                decForm.format(checking.getBalance()));
            } else if (savingsRadio.isSelected()) {
                JOptionPane.showMessageDialog(frame,
                        "Your savings account balance is: \n" +
                                decForm.format(savings.getBalance()));
            } else errorValidNumber();
            clearEntryValue();
        }
    }

    
    public ATM(double checkingStartingBalance, double savingsStartingBalance) {

        super("ATM Machine");
        setLayout(new GridBagLayout());
        GridBagConstraints layout = new GridBagConstraints();
        setFrame(WINDOWWIDTH, WINDOWHEIGHT);
        JPanel buttonPanel = new JPanel();
        JPanel textEntry = new JPanel();
        setResizable(false);
        layout.gridy = 2;
        add(buttonPanel);
        add(textEntry, layout);
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        textEntry.setLayout(new GridLayout(1, 1));
        buttonPanel.add(withdraw);
        buttonPanel.add(deposit);
        buttonPanel.add(transfer);
        buttonPanel.add(balance);
        radios.add(checkingRadio);
        radios.add(savingsRadio);
        buttonPanel.add(checkingRadio);
        buttonPanel.add(savingsRadio);
        entry.setPreferredSize(new Dimension(TEXTWIDTH, TEXTHEIGHT));
        checkingRadio.setSelected(true);
        textEntry.add(entry);

       
        makeAccounts(checkingStartingBalance, savingsStartingBalance);

        // Action listeners
        
        withdraw.addActionListener(new WithdrawButton());
        deposit.addActionListener(new DepositButton());
        transfer.addActionListener(new TransferToButton());
        balance.addActionListener(new BalanceButton());
    }

    /**
     * Methods
     */

    // gets the entered number
    
    public double getEntryValue() {
        try {
            return Double.parseDouble(entry.getText());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number\n");
            clearEntryValue();
            return 0;
        }
    }

    // clears the entered number
    public void clearEntryValue() {
        entry.setText("");
    }

    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display() {
        setVisible(true);
    }

    public static void main(String[] args) {
        
        // set initial account balances to $20.00
        ATM frame = new ATM(20, 20);
        frame.display();
    }

}
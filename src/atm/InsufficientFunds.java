
package atm;

import javax.swing.*;

/**
 *
 * @author jeff Bryan
 * CMIS 242
 * Project 2
 */

    
public class InsufficientFunds extends Exception {

    /**
     * Throw an exception when account balance is less than 0
     * 
     */
    public InsufficientFunds() {
        JOptionPane frame = new JOptionPane();
        JOptionPane.showMessageDialog(frame, "Insufficient Funds!");
    }
}

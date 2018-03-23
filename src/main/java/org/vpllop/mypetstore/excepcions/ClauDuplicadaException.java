/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vpllop.mypetstore.excepcions;

/**
 *
 * @author josep
 */
public class ClauDuplicadaException extends ComandesJpaException {

    /**
     * Creates a new instance of <code>ClauDuplicadaException</code> without detail message.
     */
    public ClauDuplicadaException() {
    }

    /**
     * Constructs an instance of <code>ClauDuplicadaException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ClauDuplicadaException(String msg) {
        super(msg);
    }

    public ClauDuplicadaException(String string, Exception ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

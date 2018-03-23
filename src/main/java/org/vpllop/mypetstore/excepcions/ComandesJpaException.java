/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vpllop.mypetstore.excepcions;

/**
 *
 * @author josep
 */
public class ComandesJpaException extends Exception {

    /**
     * Creates a new instance of <code>ComandesJpaException</code> without detail message.
     */
    public ComandesJpaException() {
    }

    /**
     * Constructs an instance of <code>ComandesJpaException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ComandesJpaException(String msg) {
        super(msg);
    }
    
    public ComandesJpaException(Throwable exception){
        super(exception);
    }

    public ComandesJpaException(String msg, Throwable causa){
        super(msg, causa);
    }
}

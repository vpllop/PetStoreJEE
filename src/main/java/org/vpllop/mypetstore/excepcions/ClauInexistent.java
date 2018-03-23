/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vpllop.mypetstore.excepcions;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author josep
 */
public class ClauInexistent extends ComandesJpaException {

    /**
     * Creates a new instance of <code>ClauInexistent</code> without detail message.
     */
    public ClauInexistent() {
    }

    /**
     * Constructs an instance of <code>ClauInexistent</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ClauInexistent(String msg) {
        super(msg);
    }

    public ClauInexistent(String string, EntityNotFoundException enfe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

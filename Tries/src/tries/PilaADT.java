/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

import java.util.Iterator;

/**
 *
 * @author edi
 */
public interface PilaADT <T> /*extends Iterable<T>*/ {
    public void push(T dato);
    public T pop();
    public T peak();
    public boolean isEmpty();
//    public Iterator<T> iterator();

    
}

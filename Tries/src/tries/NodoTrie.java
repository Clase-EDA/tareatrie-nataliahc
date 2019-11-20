/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

/**
 *
 * @author hca
 */
public class NodoTrie {
    public NodoTrie[]  hijos;
    private boolean finPalabra;
    private NodoTrie papa;

    public NodoTrie() {
        hijos=new NodoTrie[26];
        finPalabra=false;
        papa=null;
    }

    public boolean isFinPalabra() {
        return finPalabra;
    }

    public void setFinPalabra(boolean finPalabra) {
        this.finPalabra = finPalabra;
    }
    
    public NodoTrie getSig(char car, Character[] simbolos) { // se usa para insertar
        int i=0;
        while(i<simbolos.length && car!=simbolos[i]) {
            i++;
        }
        if(i==simbolos.length)
            throw new NullPointerException("no está ese símbolo"); 
        
        return hijos[i];
    }
    
    public void setSig(NodoTrie sig, Character car, Character[] simbolos) {
        int i=0;
        while(i<simbolos.length && car!=simbolos[i]) {
            i++;
        }
        if(i==simbolos.length)
            throw new NullPointerException("no está ese símbolo");
        
        hijos[i]=sig;
    }
    
    public void setSigNull(NodoTrie sig) {
        int i=0;
        while(i<26 && !hijos[i].equals(sig))
            i++;
        if(i<26)
            hijos[i]=null;
    }
    
    public boolean vacia() {
        boolean res=false;
        int i=0;
        while(i<26 && hijos[i]==null) {
            i++;
        }
        if(i>=26)
            res=true;
        return res;
    }

    public NodoTrie getPapa() {
        return papa;
    }

    public void setPapa(NodoTrie papa) {
        this.papa = papa;
    }
    
}


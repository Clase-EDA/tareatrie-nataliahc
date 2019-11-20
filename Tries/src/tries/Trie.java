/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hca
 */
public class Trie {

    private NodoTrie raiz;
    private Character[] simbolos;
    private ArrayList<String> ordenadas;

    public Trie(Character[] simb) {
        raiz = null;
        Arrays.sort(simb);
        simbolos = simb.clone();
        ordenadas = new ArrayList();
    }

    private boolean busca(String llave) {
        return busca(llave, raiz);
    }

    private boolean busca(String llave, NodoTrie actual) {
        if (actual == null) {
            return false;
        }
        if (llave.equals("")) {
            return actual.isFinPalabra();
        }

        return busca(llave.substring(1), actual.getSig(llave.charAt(0), simbolos));
    }

    private PilaA<NodoTrie> busca2(String llave) {
        return busca2(llave, raiz, new PilaA<NodoTrie>());
    }

    private PilaA<NodoTrie> busca2(String llave, NodoTrie actual, PilaA<NodoTrie> pila) {
        if (actual == null) {
            return null;
        }

        pila.push(actual);
        if (llave.equals("")) {
            if (actual.isFinPalabra()) {
                return pila;
            } else {
                return null;
            }
        }
        return busca2(llave.substring(1), actual.getSig(llave.charAt(0), simbolos), pila);
    }

    public void inserta(String llave) {
        if (!llave.equals("")) {
            if (raiz == null) {
                raiz = new NodoTrie();
            }
            inserta(llave, raiz);
        }
    }

    private void inserta(String llave, NodoTrie actual) {
        if (llave.equals("")) {
            actual.setFinPalabra(true);
        } else {
            NodoTrie sig = actual.getSig(llave.charAt(0), simbolos);
            if (sig == null) {
                sig = new NodoTrie();
                actual.setSig(sig, llave.charAt(0), simbolos);
                sig.setPapa(actual); 
            }
            inserta(llave.substring(1), sig);
        }
    }

    public boolean eliminar(String llave) {
        boolean res = false, ban;
        NodoTrie act = buscaFin(llave);
        ban = act != null;
        if (ban && llave != null && !llave.equals("")) {
            act.setFinPalabra(false);
            ban = act.vacia();
            res = !ban;
            if (ban) {
                res = eliminar(llave, act.getPapa());
            }
        }
        return res;
    }

    private boolean eliminar(String llave, NodoTrie actual) {
        if (actual == null) {
            return false;
        }
        int pos = buscaCar(llave.charAt(llave.length() - 1));
        actual.hijos[pos] = null;
        if (actual.isFinPalabra()) {
            return true;
        }
        if (!actual.vacia()) {
            return true;
        }
        if (actual.getPapa() == null) {
            return true;
        }
        return eliminar(llave.substring(0, llave.length() - 1), actual.getPapa());
    }

    public NodoTrie buscaFin(String llave) {
        return buscaFin(llave, raiz);
    }

    private NodoTrie buscaFin(String llave, NodoTrie actual) {
        if (actual == null) {
            return null;
        }
        if (llave.compareTo("") == 0) {
            return actual;
        }

        return buscaFin(llave.substring(1), actual.getSig(llave.charAt(0), simbolos));
    }

    private int buscaCar(Character c) {
        int i = 0;
        while (i < simbolos.length && simbolos[i].compareTo(c) != 0) {
            i++;
        }
        if (i == simbolos.length) {
            return -1;
        }
        return i;
    }

    public void ordLex(String[] arre) {
        if (arre != null) {
            int n = arre.length;
            for (int i = 0; i < n; i++) {
                inserta(arre[i]);
                ordenadas.clear();
                ordLex(raiz, "");
            }
        }
    }
    
    public void insertaArre(String[] arre) {
        raiz=null;
        if (arre != null) {
            int n = arre.length;
            for (int i = 0; i < n; i++) {
                inserta(arre[i]);
            }
        }
    }

    public void ordLex() {
        ordenadas.clear();
        ordLex(raiz, "");
    }

    private void ordLex(NodoTrie actual, String palabra) {
        if (actual.isFinPalabra()) {
            ordenadas.add(palabra);
        }

        for (int i = 0; i < 26; i++) {
            if (actual.hijos[i] != null) {
                ordLex(actual.hijos[i], palabra + simbolos[i]);
            }
        }
    }

    public String toString() {
        ordLex();

        StringBuilder res = new StringBuilder();
        int i = 0;
        int n = ordenadas.size();
        while (i < n) {
            res.append(ordenadas.get(i)).append(" \n");
            i++;
        }
        return res.toString();

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author hca
 */
public class Tries {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Character[] simbolos = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Trie trie = new Trie(simbolos);

        trie.inserta("ama");
        trie.inserta("bobo");
        trie.inserta("bob");
        trie.inserta("drake");
        trie.inserta("draker");
        trie.inserta("agua");
        System.out.println(trie.toString());

        trie.eliminar("drake");
        trie.eliminar("agua");
        System.out.println(trie.toString());

        Scanner sc = null;

        try {
            File file = new File("E:\\palabras.txt");
            sc = new Scanner(new FileReader(file));

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            System.exit(1);
        }

        String[] arre;
        int i;
        long TInicio, tiempoTrie, tiempoMerge;
        
        for (int j = 10000; j <= 30000; j=j+10000) {
            arre = new String[j];
            i = 0;
            while (i < j) {
                String text = sc.nextLine().toLowerCase();
                if ((text != null) && (!text.equals("")) && (text.matches("^[a-zA-Z]*$"))) {
                    arre[i] = text;
                    i++;
                }
            }

            TInicio = System.currentTimeMillis();
            trie.insertaArre(arre);
            trie.ordLex();
            tiempoTrie = System.currentTimeMillis() - TInicio;
            System.out.println("El algoritmo de Trie tardó " + tiempoTrie+" milisegundos con "+i+" entradas.");

            TInicio = System.currentTimeMillis();
            MergeSort.mergeSort(arre);
            tiempoMerge = System.currentTimeMillis() - TInicio;
            System.out.println("El algoritmo de Merge tardó " + tiempoMerge +" milisegundos con "+i+" entradas."+ "\n");
        }


    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tries;

/**
 *
 * @author Natalia
 */
public class MergeSort {

    public static <T extends Comparable<T>> T[] mergeSort(T[] unsorted) {
        T[] tmp = (T[]) new Comparable[unsorted.length];
        doSort(unsorted, tmp, 0, unsorted.length - 1);
        return unsorted;
    }

    private static <T extends Comparable<T>> void doSort(T[] arr, T[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            doSort(arr, temp, left, mid);
            doSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }

    }

    private static <T extends Comparable<T>> void merge(T[] arr, T[] temp, int left, int mid, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            // quickCont++;
            if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = temp[i++];
        }

        while (j <= right) {
            arr[k++] = temp[j++];
        }
    }

}

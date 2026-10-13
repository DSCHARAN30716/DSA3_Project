package algorithms;

import java.util.Random;

public class RandomizedQuickSort {

    private static final Random random =
            new Random();

    public static void sort(double[] array) {

        if (array == null ||
                array.length <= 1) {

            return;
        }

        quickSort(
                array,
                0,
                array.length - 1
        );
    }

    private static void quickSort(
            double[] array,
            int low,
            int high) {

        if (low >= high) {
            return;
        }

        int pivotIndex =
                low + random.nextInt(
                        high - low + 1
                );

        swap(
                array,
                pivotIndex,
                high
        );

        int partitionIndex =
                partition(
                        array,
                        low,
                        high
                );

        quickSort(
                array,
                low,
                partitionIndex - 1
        );

        quickSort(
                array,
                partitionIndex + 1,
                high
        );
    }

    private static int partition(
            double[] array,
            int low,
            int high) {

        double pivot =
                array[high];

        int i = low - 1;

        for (int j = low;
             j < high;
             j++) {

            if (array[j] <= pivot) {

                i++;

                swap(
                        array,
                        i,
                        j
                );
            }
        }

        swap(
                array,
                i + 1,
                high
        );

        return i + 1;
    }

    private static void swap(
            double[] array,
            int i,
            int j) {

        double temp =
                array[i];

        array[i] =
                array[j];

        array[j] =
                temp;
    }
}
import java.util.Arrays;
import java.util.Stack;

public class Main3 {
    public static void main(String[] args) {
        int[] arr1 = { 20,19,18,17,16,15,14,13,12,11,10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr3 = {6, 7, 8, 9, 10, 1, 2, 3, 4, 5};

        System.out.println("_____________________________________________");
        System.out.println("VVV IN REVERSED VVV");
        System.out.println("_____________________________________________");
        bubbleSorter(arr1);
        sortShell(arr1);

        System.out.println("_____________________________________________");
        System.out.println("VVV IN SORTER VVV");
        System.out.println("_____________________________________________");
        bubbleSorter(arr2);
        sortShell(arr2);

        System.out.println("_____________________________________________");
        System.out.println("VVV IN RANDOM VVV");
        System.out.println("_____________________________________________");
        bubbleSorter(arr3);
        sortShell(arr3);

    }

    public static void bubbleSorter(int[] arr) {
        int temp;
        int swapCount = 0;
        int comparisonCount = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1 -i; j++) {
                comparisonCount++;
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapCount++;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("BUBBLE Comparison count: " + comparisonCount);
        System.out.println("BUBBLE Swap count: " + swapCount);
    }

    public static int[] sortShell(int[] arr) {
        int n = arr.length;
        int step = 1;
        int swapCount = 0;
        int comparisonCount = 0;
        Stack<Integer> steps = new Stack<>();
        while (step < arr.length){
            steps.add(step);
            step = step * 3 + 1;
        }
        while (!steps.empty()) {
            step = steps.pop();
            for (int i = step; i < n; i++) {
                int key = arr[i];
                int j = i;
                comparisonCount++;
                while (j >= step && arr[j - step] > key) {
                    swapCount++;
                    arr[j] = arr[j - step];
                    j -= step;
                }
                swapCount++;
                arr[j] = key;
            }
        }
        System.out.println("SHELL Comparison count: " + comparisonCount);
        System.out.println("SHELL Swap count: " + swapCount);
        return arr;
    }
}

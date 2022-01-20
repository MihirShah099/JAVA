package com.example.demoStaticCRUD;

public class DemoNew {

    public static void main(String[] args) {
        /*int a = 3;
        int b = 4;
        int c = 1;
        int d = 2;
        int f = 5;

        int sum = a+b+c+d+f;
        System.out.println(sum);*/

        int arr[] = { 1, 2, 3, 4 ,5};
        int n = arr.length;
        System.out.println(pairORSum(arr, n));

    }

    static int pairORSum(int arr[], int n)
    {
        int ans = 0; // Initialize result

        // Consider all pairs (arr[i], arr[j)
        // such that i < j
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                ans += arr[i] | arr[j];

        return ans;
    }
}

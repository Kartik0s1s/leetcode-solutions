
import java.util.Arrays;

class Solution {

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {

        Arrays.sort(arr2);

        int count = 0;

        for (int a : arr1) {

            int low = a - d;
            int high = a + d;

            int index = lowerBound(arr2, low);

            if (index == arr2.length || arr2[index] > high) {
                count++;
            }
        }

        return count;
    }

    private int lowerBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}

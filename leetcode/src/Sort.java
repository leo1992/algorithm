/**
 * Created by zhangying on 5/18/18.
 */
public class Sort {

    public void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void insertSort(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
        }
    }

    public void selectSort(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    public void bubbleSort(int[] nums) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > i; j--) {
                if (nums[j] < nums[j - 1])
                    swap(nums, j, j - 1);
            }
        }
    }

    public void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int middle = start + (end - start) / 2;
        mergeSort(nums, start, middle);
        mergeSort(nums, middle + 1, end);
        merge(nums, start, middle, end);
    }

    public void merge(int[] nums, int start, int middle, int end) {
        int[] mergeArray = new int[end - start + 1];
        int i = start;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= end) mergeArray[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        while (i <= middle) mergeArray[k++] = nums[i++];
        while (j <= end) mergeArray[k++] = nums[j++];
        for (int m = 0; m < k; m++) nums[start + m] = mergeArray[m];
    }

    public void heapSort(int[] nums) {
        int size = nums.length;
//        int[] heap = new int[size];
//        for (int i = 0; i < size; i++)
//            insertHeap(heap, i, nums[i]);
//        for (int i = 0; i < size; i++) nums[i] = popHeap(heap, size - i);
        for (int i = (size - 2) / 2; i>=0; i--) adjustHeap(nums, i, size);
        for (int i = size - 1; i >= 0; i--) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            adjustHeap(nums, 0, i);
        }
    }

    private void insertHeap(int[] heap, int size, int value) {
        heap[size] = value;
        int index = size;
        while (index > 0 && heap[index] < heap[(index - 1) / 2]) {
            swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void adjustHeap(int[] heap, int parent, int length) {
        int leftChild = parent * 2 + 1;
        if (leftChild >= length) return;
        int adjustPoint = parent;
        if (heap[leftChild] > heap[adjustPoint]) adjustPoint = leftChild;
        if (leftChild + 1 < length && heap[leftChild + 1] > heap[adjustPoint]) adjustPoint = leftChild + 1;
        if (adjustPoint != parent) {
            swap(heap, parent, adjustPoint);
            adjustHeap(heap, adjustPoint, length);
        }
    }

    private int popHeap(int[] heap, int size) {
        int temp = heap[0];
        heap[0] = heap[size - 1];
        int index = 0;
        while (index < size - 1) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int maxIndex = index;
            if (left < size - 1 && heap[left] < heap[maxIndex])
                maxIndex = left;
            if (right < size - 1 && heap[right] < heap[maxIndex])
                maxIndex = right;
            if (maxIndex == index) break;
            swap(heap, index, maxIndex);
            index = maxIndex;
        }
        return temp;
    }

    public void quickSortTwo(int[] nums, int start, int end) {
        int base = nums[start];
        int leftIndex = start + 1;
        int rightIndex = end;
        while(leftIndex < rightIndex) {
            while (leftIndex < rightIndex && nums[leftIndex] <= base) leftIndex++;
            while (rightIndex > leftIndex && nums[rightIndex] >= base) rightIndex--;
            swap(nums, leftIndex, rightIndex);
        }
        if (nums[rightIndex] < base) swap(nums, start, rightIndex);
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start >= end || start < 0 || end >= nums.length) return;
        int base = nums[start];
        int leftIndex = start + 1;
        int rightIndex = end;
        while (leftIndex < rightIndex) {
            while (nums[leftIndex] <= base && leftIndex < rightIndex) leftIndex++;
            while (nums[rightIndex] >= base && rightIndex > leftIndex) rightIndex--;
            swap(nums, leftIndex, rightIndex);
        }
        if (nums[rightIndex] < base) {
            swap(nums, start, rightIndex);
        }
        quickSort(nums, start, rightIndex - 1);
        quickSort(nums, leftIndex, end);

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

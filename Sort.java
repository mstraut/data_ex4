package a4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;


public class Sort {

	/**
	 * Build a random array
	 * 
	 * @param rand   a Random object
	 * @param LENGTH The range of the integers in the array will be from 0 to
	 *               LENGTH-1
	 * @return
	 */
	private static int[] build_random_array(Random rand, int LENGTH) {
		int[] array = new int[LENGTH];
		// set index 0 to 0 for consistency with CLRS, where sorting starts at index 1
		array[0] = 0;
		for (int i = 1; i < LENGTH; i++) {
			// Generate random integers in range 0 to 999
			int rand_int = rand.nextInt(LENGTH);
			array[i] = rand_int;
		}
		return array;
	}

	/**
	 * Assert the array is sorted correctly
	 * 
	 * @param array_unsorted The original unsorted array
	 * @param array_sorted   The sorted array
	 */
	public static void assert_array_sorted(int[] array_unsorted, int[] array_sorted) {
		int a_sum = array_unsorted[0];
		int b_sum = array_sorted[0];
		for (int i = 1; i < array_unsorted.length; i++) {
			a_sum += array_unsorted[i];
		}
		for (int i = 1; i < array_sorted.length; i++) {
			b_sum += array_sorted[i];
			assertTrue(array_sorted[i - 1] <= array_sorted[i]);
		}
		assertEquals(a_sum, b_sum);
	}

	public static void insertionSort(int[] array) {
		int arrayNum = array.length;
		// for loop iterates i position through array
		for (int i = 1; i < arrayNum; ++i) {
			int arrayKey = array[i];
			int j = i - 1;
			/*
			 * while loops while elements in array are greater than arrayKey value moving
			 * them ahead of the arrayKey position
			 */
			while (j >= 0 && array[j] > arrayKey) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			// final arrayKey position
			array[j + 1] = arrayKey;
		}

	}

	public static void selectionSort(int[] array) {
		int r = array.length;

		// two for loops for positions in array
		for (int i = 0; i < r - 1; i++) {
			int minNum = i;
			for (int j = i + 1; j < r; j++) {
				/*
				 * if to compare the two elements iterated by i and j to find the smaller value
				 */
				if (array[j] < array[minNum]) {
					minNum = j;
				}
			}
			/*
			 * switches the lower positioned element with the smaller value
			 */
			int temp = array[minNum];
			array[minNum] = array[i];
			array[i] = temp;

		}
	}

	// Return the index of the parent of the node currently at pos
	public static int parent(int pos) {
		return (pos / 2);
	}

	// Return the index of the leftchild of the node currently at pos
	public static int leftChild(int pos) {
		return (2 * pos);
	}

	// Return the index of the rightchild of the node currently at pos
	public static int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	// Function to heapify the node at position i, to the position n
	public static void maxHeapify(int[] array, int i, int n) {
		int posIindex = i;
		int rightChildIndex = rightChild(i);
		int leftChildIndex = leftChild(i);
		/*
		 * checks if the left child's index is <= position n and the left child's value
		 * is > than the node at position posIindex
		 */
		if (leftChildIndex <= n && array[leftChildIndex] > array[posIindex]) {
			posIindex = leftChildIndex;
		}
		/*
		 * checks if the right child's index is <= position n and the right child's
		 * value is > than the node at position posIindex
		 */
		if (rightChildIndex <= n && array[rightChildIndex] > array[posIindex]) {
			posIindex = rightChildIndex;
		}
		// if posIindex is now different than i, values are swapped & maxHeapify
		if (posIindex != i) {
			swap(array, i, posIindex);
			maxHeapify(array, posIindex, n);
		}
	}

	// takes the array created and organizes it into a max heap
	public static void buildMaxHeap(int[] array) {
		/**
		 * TODO implement these as shown in class.
		 */

		int heapNum = array.length - 1;
		int parentNum = parent(heapNum);
		for (int i = parentNum; i >= 0; i--) {
			maxHeapify(array, i, heapNum);
		}

	}

	// sorts the heap, swaps nodes
	public static void heapSort(int[] array) {
		/**
		 * TODO implement these as shown in class.
		 */
		int[] heap = array;
		int heapNum = heap.length - 1;
		buildMaxHeap(array);

		for (int i = heap.length - 1; i > 0; i--) {
			swap(array, 0, i);
			heapNum--;
			maxHeapify(array, 0, heapNum);
		}

		System.out.println(heap[1] + " + " + heap[2] + " = " + (heap[2] + heap[1]));
	}

	// Swap two nodes of the heap at index first second
	public static void swap(int[] array, int first, int seconds) {
		int tmp;
		tmp = array[first];
		array[first] = array[seconds];
		array[seconds] = tmp;
	}

	public static void mergeSort(int array[], int p, int r) {
		if (p < r) {
			// q is the midpoint
			int q = (p + r) / 2;

			// sends first half of array for sorting
			mergeSort(array, p, q);
			// sends second half of array for sorting
			mergeSort(array, q + 1, r);

			// merge the halves after sorting
			arrMerge(array, p, q, r);
		}
	}

	public static void arrMerge(int array[], int p, int q, int r) {
		// size of first and second halves of array
		int arrNum1 = q - p + 1;
		int arrNum2 = r - q;

		// temp arrays
		int temp1[] = new int[arrNum1];
		int temp2[] = new int[arrNum2];

		// array elements split into the temp arrays
		for (int i = 0; i < arrNum1; ++i)
			temp1[i] = array[p + i];
		for (int j = 0; j < arrNum2; ++j)
			temp2[j] = array[q + 1 + j];

		int i = 0;
		int j = 0;
		int k = p;
		// merges the temporary arrays
		while (i < arrNum1 && j < arrNum2) {
			if (temp1[i] <= temp2[j]) {
				array[k] = temp1[i];
				i++;
			} else {
				array[k] = temp2[j];
				j++;
			}
			k++;
		}

		// moves remaining elements in temp1
		while (i < arrNum1) {
			array[k] = temp1[i];
			i++;
			k++;
		}

		// moves remaining elements in temp2
		while (j < arrNum2) {
			array[k] = temp2[j];
			j++;
			k++;
		}
	}

	public static void quickSort(int[] array) {
		int r = array.length;
		int arrL = array.length - 1;
		// for loop to iterate through array
		for (int i = 0; i < arrL - 1; i++) {
			int minNum = i;
			// for loop to iterate for search
			for (int j = i + 1; j < r; j++)
				// if to compare while searching for lower value
				if (array[j] < array[minNum]) {
					minNum = j;
				}
			// switch values
			int tempNum = array[minNum];
			array[minNum] = array[i];
			array[i] = tempNum;
		}
	}

	public static void bucketSort(int[] array) {
		int bucketCount = array.length / 2;
		int minIntValue = 0;
		int maxIntValue = array.length - 1;
		// Create bucket array
		List<Integer>[] buckets = new List[bucketCount];
		// Associate a list with each index in the bucket array
		for (int i = 0; i < bucketCount; i++) {
			buckets[i] = new LinkedList<>();
		}

		
		// for loops through array
		for (int i = 0; i < array.length; i++) {
			// indexArr, finds the bucket the array value needs to get sorted into
			int indexArr = array[i] / bucketCount;
			// adds value to its bucket
			buckets[indexArr].add(array[i]);

		}

		// sort buckets
		for (List<Integer> bucket : buckets) {
			Collections.sort(bucket);
		}
		int i = 0;
		// Merge buckets to get sorted array
		for (List<Integer> bucket : buckets) {
			for (int num : bucket) {
				array[i++] = num;
			}
		}
	}

	public static void main(String[] args) {
		int NUM_RUNS = 3;
		// create instance of Random class
		Random rand = new Random();

		/////////////////////////////////////////
		int LENGTH = 100;
		System.out.println("_____________INPUT " + LENGTH + "_____________");
		int[] array_100 = build_random_array(rand, LENGTH);

		// For runtime computations
		long startTime, endTime;

		double duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;

		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100_c, 0, array_100_c.length - 1);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		/////////////////////////////////////////
		LENGTH = 1000;
		System.out.println("_____________INPUT " + LENGTH + "_____________");
		int[] array_1000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_1000_c, 0, array_1000_c.length - 1);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		/////////////////////////////////////////
		LENGTH = 10000;
		System.out.println("_____________INPUT " + LENGTH + "_____________");
		int[] array_10000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;

		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_10000_c, 0, array_10000_c.length - 1);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		/////////////////////////////////////////
		LENGTH = 100000;
		System.out.println("_____________INPUT " + LENGTH + "_____________");
		int[] array_100000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100000_c, 0, array_100000_c.length - 1);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0; t < NUM_RUNS; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

	}

}

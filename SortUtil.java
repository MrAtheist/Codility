

public class SortUtil {

	public static void insertionSort(int[] iList) {
		int len = iList.length;
		for (int i = 1; i < len; i++) {
			int cur = iList[i];
			int j = i;
			while (j > 0 && cur < iList[j-1]) {
				iList[j] = iList[j-1];
				j--;
			}
			iList[j] = cur;
		}
	}

	public static void selectionSort(int[] iList) {
		int len = iList.length;
		for (int i = 0; i < len-1; i++) {
			int minIndex = i;
			for (int j = i+1; j < len; j++) {
				if (iList[j] < iList[minIndex]) {
					minIndex = j;
				}
			}
			int temp = iList[i];
			iList[i] = iList[minIndex];
			iList[minIndex] = temp;
		}
	}

	public static void bubbleSort(int[] iList) {
		int len = iList.length;
		for (int i = len-1; i >= 1; i--) {
			boolean isSorted = true;
			for (int j = 0; j <= i-1; j++) {
				if (iList[j] > iList[j+1]) {
					int temp = iList[j];
					iList[j] = iList[j+1];
					iList[j+1] = temp;
					isSorted = false;
				}
			}
			if (isSorted) {
				return;
			}
		}
	}

	public static void mergeSort(int[] iList) {
		int len = iList.length;
		int[] curList = iList;
		int[] cpList = new int[len];
		for (int i = 1; i < len; i *= 2) {
			for (int j = 0; j < len; j += 2*i) {
				int bound1 = Math.min(j+i-1, len-1);
				int bound2 = Math.min(j+2*i-1, len-1);
				int position1 = j;
				int position2 = j+i;
				int position3 = j;
				while (position1 <= bound1 || position2 <= bound2) {
					if (position1 > bound1) {
						while (position2 <= bound2) {
							cpList[position3++] = curList[position2++];
						}
						break;
					} else if (position2 > bound2) {
						while (position1 <= bound1) {
							cpList[position3++] = curList[position1++];
						}
						break;
					} else {
						if (curList[position1] < curList[position2]) {
							cpList[position3++] = curList[position1++];
						} else {
							cpList[position3++] = curList[position2++];
						}
					}
				}
			}
			int[] temp = curList;
			curList = cpList;
			cpList = temp;
		}
		iList = curList;
	}

	public static void quickSort(int[] iList) {
		quickSortHelper(iList, 0, iList.length);
	}

	private static void quickSortHelper(int[] iList, int start, int len) {
		if (len <= 1) {
			return;
		}

		int more = start+len-1;
		int less = start;
		while (more > less) {
			if (iList[more] <= iList[more-1]) {
				int temp = iList[more-1];
				iList[more-1] = iList[more];
				iList[more] = temp;
				more--;
			} else {
				int temp = iList[less];
				iList[less] = iList[more-1];
				iList[more-1] = temp;
				less++;
			}
		}
		quickSortHelper(iList, start, more-start);
		quickSortHelper(iList, more+1, start+len-more-1);
	}

	public static void heapSort(int[] iList) {
		
		int len = iList.length;
		
		for (int i = 1; i < len; i++) {
			int j = i;
			while (j >= 1 && iList[j] > iList[(j-1)/2]) {
				int temp = iList[j];
				iList[j] = iList[(j-1)/2];
				iList[(j-1)/2] = temp;
				j = (j-1)/2;
			}
		}
		
		for (int i = len-1; i >= 1;i--) {
			int temp = iList[0];
			iList[0] = iList[i];
			iList[i] = temp;
			int j = 0;
			while (j*2+1 <= i-1) {
				int maxChild = 0;
				if (j*2+2 <= i-1) {
					maxChild = iList[j*2+1] > iList[j*2+2]?j*2+1:j*2+2;
				} else {
					maxChild = j*2+1;
				} 
				if (iList[maxChild] > iList[j]) {
					int temp1 = iList[j];
					iList[j] = iList[maxChild];
					iList[maxChild] = temp1;
					j = maxChild;
				} else {
					break;
				}
			}
		}
	}
}

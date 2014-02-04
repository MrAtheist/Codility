import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;


public class Codility {
	public static int solutionTapeEquilibrium(int[] A) {
		int len = A.length;
		long sum = 0;
		for (int i = 0; i < len; i++) {
			sum += A[i];
		}
		long min = -1;
		int left = 0;
		for (int i = 0; i < len - 1; i++) {
			left += A[i];
			long diff = Math.abs(left - (sum - left));
			if (min >= 0) {
				min = Math.min(min, diff);
			} else {
				min = diff;
			}
		}
		return (int) min;
	}

	public static int solutionFrogJmp(int X, int Y, int D) { 
		return (Y - X) / D + (((Y - X) % D == 0)?0:1);
	}

	public static int solutionPermMissingElem(int[] A) {
		int sum = 0;
		for (int i : A) {
			sum = sum ^ i;
		}
		for (int i = 1; i <= A.length + 1; i++) {
			sum = sum ^ i;
		}
		return sum;
	}

	public static int solutionPermCheck(int[] A) {
		int[] B = new int[A.length];
		for (int i : A) {
			if (i < 1 || i > A.length) {
				return 0;
			}
			else if (B[i-1] == 0) {
				B[i-1]++;
			} else {
				return 0;
			}
		}
		return 1;
	}

	public static int solutionFrogRiverOne(int X, int[] A) {
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 0; i < A.length; i++) {
			hs.add(A[i]);
			if (hs.size() == X) {
				return i;
			}
		}
		return -1;
	}

	public static int[] solutionMaxCounters(int N, int[] A) {
		int[] B = new int[N];
		int maxCounter = 0;
		int max = 0;
		for (int i : A) {
			if (i == N + 1) {
				maxCounter = max;
			} else {
				B[i-1] = (B[i-1] > maxCounter)?B[i-1]+1:maxCounter+1;
				max = Math.max(max, B[i-1]);
			}
		}
		for (int i = 0; i < N; i++) {
			B[i] = Math.max(B[i], maxCounter);
		}
		return B;
	}

	public static int solutionPassingCars(int[] A) {
		int result = 0;
		int zeros = 0;
		for (int i : A) {
			if (i == 0) {
				zeros++;
			}
		}
		int ones = A.length - zeros;
		int currentOnes = 0;
		for (int i : A) {
			if (i == 1) {
				currentOnes++;
			} else {
				result += (ones - currentOnes);
				if (result > 1000000000) {
					return -1;
				}
			}
		}
		return result;
	}

	public static int[] solutionGenomicRangeQuery(String S, int[] P, int[] Q) {
		int len = S.length();
		int[] aSum = new int[len+1];
		int[] cSum = new int[len+1];
		int[] gSum = new int[len+1];
		int[] tSum = new int[len+1];
		for (int i = 1; i < len+1; i++) {
			if (S.charAt(i-1) == 'A') {
				aSum[i] = aSum[i-1] + 1;
				cSum[i] = cSum[i-1];
				gSum[i] = gSum[i-1];
				tSum[i] = tSum[i-1];
			} else if (S.charAt(i-1) == 'C') {
				cSum[i] = cSum[i-1] + 1;
				aSum[i] = aSum[i-1];
				gSum[i] = gSum[i-1];
				tSum[i] = tSum[i-1];
			} else if (S.charAt(i-1) == 'G') {
				gSum[i] = gSum[i-1] + 1;
				aSum[i] = aSum[i-1];
				cSum[i] = cSum[i-1];
				tSum[i] = tSum[i-1];
			} else if (S.charAt(i-1) == 'T') {
				tSum[i] = tSum[i-1] + 1;
				aSum[i] = aSum[i-1];
				cSum[i] = cSum[i-1];
				gSum[i] = gSum[i-1];
			}
		}
		int[] result = new int[P.length];
		for (int i = 0; i < P.length; i++) {
			int start = P[i];
			int end = Q[i];
			if (aSum[end+1] - aSum[start] > 0) {
				result[i] = 1;
			} else if (cSum[end+1] - cSum[start] > 0) {
				result[i] = 2;
			} else if (gSum[end+1] - gSum[start] > 0) {
				result[i] = 3;
			} else if (tSum[end+1] - tSum[start] > 0) {
				result[i] = 4;
			}
		}
		return result;
	}

	public static int solutionMaxProductOfThree(int[] A) {
		Arrays.sort(A);
		return Math.max(A[0] * A[1] * A[A.length-1], A[A.length-1] * A[A.length-2] * A[A.length-3]);
	}

	public static int solutionTriangle(int[] A) {
		Arrays.sort(A);
		for (int i = 0; i <= A.length-3; i++) {
			long a = A[i];
			long b = A[i+1];
			long c = A[i+2];
			if (a + b > c) {
				return 1;
			}
		}
		return 0;
	}

	public static int solutionBeta2010(int A[]) {
		int len = A.length;
		long[] ins = new long[len];
		long[] outs = new long[len];
		for (int i = 0; i < len; i++) {
			ins[i] = (long) i - (long) A[i];
			outs[i] = (long) i + (long) A[i];
		}
		Arrays.sort(ins);
		Arrays.sort(outs);
		int result = 0;
		int insIndex = 0;
		int outsIndex = 0;
		int currentIns = 0;
		while (insIndex < len || outsIndex < len) {
			long pos = 0;
			if (insIndex >= len) {
				pos = outs[outsIndex];
			} else if (outsIndex >= len) {
				pos = ins[insIndex];
			} else {
				pos = Math.min(ins[insIndex], outs[outsIndex]);
			}
			while (insIndex < len && ins[insIndex] == pos) {
				result += currentIns;
				if (result > 10000000) {
					return -1;
				}
				currentIns++;
				insIndex++;
			}
			while (outsIndex < len && outs[outsIndex] == pos) {
				currentIns--;
				outsIndex++;
			}
		}
		return result;
	}

	public static int solutionBrackets(String S) {
		Stack<Character> cStack = new Stack<Character>();
		for (char c : S.toCharArray()) {
			if (c == '(' || c == '[' || c == '{') {
				cStack.push(c);
			} else {
				if (cStack.empty()) {
					return 0;
				} else if (c == ')') {
					if (cStack.pop() != '(') {
						return 0;
					}
				} else if (c == ']') {
					if (cStack.pop() != '[') {
						return 0;
					}
				} else if (c == '}') {
					if (cStack.pop() != '{') {
						return 0;
					}
				}
			}
		}
		return cStack.empty()?1:0;
	}

	public static int solutionNesting(String S) {
		int indicator = 0;
		for (char c : S.toCharArray()) {
			if (c == '(') {
				indicator++;
			} else if (c == ')') {
				indicator--;
			}
			if (indicator < 0) {
				return 0;
			}
		}

		return indicator == 0?1:0;
	}

	public static int solutionSigma2012(int[] H) {
		Stack<Integer> iStack = new Stack<Integer>();
		int result = 0;
		for (int i : H) {
			while (!iStack.empty() && iStack.peek() > i) {
				iStack.pop();
			}
			if (iStack.empty() || iStack.peek() != i) {
				result++;
				iStack.push(i);
			}
		}
		return result;
	}

	public static int solutionFish(int[] A, int[] B) {
		int result = 0;
		Stack<Integer> iStack = new Stack<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (B[i] == 0) {
				while (true) {
					if (iStack.empty()) {
						result++;
						break;
					} else if (iStack.peek() < A[i]) {
						iStack.pop();
					} else {
						break;
					}
				}
			} else {
				iStack.push(A[i]);
			}
		}
		return result + iStack.size();
	}

	public static int solutionDominator(int[] A) {
		int count = 0;
		int value = 0;
		for (int i : A) {
			if (count == 0) {
				value = i;
				count++;
			} else if (i == value) {
				count++;
			} else {
				count--;
			}
		}
		if (count == 0) {
			return -1;
		} else {
			int total = 0;
			int position = -1;
			for (int i = 0; i < A.length; i++) {
				if (A[i] == value) {
					total++;
					position = i;
				}
			}
			if (total * 2 > A.length) {
				return position;
			} else {
				return -1;
			}
		}
	}

	public static int solutionEquiLeader(int[] A) {
		int count = 0;
		int value = 0;
		for (int i : A) {
			if (count == 0) {
				value = i;
				count++;
			} else if (i == value) {
				count++;
			} else {
				count--;
			}
		}
		if (count == 0) {
			return 0;
		} 
		int total = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == value) {
				total++;
			}
		}
		if (total * 2 <= A.length) {
			return 0;
		}
		int leftCount = 0;
		int result = 0;
		for (int i = 0; i < A.length-1; i++) {
			if (A[i] == value) {
				leftCount++;
			}
			if ((2 * leftCount > i + 1) && (2 * (total - leftCount) > A.length - i - 1)) {
				result++;
			}
		}
		return result;
	}

	public static int solutionMaxDoubleSliceSum(int[] A) {
		int prevMaxSliceEnding = 0;
		int curMaxSliceEnding = Math.max(0, A[1]);
		int maxDoubleSliceEnding = 0;
		int maxDoubleSlice = 0;
		for (int i = 3; i < A.length; i++) {
			prevMaxSliceEnding = curMaxSliceEnding;
			curMaxSliceEnding = Math.max(0, prevMaxSliceEnding + A[i-1]);
			maxDoubleSliceEnding = Math.max(prevMaxSliceEnding, maxDoubleSliceEnding + A[i-1]);
			maxDoubleSlice = Math.max(maxDoubleSliceEnding, maxDoubleSlice);
		}
		return maxDoubleSlice;
	}

	public static int solutionMaxProfit(int[] A) {
		int result = 0;
		int maxEnding = 0;
		for (int i = 1; i < A.length; i++) {
			maxEnding = Math.max(0, maxEnding + A[i] - A[i-1]);
			result = Math.max(result, maxEnding);
		}
		return result;
	}

	public static int solutionMaxSliceSum(int[] A) {
		int maxSlice = A[0];
		int maxEnding = A[0];
		for (int i = 1; i < A.length; i++) {
			maxEnding = Math.max(A[i], maxEnding + A[i]);
			maxSlice = Math.max(maxSlice, maxEnding);
		}
		return maxSlice;
	}

	public static int solutionMinPerimeterRectangle(int N) {
		int i = (int) Math.floor(Math.sqrt(N));
		while (N % i != 0) {
			i--;
		}
		return 2 * (N / i + i);
	}

	public static int solutionPeaks(int[] A) {
		boolean[] B = new boolean[A.length];
		int peakSize = 0;
		for (int i = 1; i < A.length-1; i++) {
			if (A[i] > A[i-1] && A[i] > A[i+1]) {
				B[i] = true;
				peakSize++;
			}
		}
		int result = 0;
		for (int i = 1; i <= peakSize; i++) {
			if (A.length % i == 0) {
				boolean allHasPeak = true;
				for (int j = 0; j < i; j++) {
					boolean hasPeak = false;
					for (int k = 0; k < A.length / i; k++) {
						if (B[j * (A.length / i) + k]) {
							hasPeak = true;
							break;
						}
					}
					if (!hasPeak) {
						allHasPeak = false;
						break;
					}
				}
				if (allHasPeak) {
					result = i;
				}
			}
		}
		return result;
	}

	public static int solutionBoron2013(int[] A) {
		boolean[] B = new boolean[A.length];
		int peakSize = 0;
		for (int i = 1; i < A.length-1; i++) {
			if (A[i] > A[i-1] && A[i] > A[i+1]) {
				B[i] = true;
				peakSize++;
			}
		}
		int[] next = new int[A.length];
		next[A.length - 1] = -1;
		for (int i = A.length - 2; i >= 0; i--) {
			if (B[i]) {
				next[i] = i;
			} else {
				next[i] = next[i+1];
			}
		}
		int j = 1;
		int result = 0;
		while (j * j <= A.length && j <= peakSize) {
			int num = 0;
			int pos = 0;
			while (pos < A.length && num < j) {
				pos = next[pos];
				if (pos == -1) {
					break;
				}
				num++;
				pos += j;
			}
			result = Math.max(result, num);
			j++;
		}
		return result;
	}

	public static int[] solutionCountNonDivisible(int[] A) {
		int[] div = new int[2 * A.length + 1];
		int[] count = new int[2 * A.length + 1];
		int[] visit = new int[2 * A.length + 1];
		for (int i : A) {
			count[i]++;
		}
		for (int i = 0; i < A.length; i++) {
			if (visit[A[i]] == 0) {
				visit[A[i]]++;
				int j = 1;
				while (j * j <= A[i]) {
					if (A[i] % j == 0 && j * j != A[i]) {
						div[A[i]] += count[j] + count[A[i] / j];
					} else if (A[i] % j == 0) {
						div[A[i]] += count[j];
					}
					j++;
				}
			}
		}
		for (int i = 0; i < A.length; i++) {
			A[i] = A.length - div[A[i]];
		}
		return A;
	}

	public static int[] solutionCountSemiprimes(int N, int[] P, int[] Q) {
		int[] minPrimes = new int[N + 1];
		for (int i = 4; i < N + 1; i++) {
			int j = 2;
			while (j * j <= i) {
				if (i % j == 0) {
					minPrimes[i] = j;
					break;
				}
				j++;
			}
		}
		int[] semiPrimeCounts = new int[N + 1];
		for (int i = 2; i < N + 1; i++) {
			semiPrimeCounts[i] = semiPrimeCounts[i-1];
			if (minPrimes[i] != 0 && minPrimes[i / minPrimes[i]] == 0) {
				semiPrimeCounts[i]++;
			}
		}
		int[] result = new int[P.length];
		for (int i = 0; i < P.length; i++) {
			result[i] = semiPrimeCounts[Q[i]] - semiPrimeCounts[P[i]-1];
		}
		return result;
	}

}


import java.util.*;
import java.io.*;

class Time implements Comparable<Time> {
	int start;
	int end;

	Time(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Time t) {
		return Integer.compare(this.start, t.start);
	}
}

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int n;
	static Map<Integer, Integer> sumMap;
	static Time[] times;

	static void solution() {
		int maxNum = 0, curNum = 0;
		List<Integer> keySetList = new ArrayList<>(sumMap.keySet());
		Collections.sort(keySetList);
		for (int key : keySetList) {
			curNum += sumMap.get(key);
			maxNum = Math.max(maxNum, curNum);
		}

		int[] nums = new int[maxNum];
		Queue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < maxNum; i++) {
			pq.offer(i);
		}
		Map<Integer, Integer> timeMap = new HashMap<>();
		for(int i = 0; i < n; i++) {
			timeMap.put(times[i].start, i + 1);
			timeMap.put(times[i].end, (-1) * (i + 1));
		}
		List<Integer> timeList = new ArrayList<>(timeMap.keySet());
		Collections.sort(timeList);
		Map<Integer, Integer> idxMap = new HashMap<>();
		for (int time : timeList) {
			if (timeMap.get(time) > 0) {
				int idx = pq.poll();
				idxMap.put(times[timeMap.get(time) - 1].end, idx);
				nums[idx]++;
			} else {
				pq.offer(idxMap.get(time));
			}
		}

		output.append(maxNum).append("\n");
		for(int num: nums) {
			output.append(num).append(" ");
		}
	}

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(input.readLine());
		times = new Time[n];
		sumMap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(input.readLine());
			int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
			if (sumMap.containsKey(start)) {
				sumMap.put(start, sumMap.get(start) + 1);
			} else {
				sumMap.put(start, 1);
			}
			if (sumMap.containsKey(end)) {
				sumMap.put(end, sumMap.get(end) - 1);
			} else {
				sumMap.put(end, -1);
			}
			times[i] = new Time(start, end);
		}

		solution();

		System.out.println(output);
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static final int[] digitSticks = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };

	static int n;

	static String toString(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int num : list) {
			sb.append(num);
		}
		return sb.toString();
	}

	static void init(List<List<Integer>> minDp, List<List<Integer>> maxDp) {
		for (int i = 0; i <= n; i++) {
			minDp.add(new ArrayList<>());
			maxDp.add(new ArrayList<>());
			if (i == 2) {
				minDp.get(i).add(1);
				maxDp.get(i).add(1);
			} else if (i == 3) {
				minDp.get(i).add(7);
				maxDp.get(i).add(7);
			} else if (i == 4) {
				minDp.get(i).add(4);
				maxDp.get(i).add(4);
			} else if (i == 5) {
				minDp.get(i).add(2);
				maxDp.get(i).add(5);
			} else if (i == 6) {
				minDp.get(i).add(6);
				maxDp.get(i).add(9);
			} else if (i == 7) {
				minDp.get(i).add(8);
				maxDp.get(i).add(8);
			}
		}
	}

	static void solution() {
		List<List<Integer>> minDp = new ArrayList<>(n + 1);
		List<List<Integer>> maxDp = new ArrayList<>(n + 1);
		init(minDp, maxDp);

		for (int stick = 1; stick <= n; stick++) {
			for (int digit = 0; digit < 10; digit++) {
				int digitStick = digitSticks[digit];
				int prevStick = stick - digitStick;
				if (prevStick <= 0) {
					continue;
				}
				if (minDp.get(prevStick).size() > 0) {
					// minDp 계산
					if (minDp.get(stick).size() == 0 || minDp.get(stick).size() >= minDp.get(prevStick).size() + 1) {
						boolean check = false;
						List<Integer> newList = new ArrayList<>();
						for (int num : minDp.get(prevStick)) {
							if (!(digit == 0 && newList.size() == 0)) {
								if (!check && num > digit) {
									check = true;
									newList.add(digit);
								}
							}
							newList.add(num);
						}
						if (!check) { // 남은거 털기
							newList.add(digit);
						}
						if (minDp.get(stick).size() == minDp.get(prevStick).size() + 1) { // 비교해야 하는 경우
							for (int i = 0; i < minDp.get(stick).size(); i++) {
								if (newList.get(i) < minDp.get(stick).get(i)) {
									minDp.set(stick, newList);
									break;
								} else if(newList.get(i) > minDp.get(stick).get(i)) {
									break;
								}
							}
						} else { // 비교할 것도 없이 걍 넣는 경우
							minDp.set(stick, newList);
						}
					}
				}
				if (maxDp.get(prevStick).size() > 0) {
					// maxDp 계산
					if (maxDp.get(stick).size() < maxDp.get(prevStick).size() + 1) {
						boolean check = false;
						List<Integer> newList = new ArrayList<>();
						for (int num : maxDp.get(prevStick)) {
							if (!check && num < digit) {
								check = true;
								newList.add(digit);
							}
							newList.add(num);
						}
						if (!check) { // 남은거 털기
							newList.add(digit);
						}
						if (maxDp.get(stick).size() == maxDp.get(prevStick).size() + 1) { // 비교해야 하는 경우
							for (int i = 0; i < maxDp.get(stick).size(); i++) {
								if (newList.get(i) > maxDp.get(stick).get(i)) {
									maxDp.set(stick, newList);
									break;
								} else if(newList.get(i) < minDp.get(stick).get(i)) {
									break;
								}
							}
						} else { // 비교할 것도 없이 걍 넣는 경우
							maxDp.set(stick, newList);
						}
					}
				}
			}
		}

		output.append(toString(minDp.get(n))).append(" ").append(toString(maxDp.get(n))).append("\n");
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(input.readLine());
			solution();
		}

		System.out.println(output);
	}
}

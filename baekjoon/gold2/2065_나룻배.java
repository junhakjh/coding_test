import java.util.*;
import java.io.*;

class Person implements Comparable<Person> {
	int time, idx;

	Person(int time, int idx) {
		this.time = time;
		this.idx = idx;
	}

	public int compareTo(Person p) {
		return Integer.compare(this.time, p.time);
	}
}

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer st;

	static int M, t, N;
	static Queue<Person> lq, rq;

	static void solution() {
		int time = 0, lr = 0;
		int[] times = new int[N];
		while (!lq.isEmpty() || !rq.isEmpty()) {
			Person lp = lq.peek(), rp = rq.peek();
			if (lr == 0) {
				if (lp == null || lp.time > time) {
					if (lp == null || (rp != null && lp.time > rp.time)) {
						lr = 1;
						time = Math.max(rp.time + t, time + t);
					} else {
						time = lp.time;
					}
				}
			} else {
				if (rp == null || rp.time > time) {
					if (rp == null || (lp != null && rp.time > lp.time)) {
						lr = 0;
						time = Math.max(lp.time + t, time + t);
					} else {
						time = rp.time;
					}
				}
			}
			int[] arr = new int[M];
			Queue<Person> q = lr == 0 ? lq : rq;
			int num = 0;
			while (!q.isEmpty() && q.peek().time <= time && num < M) {
				arr[num++] = q.poll().idx;
			}
			time += t;
			lr = (lr + 1) % 2;

			for (int i = 0; i < num; i++) {
				times[arr[i]] = time;
			}
		}
		for (int item : times) {
			output.append(item).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(input.readLine());
		M = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lq = new PriorityQueue<>();
		rq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(input.readLine());
			int time = Integer.parseInt(st.nextToken()), lr = st.nextToken().equals("left") ? 0 : 1;
			if (lr == 0) {
				lq.offer(new Person(time, i));
			} else {
				rq.offer(new Person(time, i));
			}
		}

		solution();

		System.out.println(output);
	}
}

import java.util.*;
import java.io.*;

class Car {
    int pos;
    int fuel;

    Car(int pos, int fuel) {
        this.pos = pos;
        this.fuel = fuel;
    }
}

class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, S;
    static int[] positions;
    static int[] fuels;

    static void solution() {
        boolean[] visited = new boolean[N];
        visited[S - 1] = true;
        Queue<Car> q = new ArrayDeque<>();
        q.offer(new Car(S - 1, fuels[S - 1]));

        while (!q.isEmpty()) {
            Car car = q.poll();
            // 왼쪽 탐색
            for (int pos = car.pos - 1; pos >= 0; pos--) {
                if (car.fuel < positions[car.pos] - positions[pos]) {
                    break;
                }
                if (!visited[pos]) {
                    visited[pos] = true;
                    q.offer(new Car(pos, fuels[pos]));
                }
            }
            // 오른쪽 탐색
            for (int pos = car.pos + 1; pos < N; pos++) {
                if (car.fuel < positions[pos] - positions[car.pos]) {
                    break;
                }
                if (!visited[pos]) {
                    visited[pos] = true;
                    q.offer(new Car(pos, fuels[pos]));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                output.append(i + 1).append(" ");
            }
        }
    }


    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        positions = new int[N];
        fuels = new int[N];
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(input.readLine());
        for (int i = 0; i < N; i++) {
            fuels[i] = Integer.parseInt(st.nextToken());
        }

        solution();

        System.out.println(output);
    }
}

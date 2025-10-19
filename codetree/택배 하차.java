import java.util.*;
import java.io.*;

class Box implements Comparable<Box> {
    int r, c, h, w, k;
    boolean isAlive;

    Box(int r, int c, int h, int w, int k) {
        this.r = r;
        this.c = c;
        this.h = h;
        this.w = w;
        this.k = k;
        this.isAlive = true;
    }

    public int compareTo(Box b) {
        return (-1) * Integer.compare(this.r + this.h, b.r + b.h);
    }
}


public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static Box[] infos;
    static Map<Integer, Box> boxMap;

    static void dropBox(int[][] map, Box box) {
        int nr = box.r + box.h - 1;
        label: while(++nr < N) {
            for(int c = box.c; c < box.c + box.w; c++) {
                if(map[nr][c] != 0) {
                    break label;
                }
            }
        }
        int diff = nr - (box.r + box.h);
        for(int r = box.r; r < box.r + diff; r++) {
            for(int c = box.c; c < box.c + box.w; c++) {
                map[r][c] = 0;
            }
        }
        for(int r = nr - box.h; r < nr; r++) {
            for(int c = box.c; c < box.c + box.w; c++) {
                map[r][c] = box.k;
            }
        }
        box.r = nr - box.h;
    }

    static Queue<Box> initMap(int[][] map) {
        Queue<Box> pq = new PriorityQueue<>();
        for(Box box: infos) {
            dropBox(map, box);
            pq.offer(box);
        }
        return pq;
    }

    static Queue<Box> takeOut(int[][] map, int sc, Queue<Box> boxPQ) {
        int k = 101;
        Set<Integer> visited = new HashSet<>();
        for(int c = sc; c >= 0 && c < N; c += (sc == 0 ? 1 : -1)) {
            for(int r = 0; r < N; r++) {
                if(visited.contains(r) || map[r][c] == 0) {
                    continue;
                }
                Box box = boxMap.get(map[r][c]);
                boolean flag = false;
                for(int br = box.r; br < box.r + box.h; br++) {
                    if(visited.contains(br)) {
                        flag = true;
                    }
                    visited.add(br);
                }
                if(!flag && box.k < k) {
                    k = box.k;
                }
            }
        }
        output.append(k).append("\n");
        Box selected = boxMap.get(k);
        selected.isAlive = false;
        for(int r = selected.r; r < selected.r + selected.h; r++) {
            for(int c = selected.c; c < selected.c + selected.w; c++) {
                map[r][c] = 0;
            }
        }
        Queue<Box> pq = new PriorityQueue<>();
        while(!boxPQ.isEmpty()) {
            Box box = boxPQ.poll();
            if(!box.isAlive) {
                continue;
            }
            dropBox(map, box);
            pq.offer(box);
        }
        return pq;
    }

    static void solution() {
        int[][] map = new int[N][N];
        Queue<Box> pq = initMap(map);

        for(int i = 0; i < M; i++) {
            pq = takeOut(map, i % 2 == 0 ? 0 : N - 1, pq);
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(input.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        infos = new Box[M];
        boxMap = new HashMap<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(input.readLine());
            int k = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()) - 1, r = 0;
            Box box = new Box(r, c, h, w, k);
            infos[i] = box;
            boxMap.put(k, box);
        }

        solution();

        System.out.println(output);
    }
}

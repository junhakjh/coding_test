import java.util.*;

class Solution {
    void dfs(int curNode, List<Integer> preOrder, List<Integer> postOrder, List<Integer>[] children) {
        preOrder.add(curNode);
        
        for(int nextNode: children[curNode]) {
            dfs(nextNode, preOrder, postOrder, children);
        }
        
        postOrder.add(curNode);
    }
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        
        int[][] arr = new int[nodeinfo.length][3];
        for(int i = 0; i < nodeinfo.length; i++) {
            arr[i] = new int[] {nodeinfo[i][0], nodeinfo[i][1], i + 1};
        }
        Arrays.sort(arr, (a, b) -> {
            if(a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return (-1) * Integer.compare(a[1], b[1]);
        });
        
        int height = 1;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i][1] != arr[i - 1][1]) {
                height++;
            }
        }
        int floorIdx = 0;
        List<int[]>[] floors = new ArrayList[height];
        for(int i = 0; i < height; i++) {
            floors[i] = new ArrayList<>();
        }
        for(int i = 0; i < arr.length; i++) {
            if(i > 0 && arr[i][1] != arr[i - 1][1]) {
                floorIdx++;
            }
            floors[floorIdx].add(arr[i]);
        }
        
        List<Integer>[] children = new ArrayList[nodeinfo.length + 1];
        for(int i = 1; i <= nodeinfo.length; i++) {
            children[i] = new ArrayList<>();
        }
        
        int[][] boundaries = new int[nodeinfo.length + 1][2];
        for(int[] boundary: boundaries) {
            boundary[0] = -1;
            boundary[1] = 100_001;
        }
        if(height > 1) {
            int[] root = floors[0].get(0);
            for(int[] floor: floors[1]) {
                children[root[2]].add(floor[2]);
                if(floor[0] < root[0]) {
                    boundaries[floor[2]][1] = root[0];
                } else {
                    boundaries[floor[2]][0] = root[0];
                }
            }
        }
        for(int i = 2; i < height; i++) {
            int j = 0;
            for(int[] floor: floors[i]) {
                while(j < floors[i - 1].size()) {
                    int[] parent = floors[i - 1].get(j);
                    int lb = boundaries[parent[2]][0], rb = boundaries[parent[2]][1];
                    if(lb < floor[0] && floor[0] < rb) {
                        children[parent[2]].add(floor[2]);
                        if(floor[0] < parent[0]) {
                            boundaries[floor[2]] = new int[] {lb, parent[0]};
                        } else {
                            boundaries[floor[2]] = new int[] {parent[0], rb};
                        }
                        break;
                    }
                    j++;
                }
            }
        }
        
        List<Integer> preOrder = new ArrayList<>(), postOrder = new ArrayList<>();
        dfs(floors[0].get(0)[2], preOrder, postOrder, children);
        for(int i = 0; i < nodeinfo.length; i++) {
            answer[0][i] = preOrder.get(i);
            answer[1][i] = postOrder.get(i);
        }
        
        return answer;
    }
}

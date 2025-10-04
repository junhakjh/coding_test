import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> inSet = new HashSet<>();
        for(int[] edge: edges) {
            map.putIfAbsent(edge[0], new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            inSet.add(edge[1]);
        }
        
        for(int key: map.keySet()) {
            if(map.get(key).size() >= 2 && !inSet.contains(key)) {
                answer[0] = key;
            }
        }
        
        Set<Integer> visited = new HashSet<>();
        visited.add(answer[0]);
        label: for(int startIdx: map.get(answer[0])) {
            visited.add(startIdx);
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(startIdx);
            while(!q.isEmpty()) {
                int node = q.poll();
                if(map.get(node) == null) {
                    answer[2]++;
                    continue label;
                }
                for(int idx: map.get(node)) {
                    if(map.get(idx) == null) {
                        answer[2]++;
                        continue label;
                    }
                    if(map.get(idx).size() == 2) {
                        answer[3]++;
                        continue label;
                    }
                    if(!visited.contains(idx)) {
                        visited.add(idx);
                        q.offer(idx);
                    }
                }
            }
            answer[1]++;
        }
        
        return answer;
    }
}

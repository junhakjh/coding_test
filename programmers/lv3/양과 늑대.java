import java.util.*;

class Solution {
    int maxSheep = 1;
    Map<Integer, List<Integer>> children = new HashMap<>();
    
    void dfs(int[] info, Set<Integer> includeSet, int sheeps, int wolves) {
        Set<Integer> newSet = new HashSet<>(includeSet);
        
        for(int idx: includeSet) {
            newSet.add(idx);
            for(int child: children.get(idx)) {
                if(!includeSet.contains(child)) {
                    if(info[child] == 0 || (info[child] == 1 && sheeps - wolves > 1)) {
                        newSet.add(child);
                        int newSheeps = sheeps, newWolves = wolves;
                        if(info[child] == 0) {
                            newSheeps += 1;
                            maxSheep = Math.max(maxSheep, newSheeps);
                        } else {
                            newWolves += 1;
                        }
                        dfs(info, newSet, newSheeps, newWolves);
                        newSet.remove(child);
                    }
                }
            }
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        for(int i = 0; i < info.length; i++) {
            children.put(i, new ArrayList<>());
        }
        
        for(int[] edge: edges) {
            children.get(edge[0]).add(edge[1]);
        }
        
        Set<Integer> set = new HashSet<>();
        set.add(0);
        
        dfs(info, set, 1, 0);
        
        answer = maxSheep;
        
        return answer;
    }
}

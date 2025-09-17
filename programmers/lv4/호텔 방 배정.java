import java.util.*;

class Node {
    long parent, max;
    
    Node(long parent, long max) {
        this.parent = parent;
        this.max = max;
    }
}

class Union {
    Map<Long, Node> parents;
    
    Union() {
        parents = new HashMap<>();
    }
    
    long findRoot(long idx) {
        Node node = parents.get(idx);
        if(node.parent == -1) {
            return idx;
        }
        
        return node.parent = findRoot(node.parent);
    }
    
    boolean union(long a, long b) {
        long aRoot = findRoot(a);
        long bRoot = findRoot(b);
        if(aRoot == bRoot) {
            return false;
        }
        
        long max = Math.max(parents.get(aRoot).max, parents.get(bRoot).max);
        parents.get(aRoot).max = max;
        parents.get(bRoot).max = max;
        parents.get(bRoot).parent = aRoot;
        return true;
    }
}

class Solution {
    void unionLeftRight(Union union, long idx) {
        if(union.parents.containsKey(idx - 1)) {
            union.union(idx - 1, idx);
        }
        if(union.parents.containsKey(idx + 1)) {
            union.union(idx + 1, idx);
        }
    }
    
    public long[] solution(long k, long[] roomNumber) {
        long[] answer = new long[roomNumber.length];
        Union union = new Union();
        for(int i = 0; i < roomNumber.length; i++) {
            long room = roomNumber[i];
            if(union.parents.containsKey(room)) {
                room = union.parents.get(union.findRoot(room)).max + 1;
            }
            union.parents.put(room, new Node(-1, room));
            answer[i] = room;
            unionLeftRight(union, room);
        }
        
        return answer;
    }
}

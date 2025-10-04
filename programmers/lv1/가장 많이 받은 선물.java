import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < friends.length; i++) {
            map.put(friends[i], i);
        }
        int[][] table = new int[friends.length][friends.length];
        int[] giftScore = new int[friends.length];
        for(String str: gifts) {
            String[] arr = str.split(" ");
            int a = map.get(arr[0]), b = map.get(arr[1]);
            table[a][b]++;
            giftScore[a]++;
            giftScore[b]--;
        }
        
        int[] nums = new int[friends.length];
        
        for(int a = 0; a < friends.length; a++) {
            for(int b = 0; b < friends.length; b++) {
                if(a == b) {
                    continue;
                }
                if(table[a][b] == table[b][a]) {
                    if(giftScore[a] > giftScore[b]) {
                        nums[a]++;
                    }
                } else if(table[a][b] > table[b][a]) {
                    nums[a]++;
                }
            }
        }
        
        for(int i = 0; i < friends.length; i++) {
            answer = Math.max(answer, nums[i]);
        }
        
        return answer;
    }
}

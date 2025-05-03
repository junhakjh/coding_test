import java.util.*;

class Solution {
    int getMin(String startStr, String endStr) {
        String[] startArr = startStr.split(":"), endArr = endStr.split(":");
        int startMin = Integer.parseInt(startArr[0]) * 60 + Integer.parseInt(startArr[1]),
            endMin = Integer.parseInt(endArr[0]) * 60 + Integer.parseInt(endArr[1]);
        return endMin - startMin;
    }
    
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        
        Map<Character, Integer> mapper = new HashMap<>();
        mapper.put('C', 0);
        mapper.put('D', 2);
        mapper.put('E', 4);
        mapper.put('F', 5);
        mapper.put('G', 7);
        mapper.put('A', 9);
        mapper.put('B', 11);
        
        List<Integer> mList = new ArrayList<>();
        List<List<Integer>> musicNameList = new ArrayList<>();
        String[][] musicInfoArr = new String[musicinfos.length][4];
        for(int i = 0; i < musicinfos.length; i++) {
            musicInfoArr[i] = musicinfos[i].split(",");
        }
        
        for(int i = 0; i < m.length(); i++) {
            if(m.charAt(i) == '#') continue;
            int num = mapper.get(m.charAt(i));
            if(i < m.length() - 1 && m.charAt(i + 1) == '#') num++;
            mList.add(num);
        }
        for(String[] musicInfo: musicInfoArr) {
            String str = musicInfo[3];
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == '#') continue;
                int num = mapper.get(str.charAt(i));
                if(i < str.length() - 1 && str.charAt(i + 1) == '#') num++;
                list.add(num);
            }
            musicNameList.add(list);
        }
        
        int maxMin = 0;
        for(int musicIdx = 0; musicIdx < musicInfoArr.length; musicIdx++) {
            List<Integer> musicName = musicNameList.get(musicIdx);
            int runMin = getMin(musicInfoArr[musicIdx][0], musicInfoArr[musicIdx][1]);
            for(int i = 0; i < runMin; i++) {
                int min = i, idx = 0;
                while(min < runMin && idx < mList.size()) {
                    if(mList.get(idx) != musicName.get(min % musicName.size())) {
                        break;
                    }
                    idx++;
                    min++;
                }
                if(idx == mList.size()) {
                    if(runMin > maxMin) {
                        maxMin = runMin;
                        answer = musicInfoArr[musicIdx][2];
                    }
                    break;
                }
            }
        }
        
        return answer;
    }
}

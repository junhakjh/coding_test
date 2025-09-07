import java.util.*;

class Solution {
    public String[] solution(String[] commands) {
        List<String> answerList = new ArrayList<>();
        
        int[][] union = new int[51][51];
        String[][] map = new String[51][51];
        for(int r = 1; r <= 50; r++) {
            for(int c = 1; c <= 50; c++) {
                union[r][c] = 50 * r + c;
                map[r][c] = "";
            }
        }
        
        for(String command: commands) {
            String[] commandList = command.split(" ");
            switch(commandList[0]) {
                case "UPDATE":
                    if(commandList.length == 4) {
                        int r = Integer.parseInt(commandList[1]), c = Integer.parseInt(commandList[2]);
                        String value = commandList[3];
                        int target = union[r][c];
                        for(r = 1; r <= 50; r++) {
                            for(c = 1; c <= 50; c++) {
                                if(union[r][c] == target) {
                                    map[r][c] = value;
                                }
                            }
                        }
                    }
                    if(commandList.length == 3) {
                        String value1 = commandList[1], value2 = commandList[2];
                        for(int r = 1; r <= 50; r++) {
                            for(int c = 1; c <= 50; c++) {
                                if(map[r][c].equals(value1)) {
                                    map[r][c] = value2;
                                }
                            }
                        }
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(commandList[1]), c1 = Integer.parseInt(commandList[2]), r2 = Integer.parseInt(commandList[3]), c2 = Integer.parseInt(commandList[4]);
                    if(r1 == r2 && c1 == c2) {
                        break;
                    }
                    int root = union[r1][c1], target = union[r2][c2];
                    String value = map[r2][c2];
                    if(!map[r1][c1].equals("")) {
                        value = map[r1][c1];
                    }
                    for(int r = 1; r <= 50; r++) {
                        for(int c = 1; c <= 50; c++) {
                            if(union[r][c] == target) {
                                union[r][c] = root;
                            }
                            if(union[r][c] == root) {
                                map[r][c] = value;
                            }
                        }
                    }
                    break;
                case "UNMERGE":
                    int sr = Integer.parseInt(commandList[1]), sc = Integer.parseInt(commandList[2]);
                    int targetIdx = union[sr][sc];
                    String string = map[sr][sc];
                    for(int r = 1; r <= 50; r++) {
                        for(int c = 1; c <= 50; c++) {
                            if(union[r][c] == targetIdx) {
                                union[r][c] = 50 * r + c;
                                map[r][c] = "";
                            }
                            if(r == sr && c == sc) {
                                map[r][c] = string;
                            }
                        }
                    }
                    break;
                case "PRINT":
                    int r = Integer.parseInt(commandList[1]), c = Integer.parseInt(commandList[2]);
                    if(map[r][c].equals("")) {
                        answerList.add("EMPTY");
                    } else {
                        answerList.add(map[r][c]);
                    }
                    break;
            }
        }
        
        
        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}

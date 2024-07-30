import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder output = new StringBuilder();
    static StringTokenizer st;

    static final int CODE_LEN = 8 * 19;
    static final String alphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";

    static void solution(String hex) {
        String bin = hexToBinary(hex);
        int i = 0;

        int totalLen = 0;
        StringBuilder sb = new StringBuilder();
        loop: while(i < bin.length()) {
            if (bin.length() < i + 4) {
                break;
            }
            String mode = bin.substring(i, i += 4);
            String countBits;
            int bitLen;
            switch (mode) {
                case "0001": {
                    countBits = bin.substring(i, i += 10);
                    bitLen = Integer.parseInt(countBits, 2);
                    totalLen += bitLen;
                    for(int unit = 0; unit < bitLen / 3; unit++) {
                        String bits = bin.substring(i, i += 10);
                        String origin = "" + Integer.parseInt(bits, 2);
                        sb.append("0".repeat(3 - origin.length())).append(origin);
                    }
                    if(bitLen % 3 == 1) {
                        String bits = bin.substring(i, i += 4);
                        String origin = "" + Integer.parseInt(bits, 2);
                        sb.append("0".repeat(1 - origin.length())).append(origin);
                    } else if(bitLen % 3 == 2) {
                        String bits = bin.substring(i, i += 7);
                        String origin = "" + Integer.parseInt(bits, 2);
                        sb.append("0".repeat(2 - origin.length())).append(origin);
                    }
                    break;
                }
                case "0010": {
                    countBits = bin.substring(i, i += 9);
                    bitLen = Integer.parseInt(countBits, 2);
                    totalLen += bitLen;
                    for(int unit = 0; unit < bitLen / 2; unit++) {
                        String bits = bin.substring(i, i += 11);
                        int code = Integer.parseInt(bits, 2);
                        int left = code / 45 % 45;
                        int right = code % 45;
                        sb.append(alphaNumeric.charAt(left)).append(alphaNumeric.charAt(right));
                    }
                    if(bitLen % 2 == 1) {
                        String bits = bin.substring(i, i += 6);
                        int code = Integer.parseInt(bits, 2);
                        int left = code % 45;
                        sb.append(alphaNumeric.charAt(left));
                    }
                    break;
                }
                case "0100": {
                    countBits = bin.substring(i, i += 8);
                    bitLen = Integer.parseInt(countBits, 2);
                    totalLen += bitLen;
                    for(int unit = 0; unit < bitLen; unit++) {
                        String bits = bin.substring(i, i += 8);
                        int codeInt = Integer.parseInt(bits, 2);
                        if(codeInt >= 32 && codeInt <= 126) sb.append(Character.toChars(codeInt));
                        else {
                            String code = Integer.toHexString(codeInt).toUpperCase();
                            sb.append("\\").append("0".repeat(2 - code.length())).append(code);
                        }
                    }
                    break;
                }
                case "1000": {
                    countBits = bin.substring(i, i += 8);
                    bitLen = Integer.parseInt(countBits, 2);
                    totalLen += bitLen;
                    for(int unit = 0; unit < bitLen; unit++) {
                        String bits = bin.substring(i, i += 13);
                        String code = Integer.toHexString(Integer.parseInt(bits, 2)).toUpperCase();
                        sb.append("#").append("0".repeat(4 - code.length())).append(code);
                    }
                    break;
                }
                default: {
                    break loop;
                }
            }
        }
        output.append(totalLen).append(" ").append(sb).append("\n");
    }

    static String hexToBinary(String hex) {
        BigInteger decimal = new BigInteger(hex, 16);
        return "0".repeat(CODE_LEN - decimal.toString(2).length()) + decimal.toString(2);
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(input.readLine());
        for(int tc = 0; tc < t; tc++) {
            String hex = input.readLine();
            solution(hex);
        }

        System.out.println(output);
    }
}

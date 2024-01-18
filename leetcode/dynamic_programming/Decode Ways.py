class Solution:
    def numDecodings(self, s: str) -> int:
        if s[0] == '0': return 0
        if len(s) == 1: return 1
        arr = [1]
        if 10 < int(s[:2]) < 20 or 20 < int(s[:2]) <= 26:
            arr.append(2)
        else:
            if int(s[:2]) % 10 == 0 and int(s[:2]) // 10 >= 3: return 0
            arr.append(1)
        if len(s) == 2: return arr[-1]
        for i in range(2, len(s)):
            two_digit = 10 * int(s[i - 1]) + int(s[i])
            if (two_digit < 10 or two_digit > 26) and s[i] == '0':
                return 0
            if 10 <= two_digit <= 26:
                if s[i] == '0':
                    arr.append(arr[-2])
                else:
                    arr.append(arr[-2] + arr[-1])
            else:
                arr.append(arr[-1])
        return arr[-1]

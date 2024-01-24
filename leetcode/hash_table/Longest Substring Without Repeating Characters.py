class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        answer, char_dict, num, start_i = 0, {}, 0, 0
        for i, char in enumerate(s):
            if char in char_dict and char_dict[char] >= start_i:
                answer = max(answer, num)
                num, start_i = i - char_dict[char], char_dict[char] + 1
                char_dict[char] = i
                continue
            char_dict[char] = i
            num += 1
        return max(answer, num)

class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        ''' Solution
        l, c_frequency, longest_str_len = 0, defaultdict(int), 0
        for r, char in enumerate(s):
            c_frequency[char] += 1
            
            # Replacements cost = cells count between left and right - highest frequency
            cells_count = r - l + 1
            if cells_count - max(c_frequency.values()) <= k:
                longest_str_len = max(longest_str_len, cells_count)
            else:
                c_frequency[s[l]] -= 1
                l += 1
        
        return longest_str_len
        '''

        answer = 0

        cur_c, l, r, s_dict = s[0], 0, 0, defaultdict(list)
        for i, char in enumerate(s):
            if char == cur_c:
                r = i
            else:
                s_dict[cur_c].append((l, r))
                answer = max(answer, r - l + 1)
                l = r = i
                cur_c = char
        s_dict[cur_c].append((l, r))
        answer = max(answer, r - l + 1)

        for char in s_dict:
            for cur_i in range(len(s_dict[char])):
                num, length, prev_r = k, 0, s_dict[char][cur_i][0] - 1
                for item in s_dict[char][cur_i:]:
                    cur_l, cur_r = item
                    num -= cur_l - prev_r - 1
                    if num < 0:
                        length += cur_l - prev_r - 1 + num
                        break
                    length += cur_r - prev_r
                    prev_r = cur_r
                if num > 0: 
                    length += min(num, len(s) - length)
                answer = max(answer, length)

        return answer

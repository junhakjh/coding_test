from collections import defaultdict

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        s_dict, t_dict = defaultdict(int), defaultdict(int)
        for char in t:
            t_dict[char] += 1
        min_length, answer, l = float('inf'), '', 0
        for r, char in enumerate(s):
            if char in t_dict:
                t_dict[char] -= 1
            while l < r:
                if s[l] in t_dict:
                    if t_dict[s[l]] >= 0:
                        break
                    else:
                        t_dict[s[l]] += 1
                l += 1
            if max(t_dict.values()) == 0:
                if min_length > r - l + 1:
                    answer = s[l:r] + char
                    min_length = r - l + 1
                check = False
                while l < r:
                    if s[l] in t_dict:
                        if check:
                            break
                        t_dict[s[l]] += 1
                        check = True
                    l += 1
        
        return answer

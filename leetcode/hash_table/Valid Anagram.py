from collections import defaultdict

class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        str_dict = defaultdict(int)
        for char in s:
            str_dict[char] += 1
        for char in t:
            if not char in str_dict:
                return False
            str_dict[char] -= 1
            if str_dict[char] == 0:
                del str_dict[char]
        return True if len(str_dict) == 0 else False

from collections import defaultdict

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        str_dict, answer = defaultdict(list), []
        for word in strs:
            sorted_word = ''.join(sorted(word))
            str_dict[sorted_word].append(word)
        for arr in str_dict.values():
            answer.append(arr)
        return answer

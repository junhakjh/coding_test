from collections import defaultdict

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        answer, words_len, length, word_dict = [], len(words), len(words[0]), defaultdict(int)
        window = words_len * length
        window_i = 0

        for word in words:
            word_dict[word] += 1
        
        while window_i <= len(s) - window:
            new_dict = defaultdict(int)
            for i in range(words_len):
                word = s[window_i + i * length:window_i + (i + 1) * length]
                new_dict[word] += 1
            if new_dict == word_dict:
                answer.append(window_i)
            
            window_i += 1

        return answer

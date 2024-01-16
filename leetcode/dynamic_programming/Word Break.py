class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        candidate = set(wordDict)
        while len(candidate) > 0:
            new_candidate = set()
            for word in candidate:
                if s == word:
                    return True
                for dict_word in wordDict:
                    new_word = word + dict_word
                    if len(new_word) > len(s):
                        continue
                    if s[:len(new_word)] == new_word:
                        new_candidate.add(new_word)
            candidate = new_candidate

        return False

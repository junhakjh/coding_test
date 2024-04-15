from collections import deque

class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        def check(a: str, b: str) -> bool:
            diff = 0
            for i in range(len(a)):
                if a[i] != b[i]:
                    diff += 1
                if diff > 1:
                    break
            return True if diff == 1 else False

        answer = 0
        word_set, q = set(wordList), deque([(beginWord, 1)])
        
        while q:
            word, num = q.popleft()
            if word == endWord:
                answer = num
                break
            
            for next_word in list(word_set):
                if check(word, next_word):
                    q.append((next_word, num + 1))
                    word_set.remove(next_word)

        return answer

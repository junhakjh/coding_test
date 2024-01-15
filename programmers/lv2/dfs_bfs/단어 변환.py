from collections import deque

def solution(begin, target, words):
    answer, queue = 0, deque([[begin, 0]])
    
    while len(queue) > 0:
        cur_word, num = queue.popleft()
        if cur_word == target:
            return num
        for word in words:
            diff = 0
            for i in range(len(word)):
                if not cur_word[i] == word[i]:
                    diff += 1
            if diff == 1:
                queue.append([word, num + 1])
                words.remove(word)
    
    return answer

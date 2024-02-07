from collections import deque

def solution(number, k):
    answer, i, target_len = [number[0]], 1, len(number) - k
    while i < len(number):
        if len(answer) == 0 or number[i] <= answer[-1]:
            answer.append(number[i])
            i += 1
        else:
            answer.pop()
            k -= 1
        if k == 0:
            answer += number[i:]
            break
            
    return ''.join(answer)[:target_len]

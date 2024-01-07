from itertools import combinations_with_replacement

def solution(n, info):
    answer = []
    
    max_diff = 0
    cases = list(combinations_with_replacement([i for i in range(11)], n))
    candidates = []
    for case in cases:
        ryan = [0] * 11
        for i in case:
            ryan[i] += 1
        candidates.append(ryan)
    
    for candidate in candidates:
        diff = 0
        for i in range(10):
            if candidate[i] == 0 and info[i] == 0:
                continue
            if candidate[i] > info[i]:
                diff += 10 - i
            else:
                diff -= 10 - i
        if diff > max_diff:
            max_diff = diff
            answer = [candidate]
        elif diff == max_diff:
            answer.append(candidate)
    
    answer.sort(key=lambda x: tuple(x[i] for i in range(10, -1, -1)))
    
    if len(answer) == 0 or max_diff == 0:
        answer = [[-1]]
    
    return answer[-1]

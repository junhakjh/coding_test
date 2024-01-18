import math

def solution(k, d):
    answer = 0
    
    for x in range(0, d + 1, k):
        answer += int(math.sqrt(pow(d, 2) - pow(x, 2))) // k + 1
    
    return answer

import math

def solution(n, k):
    answer = 0
    
    arr = k_Jinsu(n, k).split('0')
    for item in arr:
        if item == '':
            continue
        num = int(item)
        if isSosu(num):
            answer += 1
    
    return answer

def k_Jinsu(n, k):
    answer = ""
    
    while n > 0:
        answer = str(n % k) + answer
        n = n // k
    
    return answer

def isSosu(n):
    if n < 2:
        return False
    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0:
            return False
    return True

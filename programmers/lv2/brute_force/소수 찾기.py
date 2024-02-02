import math
from itertools import permutations

def solution(numbers):
    answer = 0
    
    for i in range(1, len(numbers) + 1):
        for pmt in set(permutations(numbers, i)):
            if pmt[0] == '0': continue
            if isSosu(int(''.join(list(pmt)))): 
                answer += 1
    
    return answer

def isSosu(num):
    if num <= 1: return False
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0: return False
    return True

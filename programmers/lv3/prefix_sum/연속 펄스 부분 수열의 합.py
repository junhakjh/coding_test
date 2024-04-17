def solution(sequence):
    answer = 0
    
    s1 = s2 = 0
    s1_min = s2_min = 0
    
    for i, num in enumerate(sequence):
        s1 += num * ((-1) ** i)
        s2 += num * ((-1) ** (i + 1))
        
        answer = max(answer, s1 - s1_min, s2 - s2_min)
        
        s1_min, s2_min = min(s1_min, s1), min(s2_min, s2)
    
    return answer

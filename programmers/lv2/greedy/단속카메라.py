def solution(routes):
    answer = 0
    
    routes.sort(key=lambda x:x[0])
    
    prev = -30001
    for start, end in routes:
        if start > prev:
            answer += 1
            prev = end
        else:
            prev = min(prev, end)
    
    return answer

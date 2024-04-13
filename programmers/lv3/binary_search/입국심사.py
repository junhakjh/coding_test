def solution(n, times):
    answer = 0
    l, r = 1, max(times) * n
    
    while l < r:
        m = (l + r) // 2
        
        people = 0
        for time in times:
            people += m // time
            if people > n:
                break
        
        if people < n:
            l = m + 1
        else:
            r = m
            answer = m
    
    return answer

def solution(cap, n, deliveries, pickups):
    answer = 0
    
    deliveries, pickups = deliveries[::-1], pickups[::-1]
    d_cap, p_cap = 0, 0
    
    for i in range(n):
        d_cap += deliveries[i]
        p_cap += pickups[i]
        
        while d_cap > 0 or p_cap > 0:
            d_cap -= cap
            p_cap -= cap
            answer += (n - i) * 2
            
    return answer

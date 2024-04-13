def solution(distance, rocks, n):
    answer = 0
    
    rocks.sort()
    rocks.append(distance)
    l, r = 0, distance
    
    while l < r:
        m = (l + r) // 2 + 1
        
        prev, delete = 0, 0
        for rock in rocks:
            dist = rock - prev
            if dist < m:
                delete += 1
            else:
                prev = rock
            if delete > n:
                break
        
        if delete <= n:
            l = m
        else:
            r = m - 1
    
    return r

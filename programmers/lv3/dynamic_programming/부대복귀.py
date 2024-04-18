from collections import defaultdict, deque

def solution(n, roads, sources, destination):
    answer, route_dict = [], defaultdict(set)
    
    for a, b in roads:
        route_dict[a].add(b)
        route_dict[b].add(a)
        
    dp, q = [-1] * (n + 1), deque([(destination, 0)])
    dp[destination] = 0
    while q:
        cur_r, dist = q.popleft()
        
        for next_r in route_dict[cur_r]:
            if dp[next_r] == -1 or dist + 1 < dp[next_r]:
                dp[next_r] = dist + 1
                q.append((next_r, dist + 1))
                
    for source in sources:
        answer.append(dp[source])
    
    return answer

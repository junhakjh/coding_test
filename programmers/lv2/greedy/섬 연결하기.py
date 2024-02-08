import heapq
from collections import defaultdict

def solution(n, costs):
    answer, visited, bridge_dict, h = 0, set(), defaultdict(list), []
    
    for i1, i2, cost in costs:
        bridge_dict[i1].append((i2, cost))
        bridge_dict[i2].append((i1, cost))
    
    visited.add(costs[0][0])
    for node, cost in bridge_dict[costs[0][0]]:
        heapq.heappush(h, (cost, node))
    
    while len(visited) < n:
        cost, i = heapq.heappop(h)
        if i in visited: continue
        answer += cost
        visited.add(i)
        for node, cost in bridge_dict[i]:
            heapq.heappush(h, (cost, node))
    
    return answer

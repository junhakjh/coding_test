from collections import defaultdict
import heapq

def solution(n, paths, gates, summits):
    answer_m, answer_i, connection = 0, 10000001, defaultdict(list)
    
    for i, j, w in paths:
        connection[i].append((j, w))
        connection[j].append((i, w))
    
    graph = [answer_i for _ in range(n + 1)]
    q = []
    
    for gate in gates:
        heapq.heappush(q, (0, gate))
        
    while q:
        max_w, node = heapq.heappop(q)
        if max_w >= graph[node]:
            continue
        graph[node] = max_w
        if node in summits:
            continue
        for n_node, w in connection[node]:
            heapq.heappush(q, (max(max_w, w), n_node))
    
    for summit in sorted(summits):
        if graph[summit] < answer_i:
            answer_m = summit
            answer_i = graph[summit]
    
    return [answer_m, answer_i]

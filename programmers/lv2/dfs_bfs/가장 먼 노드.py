from collections import defaultdict, deque

def solution(n, edge):
    answer, node_dict = 0, defaultdict(list)
    
    for v1, v2 in edge:
        node_dict[v1].append(v2)
        node_dict[v2].append(v1)
    
    visited, q = [False for _ in range(n)], deque([(1, 0)])
    visited[0], dist = True, 0
    
    while q:
        cur_vertex, cur_dist = q.popleft()
        if cur_dist > dist:
            dist, answer = cur_dist, 1
        elif cur_dist == dist:
            answer += 1
        
        for vertex in node_dict[cur_vertex]:
            if not visited[vertex - 1]:
                visited[vertex - 1] = True
                q.append((vertex, cur_dist + 1))
    
    return answer

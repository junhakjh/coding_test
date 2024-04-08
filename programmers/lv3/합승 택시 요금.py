from collections import deque, defaultdict

def solution(n, s, a, b, fares):
    answer, fare_dict = 0, defaultdict(list)
    
    for fare in fares:
        fare_dict[fare[0]].append([fare[1], fare[2]])
        fare_dict[fare[1]].append([fare[0], fare[2]])
    
    nodes = [float('inf')] * (n + 1)
    nodes[s], q = 0, deque([s])
    
    while q:
        node = q.popleft()
        for n_node, cost in fare_dict[node]:
            if nodes[n_node] > nodes[node] + cost:
                nodes[n_node] = nodes[node] + cost
                q.append(n_node)
    
    answer = nodes[a] + nodes[b]
    
    for i in range(1, n + 1):
        if i == s: continue
        new_nodes = [float('inf')] * (n + 1)
        new_nodes[i], q = nodes[i], deque([i])
        while q:
            node = q.popleft()
            for n_node, cost in fare_dict[node]:
                if new_nodes[n_node] > new_nodes[node] + cost:
                    new_nodes[n_node] = new_nodes[node] + cost
                    q.append(n_node)
        answer = min(answer, new_nodes[a] + new_nodes[b] - new_nodes[i])
    
    return answer

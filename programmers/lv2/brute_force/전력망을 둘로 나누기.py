from collections import defaultdict, deque

def solution(n, wires):
    answer = float('inf')
    
    for num in range(len(wires)):
        wire_dict = defaultdict(list)
        for i, (node1, node2) in enumerate(wires):
            if i == num: continue
            wire_dict[node1].append(node2)
            wire_dict[node2].append(node1)
        length, q, visited = 0, deque([1]), [False for _ in range(len(wires) + 1)]
        while q:
            node = q.popleft()
            visited[node - 1] = True
            length += 1
            for n_node in wire_dict[node]:
                if not visited[n_node - 1]:
                    q.append(n_node)
        answer = min(answer, abs(length - (len(wires) + 1 - length)))        
    
    return answer

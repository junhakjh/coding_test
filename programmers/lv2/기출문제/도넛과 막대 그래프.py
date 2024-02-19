from collections import defaultdict, deque

def solution(edges):
    answer, received_set, node_dict = [0, 0, 0, 0], set(), defaultdict(list)
    
    for a, b in edges:
        received_set.add(b)
        node_dict[a].append(b)

    new_node = 0
    for key in node_dict:
        if len(node_dict[key]) > 1 and not key in received_set:
            new_node = key
            answer[0] = new_node
            break
    
    for node in node_dict[key]:
        visited_set, q, node_num, edge_num = set([node]), deque([node]), 1, 0
        while q:
            cur_node = q.popleft()
            for next_node in node_dict[cur_node]:
                edge_num += 1
                if not next_node in visited_set:
                    q.append(next_node)
                    visited_set.add(next_node)
                    node_num += 1
        if node_num == edge_num:
            answer[1] += 1
        elif node_num > edge_num:
            answer[2] += 1
        else:
            answer[3] += 1
    
    return answer

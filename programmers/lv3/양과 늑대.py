from collections import defaultdict

def solution(info, edges):
    answer, node_dict = 0, defaultdict(list)
    
    for edge in edges:
        node_dict[edge[0]].append(edge[1])
    
    def dfs(wolves, sheeps, cand_set):
        nonlocal answer
        check = False
        for i in cand_set:
            if sheeps - wolves - info[i] > 0:
                new_cand_list = list(cand_set)
                new_cand_list.remove(i)
                new_cand_list.extend(node_dict[i])
                new_cand_set = set(new_cand_list)
                check = True
                if info[i] == 0:
                    dfs(wolves, sheeps + 1, new_cand_set)
                else:
                    dfs(wolves + 1, sheeps, new_cand_set)
                
        if not check:
            answer = max(answer, sheeps)
    
    dfs(0, 1, set(node_dict[0]))
    
    return answer

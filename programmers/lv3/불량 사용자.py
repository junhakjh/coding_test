def solution(user_id, banned_id):
    answer, candidates, set_list = 0, [], []
    
    for b_id in banned_id:
        candidate = []
        for u_id in user_id:
            if len(u_id) != len(b_id): continue
            check = True
            for i, char in enumerate(b_id):
                if char == '*': continue
                if char != u_id[i]:
                    check = False
                    break
            if check:
                candidate.append(u_id)
        candidates.append(candidate)

    def dfs(candidates_i, candidate_set):
        nonlocal set_list
        
        if candidates_i == len(candidates):
            if candidate_set not in set_list:
                set_list.append(set(candidate_set))
            return
        
        for string in candidates[candidates_i]:
            if string not in candidate_set:
                candidate_set.add(string)
                dfs(candidates_i + 1, candidate_set)
                candidate_set.remove(string)
    
    dfs(0, set())
    
    answer = len(set_list)
    
    return answer

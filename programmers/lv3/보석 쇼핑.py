from collections import deque, defaultdict

def solution(gems):
    answer, q = [], deque([])
    gem_dict, gem_set, max_gem = defaultdict(int), set(), 0
    
    for i, gem in enumerate(gems):
        gem_dict[gem] += 1
        gem_set.add(gem)
        q.append([i, gem])
        while q:
            if gem_dict[q[0][1]] > 1:
                gem_dict[q[0][1]] -= 1
                q.popleft()
            else:
                break
        if len(gem_set) > max_gem:
            max_gem = len(gem_set)
            answer = [q[0][0] + 1, q[-1][0] + 1]
        elif len(gem_set) == max_gem:
            if answer[1] - answer[0] > q[-1][0] - q[0][0]:
                answer = [q[0][0] + 1, q[-1][0] + 1]
        else: continue
    
    return answer

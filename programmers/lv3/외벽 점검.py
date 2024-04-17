from itertools import permutations

def solution(n, weak, dist):
    answer, weak_n = 9, len(weak)
    
    for p_dist in permutations(dist):
        for start_i, start_weak in enumerate(weak):
            prev_weak, prev_dist_i = start_weak, 0

            check = False
            for i in range(start_i + 1, start_i + weak_n):
                i = i % weak_n
                cur_weak = weak[i]
                if cur_weak < prev_weak:
                    cur_weak += n
                if cur_weak - prev_weak > p_dist[prev_dist_i]:
                    prev_weak, prev_dist_i = weak[i], prev_dist_i + 1
                if prev_dist_i >= len(p_dist):
                    check = True
                    break

            if not check:
                answer = min(answer, prev_dist_i + 1)
    
    return -1 if answer == 9 else answer

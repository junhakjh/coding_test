from itertools import permutations
import heapq

def solution(picks, minerals):
    answer, mineral_lv_dict, heap = 0, {}, []
    minerals = minerals[:sum(picks) * 5]
    heapq.heapify(heap)
    for i in range(0, len(minerals), 5):
        num = 0
        for j in range(i, i + 5):
            if j == len(minerals):
                break
            if minerals[j] == 'diamond':
                num += 25
            elif minerals[j] == 'iron':
                num += 5
            else:
                num += 1
        if num in mineral_lv_dict:
            mineral_lv_dict[num].append(i)
        else:
            mineral_lv_dict[num] = [i]
        heapq.heappush(heap, (-1) * num)
        
    pick_i = 0
    while len(heap) > 0 and sum(picks) > 0:
        if pick_i > 2:
            break
        if picks[pick_i] == 0:
            pick_i += 1
            continue
        num = heapq.heappop(heap)
        start_i = mineral_lv_dict[(-1) * num].pop()
        for i in range(start_i, start_i + 5):
            if i == len(minerals):
                break
            if pick_i == 0:
                answer += 1
            elif pick_i == 1:
                if minerals[i] == 'diamond':
                    answer += 5
                else:
                    answer += 1
            else:
                if minerals[i] == 'diamond':
                    answer += 25
                elif minerals[i] == 'iron':
                    answer += 5
                else:
                    answer += 1
        picks[pick_i] -= 1
    return answer

from collections import defaultdict
from itertools import combinations

def solution(info_list, queries):
    answer = []
    infos = defaultdict(list)
    
    for info in info_list:
        condition, score = info.split()[:-1], info.split()[-1]
        for i in range(5):
            for comb in combinations([0, 1, 2, 3], i):
                temp = condition[:]
                for c in comb:
                    temp[c] = '-'
                infos['_'.join(temp)].append(int(score))
    
    for info in infos:
        infos[info].sort()

    for i, query in enumerate(queries):
        query = query.split()
        condition = '_'.join([query[0], query[2], query[4], query[6]])
        score = int(query[7])
        
        if condition in infos:
            info = infos[condition]
            l, r = 0, len(info)
            while l < r:
                m = (r + l) // 2
                if info[m] >= score:
                    r = m
                else:
                    l = m + 1
            answer.append(len(info) - r)
        else:
            answer.append(0)
    
    return answer

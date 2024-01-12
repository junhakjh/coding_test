from itertools import combinations

def solution(relation):
    answer = 0
    rows, columns = len(relation), len(relation[0])
    candidate = [i for i in range(columns)]
    keys = []
    
    for num in range(1, columns + 1):
        for comb in combinations(candidate, num):
            comb = set(comb)
            check = False
            for key in keys:
                if key.issubset(comb):
                    check = True
                    break
            if check:
                continue
            arr = []
            for row in relation:
                key = [row[i] for i in comb]
                if key in arr:
                    break
                else:
                    arr.append(key)
            if len(arr) == rows:
                keys.append(comb)
                answer += 1
    
    return answer

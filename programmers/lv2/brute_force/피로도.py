from itertools import permutations

def solution(k, dungeons):
    answer = -1
    
    for permutation in permutations(dungeons):
        health, num = k, 0
        for dungeon in permutation:
            if health < dungeon[0]:
                continue
            health, num = health - dungeon[1], num + 1
        answer = max(answer, num)
        if answer == len(dungeons): return answer
    
    return answer

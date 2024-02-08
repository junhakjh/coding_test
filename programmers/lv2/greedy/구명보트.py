from collections import defaultdict

def solution(people, limit):
    answer, visited = 0, [False for _ in range(len(people))]
    people.sort()
    
    l, r = 0, len(people) - 1
    while l <= r:
        if l == r: 
            answer += 1
            break
        if people[l] + people[r] <= limit:
            l += 1
        r -= 1
        answer += 1
        
    return answer

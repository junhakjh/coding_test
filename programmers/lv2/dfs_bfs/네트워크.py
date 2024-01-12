from collections import deque

def solution(n, computers):
    answer, visited = 0, [False for _ in range(n)]
    
    def dfs(cur_i):
        nonlocal visited, computers
        visited[cur_i] = True
        for i, num in enumerate(computers[cur_i]):
            if num == 1 and not visited[i]:
                dfs(i)
        
    for i in range(len(computers)):
        if not visited[i]:
            dfs(i)
            answer += 1
    
    return answer

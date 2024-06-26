dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]

n = int(input())
matrix = []
for _ in range(n):
    matrix.append(list(map(int, input().split())))

def solution():
    answer = 0
    def dfs(visited, depth, sum):
        nonlocal answer
        answer = max(answer, sum)
        if depth == 4:
            return

        for y in range(n):
            for x in range(n):
                if visited[y][x]:
                    continue
                for i in range(4):
                    nx, ny = x + dx[i], y + dy[i]
                    if 0 <= nx < n and 0 <= ny < n and not visited[ny][nx]:
                        visited[y][x], visited[ny][nx] = True, True
                        dfs(visited, depth + 1, sum + matrix[y][x] + matrix[ny][nx])
                        visited[y][x], visited[ny][nx] = False, False

    dfs([[False] * n for _ in range(n)], 0, 0)
    return answer

print(solution())

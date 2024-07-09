T = int(input())

for tc in range(1, T + 1):
    n, m = map(int, input().split())
    matrix = []
    for _ in range(n):
        matrix.append(list(map(int, input().split())))

    dx, dy = [1, 1, 0, -1, -1, -1, 0, 1], [0, 1, 1, 1, 0, -1, -1, -1]
    answer = 0

    def dfs(x, y, direction, s, depth):
        nx, ny = x + dx[direction], y + dy[direction]
        if 0 <= nx < n and 0 <= ny < n and depth < m:
            return dfs(nx, ny, direction, s + matrix[ny][nx], depth + 1)
        else:
            return s

    for y in range(n):
        for x in range(n):
            cur_sum = 0
            for i in range(4):
                cur_sum += dfs(x, y, i * 2, matrix[y][x], 1)
            answer = max(answer, cur_sum - (3 * matrix[y][x]))

            cur_sum = 0
            for i in range(4):
                cur_sum += dfs(x, y, i * 2 + 1, matrix[y][x], 1)
            answer = max(answer, cur_sum - (3 * matrix[y][x]))

    print(f"#{tc} {answer}")

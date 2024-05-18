T = int(input())
B, W = 1, 2

for test_case in range(1, T + 1):
    n, m = list(map(int, input().split()))
    matrix = [[0] * n for _ in range(n)]
    matrix[n // 2 - 1][n // 2 - 1], matrix[n // 2][n // 2], matrix[n // 2][n // 2 - 1], matrix[n // 2 - 1][n // 2] = W, W, B, B

    dx, dy = [1, 1, 0, -1, -1, -1, 0, 1], [0, 1, 1, 1, 0, -1, -1, -1]
    will_change, candidate = [], []
    def dfs(px, py, pcolor, pd):
        global candidate
        if matrix[py][px] == color:
            will_change.extend(candidate)
            return
        else:
            candidate.append((px, py))
        nx, ny = px + dx[pd], py + dy[pd]
        if 0 <= nx < n and 0 <= ny < n and matrix[ny][nx] != 0:
            dfs(nx, ny, pcolor, pd)

    for _ in range(m):
        x, y, color = list(map(int, input().split()))
        x, y = x - 1, y - 1
        matrix[y][x] = color
        for d in range(8):
            will_change, candidate = [], []
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < n and 0 <= ny < n and matrix[ny][nx] != 0:
                dfs(nx, ny, color, d)
            for cx, cy in will_change:
                matrix[cy][cx] = color

    black = white = 0
    for y in range(n):
        for x in range(n):
            if matrix[y][x] == B:
                black += 1
            if matrix[y][x] == W:
                white += 1

    print("#{} {} {}".format(test_case, black, white))

T = int(input())

for test_case in range(1, T + 1):
    n = int(input())
    matrix, answer = [], 'NO'
    for _ in range(n):
        matrix.append(input())

    dx, dy = [1, 1, 0, -1, -1, -1, 0, 1], [0, 1, 1, 1, 0, -1, -1, -1]
    def dfs(px, py, pd, num):
        global answer
        if answer == 'YES':
            return
        if num == 5:
            answer = 'YES'
            return
        nx, ny = px + dx[pd], py + dy[pd]
        if 0 <= nx < n and 0 <= ny < n:
            if matrix[ny][nx] == 'o':
                dfs(nx, ny, pd, num + 1)

    for y in range(n):
        if answer == 'YES':
            break
        for x in range(n):
            if answer == 'YES':
                break
            if matrix[y][x] == 'o':
                for d in range(8):
                    if answer == 'YES':
                        break
                    dfs(x, y, d, 1)

    print("#{} {}".format(test_case, answer))

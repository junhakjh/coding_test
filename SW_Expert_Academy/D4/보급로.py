from collections import deque

T = int(input())
dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
for test_case in range(1, T + 1):
    case_num = "#" + str(test_case)
    n = int(input())
    in_matrix = []
    for _ in range(n):
        in_matrix.append(list(map(int, input())))

    matrix, q = [[float('inf')] * n for _ in range(n)], deque([(0, 0)])
    matrix[0][0] = in_matrix[0][0]

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < n:
                if matrix[y][x] + in_matrix[ny][nx] < matrix[ny][nx]:
                    matrix[ny][nx] = in_matrix[ny][nx] + matrix[y][x]
                    q.append((nx, ny))

    print(case_num + ' ' + str(matrix[n - 1][n - 1]))

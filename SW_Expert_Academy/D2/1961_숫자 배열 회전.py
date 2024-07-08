T = int(input())

for tc in range(1, T + 1):
    n = int(input())
    matrix = []
    for _ in range(n):
        matrix.append(input().split())

    answer = [[''] * n for _ in range(n)]

    for i in range(3):
        rotated = [[''] * n for _ in range(n)]
        for y in range(n):
            for x in range(n):
                if i == 0:
                    rotated[y][x] = matrix[n - x - 1][y]
                elif i == 1:
                    rotated[y][x] = matrix[n - y - 1][n - x - 1]
                else:
                    rotated[y][x] = matrix[x][n - y - 1]
        for j in range(n):
            answer[j][i] = (''.join(rotated[j]))

    print(f"#{tc}")
    for i in range(n):
        print(' '.join(answer[i]))

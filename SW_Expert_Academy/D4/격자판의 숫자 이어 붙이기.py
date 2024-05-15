dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]

T = int(input())
for test_case in range(1, T + 1):
    matrix = []
    for _ in range(4):
        matrix.append(list(map(int, input().split())))

    answer_set = set()

    def dfs(cx, cy, string):
        global answer_set
        if len(string) == 7:
            answer_set.add(string)
            return
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            if 0 <= nx < 4 and 0 <= ny < 4:
                dfs(nx, ny, string + str(matrix[ny][nx]))

    for y in range(4):
        for x in range(4):
            dfs(x, y, str(matrix[y][x]))

    print("#{} {}".format(test_case, len(answer_set)))

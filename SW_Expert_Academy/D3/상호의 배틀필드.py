T = int(input())

dx, dy = [0, 1, 0, -1], [-1, 0, 1, 0]
U, D, L, R = 0, 2, 3, 1

for test_case in range(1, T + 1):
    h, w = list(map(int, input().split()))
    matrix = []
    for _ in range(h):
        matrix.append(list(map(str, input())))
    n, order = int(input()), input()
    cur_x = cur_y = d = 0
    for y in range(h):
        for x in range(w):
            if matrix[y][x] == "^":
                cur_x, cur_y, d = x, y, U
            if matrix[y][x] == "v":
                cur_x, cur_y, d = x, y, D
            if matrix[y][x] == "<":
                cur_x, cur_y, d = x, y, L
            if matrix[y][x] == ">":
                cur_x, cur_y, d = x, y, R

    for char in order:
        if char == "U":
            nx, ny, d = cur_x + dx[U], cur_y + dy[U], U
            if 0 <= nx < w and 0 <= ny < h and matrix[ny][nx] == ".":
                matrix[cur_y][cur_x], matrix[ny][nx], cur_x, cur_y = ".", "^", nx, ny
            else:
                matrix[cur_y][cur_x] = "^"
        elif char == "D":
            nx, ny, d = cur_x + dx[D], cur_y + dy[D], D
            if 0 <= nx < w and 0 <= ny < h and matrix[ny][nx] == ".":
                matrix[cur_y][cur_x], matrix[ny][nx], cur_x, cur_y = ".", "v", nx, ny
            else:
                matrix[cur_y][cur_x] = "v"
        elif char == "L":
            nx, ny, d = cur_x + dx[L], cur_y + dy[L], L
            if 0 <= nx < w and 0 <= ny < h and matrix[ny][nx] == ".":
                matrix[cur_y][cur_x], matrix[ny][nx], cur_x, cur_y = ".", "<", nx, ny
            else:
                matrix[cur_y][cur_x] = "<"
        elif char == "R":
            nx, ny, d = cur_x + dx[R], cur_y + dy[R], R
            if 0 <= nx < w and 0 <= ny < h and matrix[ny][nx] == ".":
                matrix[cur_y][cur_x], matrix[ny][nx], cur_x, cur_y = ".", ">", nx, ny
            else:
                matrix[cur_y][cur_x] = ">"
        else:
            nx, ny = cur_x + dx[d], cur_y + dy[d]
            while 0 <= nx < w and 0 <= ny < h:
                if matrix[ny][nx] == "*":
                    matrix[ny][nx] = "."
                    break
                if matrix[ny][nx] == "#":
                    break
                nx, ny = nx + dx[d], ny + dy[d]

    answer = "\n".join(["".join(arr) for arr in matrix])

    print(f"#{test_case} {answer}")

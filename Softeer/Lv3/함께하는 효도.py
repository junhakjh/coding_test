n, m = map(int, input().split())
matrix, positions = [], []
for _ in range(n):
    matrix.append(list(map(int, input().split())))
for _ in range(m):
    positions.append(tuple(map(int, input().split())))

dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
d_list = []
for i in range(pow(4, m)):
    d_candidate = []
    for j in range(1, m + 1):
        d_candidate.append((i // pow(4, m - j)) % 4)
    d_list.append(d_candidate)
    
def solution():
    answer = 0

    def dfs(position_list, num, sum, visited):
        nonlocal answer
        if num == 3:
            sum = 0
            for y, x in set(visited):
                sum += matrix[y - 1][x - 1]
            answer = max(answer, sum)
            return
        for d in d_list:
            np_list, check = [], False
            for ci, (cx, cy) in enumerate(position_list):
                nx, ny = cx + dx[d[ci]], cy + dy[d[ci]]
                if 0 < nx <= n and 0 < ny <= n:
                    np_list.append((nx, ny))
                    check = True
            if check:
                dfs(np_list, num + 1, sum, visited + np_list)

    dfs(positions, 0, 0, positions)
    return answer

print(solution())

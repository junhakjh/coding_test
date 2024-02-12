def solution(m, n, puddles):
    arr = [[0 for _ in range(m)] for _ in range(n)]
    arr[0][0] = 1
    for y in range(n):
        for x in range(m):
            if x == y == 0 or [x + 1, y + 1] in puddles: continue
            up = left = 0
            if y > 0:
                up = arr[y - 1][x]
            if x > 0:
                left = arr[y][x - 1]
            arr[y][x] = up + left
    
    return arr[n - 1][m - 1] % 1000000007

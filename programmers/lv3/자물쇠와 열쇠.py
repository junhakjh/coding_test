def rotate(i, arr):
    n = len(arr)
    rotated = [[0] * n for _ in range(n)]
    if i == 0:
        return arr
    
    for y in range(n):
        for x in range(n):
            if i == 1:
                rotated[x][n - y - 1] = arr[y][x]
            elif i == 2:
                rotated[n - y - 1][n - x - 1] = arr[y][x]
            else:
                rotated[n - x - 1][y] = arr[y][x]
    
    return rotated

def solution(key, lock):
    m, n = len(key), len(lock)
    expanded_lock = [[0] * (n * 3) for _ in range(n * 3)]
    for y in range(n, n * 2):
        for x in range(n, n * 2):
            expanded_lock[y][x] = lock[y - n][x - n]
            
    def check():
        for y in range(n, n * 2):
            for x in range(n, n * 2):
                if expanded_lock[y][x] != 1:
                    return False
        return True
    
    for y in range(1, n * 2):
        for x in range(1, n * 2):
            for i in range(4):
                r_key = rotate(i, key)
                for ry in range(m):
                    for rx in range(m):
                        expanded_lock[y + ry][x + rx] += r_key[ry][rx]
                if check():
                    return True
                for ry in range(m):
                    for rx in range(m):
                        expanded_lock[y + ry][x + rx] -= r_key[ry][rx]
    
    return False

def solution(triangle):
    for y, arr in enumerate(triangle):
        if y == 0: continue
        for x, num in enumerate(arr):
            if x == 0:
                triangle[y][x] = triangle[y - 1][x] + num
            elif x == len(arr) - 1:
                triangle[y][x] = triangle[y - 1][x - 1] + num
            else:
                triangle[y][x] = max(triangle[y - 1][x] + num, triangle[y - 1][x - 1] + num)
    
    return max(triangle[len(triangle) - 1])

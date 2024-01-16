def solution(m, n, startX, startY, balls):
    answer = []
    
    for ball in balls:
        x, y = ball
        candidates = [[x, (-1) * y], [(-1) * x, y], [x, y + 2 * (n - y)], [x + 2 * (m - x), y]]
        a, b, c, d = startX == x and startY > y, startY == y and startX > x, startX == x and startY < y, startY == y and startX < x
        check = [a, b, c, d]
        min_num = float('inf')
        for i, coordinate in enumerate(candidates):
            if check[i]:
                continue
            min_num = min(min_num, pow(startX - coordinate[0], 2) + pow(startY - coordinate[1], 2))
        answer.append(min_num)
        
    return answer

def solution(n, m, x, y, r, c, k):
    answer = ''
    
    dy, dx = r - x, c - y
    cy, cx = x, y
    
    while k > 0:
        if k == 1:
            if abs(dx + dy) != 1: return 'impossible'
            if dx == 1: answer += 'r'
            elif dx == -1: answer += 'l'
            elif dy == 1: answer += 'd'
            else: answer += 'u'
        # dy가 0보다 클 때는 무조건 아래로 이동
        elif dy > 0:
            answer += 'd'
            cy += 1
            dy -= 1
        # dy가 0보다 작거나 같음, 장난칠 여유 있음
        elif dy <= 0 and cy < n and k >= abs(dx) + (abs(dy) + 2):
            answer += 'd'
            cy += 1
            dy -= 1
        # dx가 0보다 작으면 무조건 왼쪽으로 이동
        elif dx < 0:
            answer += 'l'
            cx -= 1
            dx += 1
        # dx가 0 크거나 같음, 장난칠 여유 있음
        elif dx >= 0 and k >= (abs(dx) + 2) + abs(dy):
            # 왼쪽으로 더 갈 수 있음
            if cx > 1:
                answer += 'l'
                cx -= 1
                dx += 1
            # 왼쪽으로 더 갈 수 없음
            else:
                answer += 'r'
                cx += 1
                dx -= 1
        # dx가 0보다 큼, 장난칠 여유 없음
        elif dx > 0 and k < (abs(dx) + 2) + abs(dy):
            answer += 'r'
            cx += 1
            dx -= 1
        else:
            answer += 'u'
            cy -= 1
            dy += 1
        
        k -= 1
    
    return answer

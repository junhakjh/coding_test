def solution(places):
    answer = []
    
    dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
    
    for place in places:
        is_zero = False
        for y, row in enumerate(place):
            for x, element in enumerate(row):
                if not element == 'P':
                    continue
                for i in range(4):
                    nx, ny = x + dx[i], y + dy[i]
                    if 0 <= nx < len(row) and 0 <= ny < len(place):
                        if place[ny][nx] == 'P':
                            is_zero = True
                            answer.append(0)
                            break
                        elif place[ny][nx] == 'O':
                            for j in range(4):
                                nnx, nny = nx + dx[j], ny + dy[j]
                                if nnx == x and nny == y:
                                    continue
                                if 0 <= nnx < len(row) and 0 <= nny < len(place):
                                    if place[nny][nnx] == 'P':
                                        is_zero = True
                                        answer.append(0)
                                        break
                    if is_zero:
                        break
                if is_zero:
                    break
            if is_zero:
                break
        if not is_zero:
            answer.append(1)
    
    return answer

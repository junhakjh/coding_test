def solution(money):
    answer, n = 0, len(money)
    houses1, houses2 = [0 for _ in range(n - 1)], [0 for _ in range(n - 1)]
    
    for i in range(n - 1):
        i1, i2 = i, i + 1
        if i == 0 or i == 1:
            houses1[i] = money[i1]
            houses2[i] = money[i2]
        elif i == 2:
            houses1[i] = money[i1 - 2] + money[i1]
            houses2[i] = money[i2 - 2] + money[i2]
        else:
            houses1[i] = max(houses1[i - 2] + money[i1], houses1[i - 3] + money[i1])
            houses2[i] = max(houses2[i - 2] + money[i2], houses2[i - 3] + money[i2])
            
    return max(houses1[-1], houses1[-2], houses2[-1], houses2[-2])

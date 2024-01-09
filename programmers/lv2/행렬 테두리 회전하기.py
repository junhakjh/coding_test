import copy

def solution(rows, columns, queries):
    answer = []
    
    arr = [[i + j * columns for i in range(1, columns + 1)] for j in range(rows)]
    
    for query in queries:
        y1, x1, y2, x2 = [num - 1 for num in query]
        prev = arr[y1][x1]
        min_n = prev
        for i in range(x1, x2):
            arr[y1][i + 1], prev = prev, arr[y1][i + 1]
            min_n = min(min_n, prev)
        for i in range(y1, y2):
            arr[i + 1][x2], prev = prev, arr[i + 1][x2]
            min_n = min(min_n, prev)
        for i in range(x2, x1, -1):
            arr[y2][i - 1], prev = prev, arr[y2][i - 1]
            min_n = min(min_n, prev)
        for i in range(y2, y1, -1):
            arr[i - 1][x1], prev = prev, arr[i - 1][x1]
            min_n = min(min_n, prev)
        
        answer.append(min_n)
    
    return answer

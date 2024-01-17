def solution(x, y, n):
    arr = [0 for _ in range(y + 1)]
    arr[x] = 1
    for i, num in enumerate(arr):
        if num == 0: continue
        if i + n <= y: 
            if arr[i + n] == 0:
                arr[i + n] = num + 1
            else: 
                arr[i + n] = min(arr[i + n], num + 1)
        if i * 2 <= y:
            if arr[i * 2] == 0:
                arr[i * 2] = num + 1
            else:
                arr[i * 2] = min(arr[i * 2], num + 1)
        if i * 3 <= y:
            if arr[i * 3] == 0:
                arr[i * 3] = num + 1
            else:
                arr[i * 3] = min(arr[i * 3], num + 1)
    
    return arr[y] - 1

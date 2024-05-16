for T in range(10):
    answer = 0
    n = int(input())
 
    arr = list(map(int, input().split()))
    for i in range(n):
        cur_h = arr[i]
        ll, l, r, rr = i - 2, i - 1, i + 1, i + 2
        max_h = 0
        if ll >= 0:
            max_h = max(max_h, arr[ll])
        if l >= 0:
            max_h = max(max_h, arr[l])
        if r < n:
            max_h = max(max_h, arr[r])
        if rr < n:
            max_h = max(max_h, arr[rr])
 
        answer += max(0, cur_h - max_h)
 
    print("#{} {}".format(T + 1, answer))

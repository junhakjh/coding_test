def solution(n, tops):
    divider = 10007
    a = [0 for _ in range(n)]
    b = [0 for _ in range(n)]
    start = 3 if tops[0] == 0 else 4
    a[0] = 1
    b[0] = start - 1
    
    i = 1
    while i < n:
        a[i] = a[i - 1] + b[i - 1]
        if tops[i] == 0:
            b[i] = a[i - 1] * 1 + b[i - 1] * 2
        else:
            b[i] = a[i - 1] * 2 + b[i - 1] * 3
        a[i], b[i] = a[i] % divider, b[i] % divider
        i += 1
    
    return (a[-1] + b[-1]) % divider

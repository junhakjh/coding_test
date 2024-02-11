from collections import deque

def solution(N, number):
    dp, n, num_set, q = [9 for _ in range(32001)], 0, set([N]), deque([])
    for i in range(5):
        n = n * 10 + N
        if n <= 32000:
            dp[n] = i + 1
            num_set.add(n)
            q.append(n)
            
    while q:
        i = q.popleft()
        num = dp[abs(i)]
        if num > 7: continue
        arr = set()
        for n in num_set:
            if num + dp[n] > 8: continue
            k = i + n
            if 0 < k <= 32000:
                if dp[k] > num + dp[n]:
                    dp[k] = num + dp[n]
                    arr.add(k)
                    q.append(k)
                    q.append(-k)
            k = i * n
            if 0 < k <= 32000:
                if dp[k] > num + dp[n]:
                    dp[k] = num + dp[n]
                    arr.add(k)
                    q.append(k)
                    q.append(-k)
            k = int(i / n)
            if 0 < k <= 32000:
                if dp[k] > num + dp[n]:
                    dp[k] = num + dp[n]
                    arr.add(k)
                    q.append(k)
                    q.append(-k)
        num_set = set(list(num_set) + list(arr))
        
    return -1 if dp[number] > 8 else dp[number]

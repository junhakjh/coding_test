T = int(input())

for tc in range(1, T + 1):
    n, m = map(int, input().split())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    if n > m:
        a, b = b, a

    answer = 0

    for i in range(abs(m - n) + 1):
        cur_sum = 0
        for j in range(max(n, m)):
            if j - i < 0 or j >= min(n, m) + i:
                continue
            else:
                cur_sum += a[j - i] * b[j]
        answer = max(answer, cur_sum)

    print(f"#{tc} {answer}")

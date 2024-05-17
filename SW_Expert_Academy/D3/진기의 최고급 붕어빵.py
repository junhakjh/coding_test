T = int(input())

for test_case in range(1, T + 1):
    answer = "Possible"

    n, m, k = list(map(int, input().split()))
    times = sorted(list(map(int, input().split())))
    prev_time, remain = 0, 0
    for time in times:
        if remain < 0:
            answer = "Impossible"
            break
        remain += (time // m - prev_time // m) * k
        remain -= 1
        prev_time = time
    if remain < 0: answer = "Impossible"

    print("#{} {}".format(test_case, answer))

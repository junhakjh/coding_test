T = int(input())

for test_case in range(1, T + 1):
    n, k = list(map(int, input().split()))
    arr = list(map(int, input().split()))
    dp = [0] * (n * 100 + 1)

    for num in arr:
        to_add = [(num, 1)]
        for i, item in enumerate(dp):
            if item != 0 and i + num < len(dp):
                to_add.append((i + num, item))
        for i, item in to_add:
            dp[i] += item

    print("#{} {}".format(test_case, dp[k]))

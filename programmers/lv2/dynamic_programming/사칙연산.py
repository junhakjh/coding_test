def solution(arr):
    answer, digits, operations = 0, [], []
    for item in arr:
        if item.isdigit():
            digits.append(int(item))
        else:
            operations.append(item)
    n = len(digits)
    max_dp, min_dp = [[(-1) * float('inf') for _ in range(n)] for _ in range(n)], [[float('inf') for _ in range(n)] for _ in range(n)]
    
    for step in range(n):
        for i in range(n - step):
            if step == 0:
                max_dp[i][i] = digits[i]
                min_dp[i][i] = digits[i]
                continue
            j = i + step
            for k in range(i, j):
                if operations[k] == '+':
                    max_dp[i][j] = max(max_dp[i][j], max_dp[i][k] + max_dp[k + 1][j])
                    min_dp[i][j] = min(min_dp[i][j], min_dp[i][k] + min_dp[k + 1][j])
                else:
                    max_dp[i][j] = max(max_dp[i][j], max_dp[i][k] - min_dp[k + 1][j])
                    min_dp[i][j] = min(min_dp[i][j], min_dp[i][k] - max_dp[k + 1][j])
    
    return max_dp[0][n - 1]

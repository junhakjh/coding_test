class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        if amount == 0:
            return 0
        coins.sort()
        dp = [0 for _ in range(amount + 1)]
        for coin in coins:
            if not coin > amount:
                dp[coin] = 1
        for i in range(amount + 1):
            if dp[i] == 0:
                continue
            for coin in coins:
                if i + coin <= amount:
                    if dp[i + coin] == 0:
                        dp[i + coin] = dp[i] + 1
                    else:
                        dp[i + coin] = min(dp[i + coin], dp[i] + 1)
        if dp[-1] == 0:
            return -1
        else:
            return dp[-1]

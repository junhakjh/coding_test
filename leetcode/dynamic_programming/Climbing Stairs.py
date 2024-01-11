class Solution:
    def climbStairs(self, n: int) -> int:
        ways = [1, 2]
        for i in range(2, n):
            ways.append(ways[i - 2] + ways[i - 1])
        return ways[n - 1]

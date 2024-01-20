class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        answer = 1
        for i in range(max(m - 1, n - 1) + 1, m + n - 1):
            answer = answer * i / (i - max(m - 1, n - 1))
        return int(answer)

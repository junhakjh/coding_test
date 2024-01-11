class Solution:
    def hammingWeight(self, n: int) -> int:
        answer = 0
        while n >= 1:
            if n % 2 == 1:
                answer += 1
            n = n >> 1
        return answer

from itertools import combinations

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        left_min = prices[0]
        answer = 0

        for price in prices:
            answer = max(answer, price - left_min)
            left_min = min(left_min, price)

        return answer

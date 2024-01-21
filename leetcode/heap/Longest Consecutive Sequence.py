import heapq

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if not nums:
            return 0
        nums = list(set(nums))
        heapq.heapify(nums)
        prev, num, answer = heapq.heappop(nums), 1, 0
        while nums:
            cur = heapq.heappop(nums)
            if cur == prev + 1:
                num += 1
            else:
                answer = max(answer, num)
                num = 1
            prev = cur
        
        return max(answer, num)

import heapq

class Solution:
    def maxArea(self, height: List[int]) -> int:
        # heap = height[:]
        # heapq.heapify(heap)
        # heapq.heappop()
        # max_height = heapq.heappop()
        answer = 0
        l, r = 0, len(height) - 1
        while l < r:
            answer = max(answer, (r - l) * min(height[l], height[r]))
            if height[l] < height[r]:
                l += 1
            else:
                r -= 1
        return answer

class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if not nums: return [-1, -1]
        l, r = 0, len(nums) - 1
        l_answer = r_answer = 0
        while l < r:
            m = (l + r) // 2
            if nums[m] < target:
                l = m + 1
            else:
                r = m
        l_answer, r = l, len(nums) - 1
        while l < r:
            m = (l + r) // 2 + 1
            if nums[m] <= target:
                l = m
            else:
                r = m - 1
        r_answer = r
        
        return [l_answer, r_answer] if nums[l_answer] == target else [-1, -1]

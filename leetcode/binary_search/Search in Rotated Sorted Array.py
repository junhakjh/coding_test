class Solution:
    def search(self, nums: List[int], target: int) -> int:
        l, r = 0, len(nums) - 1
        while l < r:
            m = (l + r) // 2
            if nums[l] == target:
                return l
            if nums[m] == target:
                return m
            if nums[r] == target:
                return r
            if nums[r] < target < nums[m] or target < nums[m] < nums[r] or nums[m] < nums[r] < target:
                r = m - 1
            else:
                l = m + 1
        if nums[l] == target:
            return l
        else: 
            return -1

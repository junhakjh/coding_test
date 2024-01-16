class Solution:
    def rob(self, nums: List[int]) -> int:
        for i, num in enumerate(nums):
            if i - 2 >= 0:
                nums[i] = max(nums[i], num + nums[i - 2])
            if i - 3 >= 0:
                nums[i] = max(nums[i], num + nums[i - 3])
        return max(nums)

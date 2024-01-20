class Solution:
    def canJump(self, nums: List[int]) -> bool:
        last_i = len(nums) - 1
        for i in range(len(nums) - 2, -1, -1):
            if nums[i] >= last_i - i:
                last_i = i
        return True if last_i == 0 else False

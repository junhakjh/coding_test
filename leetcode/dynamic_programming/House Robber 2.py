import copy

class Solution:
    def rob(self, nums: List[int]) -> int:
        if len(nums) < 3:
            return max(nums)
        nums_cp = copy.deepcopy(nums[1:])
        nums = nums[:-1]
        for index, _ in enumerate(nums_cp[2:]):
            i = index + 2
            if i - 3 >= 0:
                nums_cp[i] = max(nums_cp[i - 3] + nums_cp[i], nums_cp[i - 2] + nums_cp[i])
                nums[i] = max(nums[i - 3] + nums[i], nums[i - 2] + nums[i])
            else:
                nums_cp[i] = nums_cp[i - 2] + nums_cp[i]
                nums[i] = nums[i - 2] + nums[i]
        return max(nums[-1], nums[-2], nums_cp[-1], nums_cp[-2])

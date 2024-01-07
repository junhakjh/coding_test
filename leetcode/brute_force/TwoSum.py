from itertools import combinations

class Solution(object):
    def twoSum(self, nums, target):
        combination = combinations([i for i in range(len(nums))], 2)
        for comb in combination:
            a, b = comb[0], comb[1]
            if nums[a] + nums[b] == target:
                return [a, b]

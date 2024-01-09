class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        sum_max, sum_cur = (-1) * pow(10, 4), nums[0]
        for num in nums[1:]:
            if num >= 0:
                if sum_cur <= 0:
                    sum_max = max(sum_max, sum_cur)
                    sum_cur = num
                else:
                    sum_cur += num
            else:
                sum_max = max(sum_max, sum_cur)
                if sum_cur + num < 0:
                    sum_cur = num
                else:
                    sum_cur += num
        
        return max(sum_max, sum_cur)

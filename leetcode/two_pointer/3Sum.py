class Solution(object):
    def threeSum(self, nums):
        answer = []

        nums.sort()

        left, right = 0, 2
        if nums.count(0) >= 3:
            answer.append([0, 0, 0])
        
        prev_left = nums[0] - 1
        max_right_i = len(nums) - 1
        for left_i, left in enumerate(nums):
            if left >= 0:
                break
            if prev_left == left:
                continue
            mid_i, right_i = left_i + 1, max_right_i
            prev_mid, prev_right = left - 1, left - 1
            while mid_i < right_i:
                mid, right = nums[mid_i], nums[right_i]
                if right <= 0:
                    break
                if prev_mid == mid and prev_right == right:
                    if num_s > 0:
                        right_i -= 1
                    else:
                        mid_i += 1
                    continue
                num_s = left + mid + right
                if num_s == 0:
                    answer.append([left, mid, right])
                    mid_i += 1
                else:
                    if num_s > 0:
                        right_i -= 1
                    else:
                        mid_i += 1
                prev_mid, prev_right = mid, right
            prev_left = left
        
        return answer

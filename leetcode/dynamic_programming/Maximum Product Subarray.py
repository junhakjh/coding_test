from collections import deque

class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        answer = nums[0]
        cur_prod = nums[0]
        negatives = deque([])

        negative = positive = 0
        for num in nums:
            if num < 0:
                negative += 1
            elif num == 0:
                negatives.append([positive, negative])
                negative = positive = 0
            else:
                positive += 1
        negatives.append([positive, negative])
        
        i = 0
        while len(negatives) > 0 and i < len(nums):
            item = negatives.popleft()
            p_num, n_num = item[0], item[1]
            cur_n = n_num
            if p_num + n_num == 0 and nums[i] == 0:
                i += 1
                continue
            if p_num + n_num == 1:
                if i + 1 < len(nums):
                    answer = max(answer, nums[i], nums[i + 1])
                else:
                    answer = max(answer, nums[i])
                i += 2
                continue
            l = r = 1
            l_check, r_check = True, False
            while i < len(nums):
                num = nums[i]
                if num == 0:
                    answer = max(answer, l, r, 0)
                    i += 1
                    break
                if n_num % 2 == 0:
                    l = l * num
                    r = r * num
                    i += 1
                    continue
                if cur_n == 1 and num < 0:
                    l_check = False
                if l_check:
                    l = l * num
                if r_check:
                    r = r * num
                if cur_n == n_num and num < 0:
                    r_check = True
                if num < 0:
                    cur_n -= 1
                i += 1
            answer = max(answer, l, r)

        return answer

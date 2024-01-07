class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        answer = []

        standard = 1
        zero_count = 0

        for i, num in enumerate(nums):
            if num == 0:
                if zero_count == 0:
                    zero_count += 1
                else:
                    return [0] * len(nums)
            else:
                standard *= num
        
        for num in nums:
            if num == 0:
                answer.append(standard)
            else:
                if zero_count > 0:
                    answer.append(0)
                else:
                    answer.append(int(standard / num))
        
        return answer

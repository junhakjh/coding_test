from bisect import bisect_left

class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        ''' Solution

        subseq = []
        for n in nums:
            index = bisect.bisect_left(subseq, n)
            if index >= len(subseq):
                subseq.append(n)
            else:
                subseq[index] = n
        return len(subseq)

        '''
        answer, arr = 1, [1 for _ in range(len(nums))]
        for i, num in enumerate(nums):
            if i == 0:
                continue
            for j, item in enumerate(nums[i - 1::-1]):
                if j >= len(arr):
                    break
                if item < num:
                    arr[i] = max(arr[i], (arr[i - 1 - j] + 1))
                    answer = max(answer, arr[i - 1 - j] + 1)

        return answer

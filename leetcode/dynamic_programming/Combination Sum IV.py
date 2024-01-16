class Solution:
    def combinationSum4(self, nums: List[int], target: int) -> int:
        arr = [0 for _ in range(target + 1)]
        for i in nums:
            if i <= target:
                arr[i] = 1
        for i, cur_num in enumerate(arr):
            if cur_num == 0:
                continue
            for num in nums:
                if i + num <= target:
                    arr[i + num] += cur_num
        return arr[-1]

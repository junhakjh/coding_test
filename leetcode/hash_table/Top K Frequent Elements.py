from collections import defaultdict

class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        num_dict, answer = defaultdict(int), []
        for num in nums:
            num_dict[num] += 1
        arr = []
        for key in num_dict:
            arr.append((key, num_dict[key]))
        arr.sort(key=lambda x:x[1], reverse=True)

        for i in range(k):
            answer.append(arr[i][0])
        return answer

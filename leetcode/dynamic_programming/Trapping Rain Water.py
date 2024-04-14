class Solution:
    def trap(self, height: List[int]) -> int:
        arr = []
        prev_max = 0
        for h in height:
            prev_max = max(prev_max, h)
            arr.append(prev_max)
        prev_max = 0
        for i in range(len(height) - 1, -1, -1):
            prev_max = max(prev_max, height[i])
            arr[i] = min(arr[i], prev_max)

        answer = 0
        for i, item in enumerate(arr):
            answer += item - height[i]

        return answer

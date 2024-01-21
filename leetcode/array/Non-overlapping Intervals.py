class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        if not intervals: return 0
        intervals.sort(key=lambda x:x[0])
        answer, prev = -1, intervals[0]
        for interval in intervals:
            if interval[1] <= prev[1]: # prev에 interval이 포함될 때
                prev = interval
                answer += 1
            elif interval[0] < prev[1]: # prev에 interval이 걸쳐있을 때
                answer += 1
            else:
                prev = interval
                continue
        return answer

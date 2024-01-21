class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if not intervals: return intervals
        intervals.sort(key=lambda x:x[0])
        answer, prev = [], intervals[0]
        for interval in intervals:
            if interval[0] > prev[1]:
                answer.append(prev)
                prev = interval
            else:
                prev = [prev[0], max(prev[1], interval[1])]
        return answer + [prev]

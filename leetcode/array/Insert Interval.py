class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        start, end = newInterval
        start_i, end_i, start_n, end_n = 0, len(intervals) - 1, start, end
        start_check, end_check = False, False
        for i, interval in enumerate(intervals):
            if not start_check:
                if interval[1] < start:
                    start_i = i + 1
                elif interval[0] <= start <= interval[1]:
                    start_i = i
                    start_n = interval[0]
                    start_check = True
                else:
                    start_i = i
                    start_n = start
                    start_check = True
            if not end_check:
                if end < interval[0]:
                    end_i = i - 1
                    end_n = end
                    end_check = True
                elif interval[0] <= end <= interval[1]:
                    end_i = i
                    end_n = interval[1]
                    end_check = True
                else:
                    end_i = i + 1
        if end_i - start_i < 0:
            intervals.insert(start_i, [start_n, end_n])
            return intervals
        elif start_i >= len(intervals):
            return intervals + [[start_n, end_n]]
        else:
            answer = []
            for i, interval in enumerate(intervals):
                if i < start_i or i > end_i:
                    answer.append(interval)
                elif i == start_i:
                    answer.append([start_n, end_n])
                else:
                    continue
            return answer

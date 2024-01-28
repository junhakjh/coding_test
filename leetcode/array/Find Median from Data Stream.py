class MedianFinder:

    def __init__(self):
        self.arr = []

    def addNum(self, num: int) -> None:
        self.arr.append(num)

    def findMedian(self) -> float:
        self.arr.sort()
        arr_len = len(self.arr)
        if arr_len % 2 == 0:
            return (self.arr[arr_len // 2 - 1] + self.arr[arr_len // 2]) / 2
        else:
            return self.arr[arr_len // 2]


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()

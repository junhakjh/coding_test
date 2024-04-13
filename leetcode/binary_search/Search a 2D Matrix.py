class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        width, height = len(matrix[0]), len(matrix)
        l, r = 0, width * height - 1

        while l < r:
            m = (l + r) // 2
            y, x = m // width, m % width
            if matrix[y][x] == target:
                return True
            
            if matrix[y][x] < target:
                l = m + 1
            else:
                r = m

        y, x = l // width, l % width
        return True if matrix[y][x] == target else False

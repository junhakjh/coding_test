class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        w, h, arr = len(matrix[0]), len(matrix), []
        for y in range(h):
            for x in range(w):
                if matrix[y][x] == 0:
                    arr.append((x, y))
        for item in arr:
            x, y = item
            for ny in range(h):
                matrix[ny][x] = 0
            for nx in range(w):
                matrix[y][nx] = 0
        return matrix

class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        n = len(matrix)
        while n > 0:
            start_i = (len(matrix) - n) // 2
            t, r, b, l = start_i, len(matrix) - 1 - start_i, len(matrix) - 1 - start_i, start_i
            prev_t, prev_r, prev_b, prev_l = [], [], [], []
            for i in range(l, r + 1):
                prev_t.append(matrix[t][i])
            for i in range(t, b + 1):
                prev_r.append(matrix[i][r])
            for i in range(r, l - 1, -1):
                prev_b.append(matrix[b][i])
            for i in range(b, t - 1, -1):
                prev_l.append(matrix[i][l])
            for i in range(l, r + 1):
                cur_i = i - l
                matrix[t][i] = prev_l[cur_i]
            for i in range(t, b + 1):
                cur_i = i - t
                matrix[i][r] = prev_t[cur_i]
            for i in range(r, l - 1, -1):
                cur_i = r - i
                matrix[b][i] = prev_r[cur_i]
            for i in range(b, t - 1, -1):
                cur_i = b - i
                matrix[i][l] = prev_b[cur_i]
            n -= 2

        return matrix

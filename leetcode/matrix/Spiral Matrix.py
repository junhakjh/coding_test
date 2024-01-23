class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
        direction, x, y, answer = 0, 0, 0, []
        while len(answer) < len(matrix) * len(matrix[0]):
            answer.append(matrix[y][x])
            matrix[y][x] = 101
            i = direction % 4
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= len(matrix[0]) or ny < 0 or ny >= len(matrix):
                direction += 1
                i = direction % 4
                nx, ny = x + dx[i], y + dy[i]
            elif matrix[ny][nx] == 101:
                direction += 1
                i = direction % 4
                nx, ny = x + dx[i], y + dy[i]
            x, y = nx, ny
        return answer

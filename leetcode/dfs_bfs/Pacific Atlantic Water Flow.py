class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        po, ao, w, h = [], [], len(heights[0]), len(heights)
        dx, dy = [1, 0, -1, 0], [0, 1, 0, -1]
        for x in range(w):
            po.append([x, 0])
            ao.append([x, h - 1])
        for y in range(h):
            if not [0, y] in po:
                po.append([0, y])
            if not [w - 1, y] in ao:
                ao.append([w - 1, y])
        po_visited, ao_visited = set(), set()
        
        def dfs(x, y, visited):
            visited.add((y, x))
            for i in range(4):
                nx, ny = x + dx[i], y + dy[i]
                if nx < 0 or nx >= w or ny < 0 or ny >= h:
                    continue
                if heights[ny][nx] >= heights[y][x] and not (ny, nx) in visited:
                    dfs(nx, ny, visited)
        
        for item in po:
            dfs(item[0], item[1], po_visited)
        for item in ao:
            dfs(item[0], item[1], ao_visited)
        return po_visited & ao_visited

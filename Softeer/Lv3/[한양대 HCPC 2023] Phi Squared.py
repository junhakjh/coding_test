from collections import deque

n = int(input())
sizes = list(map(int, input().split()))

def solution():
    if n == 1:
        return 0
    
    q = deque([])
    for i, size in enumerate(sizes):
        q.append((i, size))

    while len(q) > 1:
        stack = []
        while q:
            cur_i, cur_size = q.popleft()
            i, size = cur_i, cur_size
            if stack:
                prev_i, prev_size = stack.pop()
                if cur_size >= prev_size:
                    i, size = cur_i, prev_size + size
                else:
                    stack.append((prev_i, prev_size))
            if q:
                next_i, next_size = q.popleft()
                if cur_size >= next_size:
                    i, size = cur_i, next_size + size
                else:
                    q.appendleft((next_i, next_size))
            stack.append((i, size))
        q.extend(stack)
        
    return q[0][0]

print(sum(sizes))
print(solution() + 1)

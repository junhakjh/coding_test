import sys
from collections import defaultdict, deque

n, m = map(int, sys.stdin.readline().strip().split())
string = sys.stdin.readline().strip()
node_dict = defaultdict(set)
for _ in range(n - 1):
    command = sys.stdin.readline().strip().split()
    node_dict[int(command[0])].add((int(command[1]), command[2]))
    node_dict[int(command[1])].add((int(command[0]), command[2]))

def solution():
    answer = 0
    q = deque([(1, [0] * (m + 1), '', set([1]))])
    while q:
        node, dp, prev_str, visited = q.popleft()
        for n_node, char in node_dict[node]:
            if n_node in visited:
                continue
            visited.add(n_node)
            new_dp = [0] * (m + 1)
            for i in range(1, m + 1):
                if char == string[i - 1]:
                    new_dp[i] = dp[i - 1] + 1
                else:
                    new_dp[i] = max(dp[i], new_dp[i - 1])
                answer = max(answer, new_dp[i])
            q.append((n_node, new_dp, prev_str + char, visited))
    
    return answer

print(solution())

from collections import defaultdict

T = int(input())

for test_case in range(1, T + 1):
    answer, node_dict = 1, defaultdict(list)
    n, m = list(map(int, input().split()))

    for _ in range(m):
        x, y = list(map(int, input().split()))
        node_dict[x].append(y)
        node_dict[y].append(x)

    def dfs(node, depth, visited):
        global answer
        answer = max(answer, depth)
        for n_node in node_dict[node]:
            if n_node not in visited:
                dfs(n_node, depth + 1, visited + [n_node])

    for cur_node in range(1, n + 1):
        dfs(cur_node, 1, [cur_node])

    print("#{} {}".format(test_case, answer))

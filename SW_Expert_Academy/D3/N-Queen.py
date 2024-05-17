T = int(input())

for test_case in range(1, T + 1):
    N, answer = int(input()), 0

    def dfs(col, row_arr):
        global answer
        if col == N:
            answer += 1
            return
        for y in range(N):
            if y in row_arr:
                continue
            check = False
            for x in range(col):
                diff = col - x
                if row_arr[x] + diff == y or row_arr[x] - diff == y:
                    check = True
                    break
            if not check:
                dfs(col + 1, row_arr + [y])

    dfs(0, [])

    print("#{} {}".format(test_case, answer))

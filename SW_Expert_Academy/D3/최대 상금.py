from collections import deque, Counter

T = int(input())

for test_case in range(1, T + 1):
    answer = 0
    num, change = list(input().split())
    num_arr, change = list(map(int, num)), int(change)
    sorted_arr = sorted(num_arr, reverse=True)
    q = deque([(0, 0, num_arr)])

    while q:
        level, cur_i, arr = q.popleft()

        if cur_i == len(num) - 1 or level == change:
            counts = Counter(arr)
            max_cnt = 0
            for value in  counts.values():
                max_cnt = max(max_cnt, value)
            if max_cnt > 1:
                answer = max(answer, int("".join(list(map(str, arr)))))
                continue
            if (change - level) % 2 == 1:
                arr[-1], arr[-2] = arr[-2], arr[-1]
            answer = max(answer, int("".join(list(map(str, arr)))))
            continue

        if arr[cur_i] != sorted_arr[cur_i]:
            target_i = []
            for i in range(cur_i + 1, len(num)):
                if arr[i] == sorted_arr[cur_i]:
                    target_i.append(i)
            for i in target_i:
                arr[cur_i], arr[i] = arr[i], arr[cur_i]
                q.append((level + 1, cur_i + 1, arr[:]))
                arr[cur_i], arr[i] = arr[i], arr[cur_i]
        else:
            q.append((level, cur_i + 1, arr[:]))

    print("#{} {}".format(test_case, str(answer)))

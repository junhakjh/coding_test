T = int(input())

for test_case in range(1, T + 1):
    arr = list(map(int, input().split()))
    n_sum = sum(arr)
    n_sum -= max(arr) + min(arr)
    answer = round(n_sum / 8)

    print("#{} {}".format(test_case, answer))

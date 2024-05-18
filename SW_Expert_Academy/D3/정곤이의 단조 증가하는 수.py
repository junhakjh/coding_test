from itertools import combinations

T = int(input())

for test_case in range(1, T + 1):
    answer = -1
    n, nums = int(input()), list(map(int, input().split()))

    def check(num):
        num = list(map(int, str(num)))
        prev = num[0]
        for cn in num:
            if prev > cn:
                return False
            prev = cn
        return True

    for a, b in combinations(nums, 2):
        k = a * b
        if check(k):
            answer = max(answer, k)

    print("#{} {}".format(test_case, answer))

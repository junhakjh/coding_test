T = int(input())

for test_case in range(1, T + 1):
    N, L = list(map(int, input().split()))
    calories = [-1] * (L + 1)
    calories[0] = 0

    for _ in range(N):
        t, k = list(map(int, input().split()))
        toAdd = []
        for i, score in enumerate(calories):
            if i + k > L or score == -1:
                continue
            if calories[i + k] < score + t:
                toAdd.append((i + k, score + t))
        for i, score in toAdd:
            calories[i] = score

    print("#{} {}".format(test_case, max(calories)))

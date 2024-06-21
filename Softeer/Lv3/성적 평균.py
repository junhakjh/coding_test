N, K = map(int, input().split())
scores = list(map(int, input().split()))
for i in range(K):
    a, b = map(int, input().split())
    print(round(sum(scores[a - 1:b]) / (b - a + 1), 2))

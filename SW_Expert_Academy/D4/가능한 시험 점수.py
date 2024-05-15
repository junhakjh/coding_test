T = int(input())
for test_case in range(1, T + 1):
    n = int(input())
    scores = list(map(int, input().split()))
 
    candidates = [0] * (sum(scores) + 1)
    candidates[0] = 1
 
    for score in scores:
        be_added = []
        for i, check in enumerate(candidates):
            if check and i + score < len(candidates):
                if candidates[i + score] == 0:
                    be_added.append(i + score)
        for i in be_added:
            candidates[i] = 1
 
    print("#{} {}".format(test_case, sum(candidates)))

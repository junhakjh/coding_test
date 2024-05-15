for _ in range(10):
    T = int(input())
    ladder = []
    for _ in range(100):
        ladder.append(list(map(int, input().split())))

    row, col = 98, ladder[-1].index(2)
    while row > 0:
        l, r = col - 1, col + 1
        if 0 <= l < 100 and ladder[row][l] == 1:
            ladder[row][col] = 0
            col = l
        elif 0 <= r < 100 and ladder[row][r] == 1:
            ladder[row][col] = 0
            col = r
        else:
            row -= 1

    print("#{} {}".format(T, col))

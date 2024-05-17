for test_case in range(1, 11):
    dump = int(input())
    arr = list(map(int, input().split()))
    answer = float('-inf')
 
    for _ in range(dump):
        max_num, min_num = max(arr), min(arr)
        if max_num == min_num:
            break
        i1, i2 = arr.index(max_num), arr.index(min_num)
        arr[i1] -= 1
        arr[i2] += 1
 
    answer = max(arr) - min(arr)
 
    print("#{} {}".format(test_case, str(answer)))

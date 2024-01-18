def solution(data, col, row_begin, row_end):
    answer, arr = 0, []
    data.sort(key=lambda x:x[0], reverse=True)
    data.sort(key=lambda x:x[col - 1])
    for i in range(row_begin - 1, row_end):
        num_sum = 0
        for num in data[i]:
            num_sum += num % (i + 1)
        arr.append(num_sum)
    for num in arr:
        answer = answer ^ num
    
    return answer

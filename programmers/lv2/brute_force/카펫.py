def solution(brown, yellow):
    answer, cells, multiple = [], brown + yellow, brown // 2 + 2
    for num in range(1, multiple // 2 + 1):
        if num * (multiple - num) == cells:
            answer = [multiple - num, num]
    
    return answer

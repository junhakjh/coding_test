def solution(n, left, right):
    answer = []
    
    num, r = left // n, left % n
    while num * n + r <= right:
        if r == n:
            num += 1
            r = 0
        if r < num:
            answer.append(num + 1)
        else:
            answer.append(r + 1)
        r += 1
    
    return answer

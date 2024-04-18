def solution(e, starts):
    answer = []
    
    divisors = [0] * (e + 1)
    for i in range(1, e):
        if i * i <= e:
            divisors[i * i] += 1
        for j in range(i + 1, e + 1):
            if i * j <= e:
                divisors[i * j] += 2
            else:
                break

    max_list, max_num, max_cnt = [0] * (e + 1), 0, 0
    for i in range(e, min(starts) - 1, -1):
        if divisors[i] >= max_cnt:
            max_num = i
            max_cnt = divisors[i]
        max_list[i] = max_num
        
    answer = [max_list[s] for s in starts]
    
    return answer

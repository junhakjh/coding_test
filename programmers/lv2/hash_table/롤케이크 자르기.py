def solution(topping):
    answer = 0
    
    total, l, t_dict = len(set(topping)), set(), {}
    for item in set(topping):
        t_dict[item] = 0
    for item in topping:
        t_dict[item] += 1
        
    for item in topping:
        l.add(item)
        t_dict[item] -= 1
        if t_dict[item] == 0:
            total -= 1
        if len(l) == total:
            answer += 1
        if len(l) > total:
            break
    
    return answer

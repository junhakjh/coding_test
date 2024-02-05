def solution(n, lost, reserve):
    lost, reserve_set = set(lost), set(reserve)
    for num in reserve:
        if num in lost:
            lost.remove(num)
            reserve_set.remove(num)
            
    for num in reserve_set:
        if num - 1 in lost:
            lost.remove(num - 1)
        elif num + 1 in lost:
            lost.remove(num + 1)
    
    return n - len(lost)

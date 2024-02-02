from collections import defaultdict

def solution(clothes):
    answer, clothes_dict = 1, defaultdict(list)
    
    for item, sort in clothes:
        clothes_dict[sort].append(item)
    for key in clothes_dict:
        answer *= len(clothes_dict[key]) + 1
    
    return answer - 1

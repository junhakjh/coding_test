def solution(phone_book):
    num_set = set()
    for num in sorted(phone_book, key=lambda x:len(x)):
        for i in range(len(num)):
            if num[:i + 1] in num_set: return False
        num_set.add(num)
    
    return True

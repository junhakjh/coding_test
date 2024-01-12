def solution(s):
    answer = []
    
    tuples = arr = []
    num, check = '', False
    for char in s[1:-1]:
        if char == '{':
            arr, check = [], True
            continue
        elif char == '}':
            tuples.append(arr + [int(num)])
            arr, num, check = [], '', False
        elif char == ',':
            if check:
                arr.append(int(num))
            num = ''
        else:
            num += char
    tuples.sort(key=lambda x:len(x))
    
    tup_dict = {}
    for arr in tuples:
        for num in arr:
            if not num in tup_dict:
                tup_dict[num] = num
                answer.append(num)
    
    return answer

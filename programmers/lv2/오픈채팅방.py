def solution(record):
    answer = []
    hash_table = {}
    arr = []
    for info in record:
        info = info.split()
        method, uid = info[0], info[1]
        if method == 'Enter':
            hash_table[uid] = info[2]
            arr.append([uid, 1])
        elif method == 'Leave':
            arr.append([uid, 0])
        else:
            hash_table[uid] = info[2]
    
    for info in arr:
        string = hash_table[info[0]] + '님이 '
        if info[1] == 1:
            string += '들어왔습니다.'
        else:
            string += '나갔습니다.'
        answer.append(string)
    
    return answer

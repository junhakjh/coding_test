def solution(s):
    answer = len(s)
    
    for unit_len in range(1, len(s) // 2 + 1):
        i, unit, unit_num, str_len = 0, s[:unit_len], 0, len(s)
        for i in range(0, len(s), unit_len):
            if i + unit_len > len(s):
                break
            if s[i:i + unit_len] == unit:
                unit_num += 1
            else:
                if unit_num > 1:
                    str_len -= unit_len * unit_num - (len(str(unit_num)) + unit_len)
                unit_num = 1
                unit = s[i:i + unit_len]
        if unit_num > 1:
            str_len -= unit_len * unit_num - (len(str(unit_num)) + unit_len)
        answer = min(answer, str_len)
    
    return answer

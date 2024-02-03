def solution(word):
    answer, alphabet, answer_dict = 0, ['A', 'E', 'I', 'O', 'U'], {'A': 1}
    
    prev, i = 'A', 1
    while prev:
        if len(prev) != 5:
            prev += 'A'
        elif prev[-1] == 'U':
            while prev and prev[-1] == 'U':
                prev = prev[:-1]
            if not prev: break
            temp = prev[:-1]
            prev = temp + alphabet[alphabet.index(prev[-1]) + 1]
        else:
            temp = prev[:-1]
            prev = temp + alphabet[alphabet.index(prev[-1]) + 1]
        i += 1
        answer_dict[prev] = i
    
    return answer_dict[word]

def solution(answers):
    answer = []
    
    a, b, c = [1, 2, 3, 4, 5], [2, 1, 2, 3, 2, 4, 2, 5], [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    a_score = b_score = c_score = 0
    
    for i, ans in enumerate(answers):
        if a[i % len(a)] == ans: a_score += 1
        if b[i % len(b)] == ans: b_score += 1
        if c[i % len(c)] == ans: c_score += 1
    if a_score == max(a_score, b_score, c_score): answer.append(1)
    if b_score == max(a_score, b_score, c_score): answer.append(2)
    if c_score == max(a_score, b_score, c_score): answer.append(3)
    
    return answer

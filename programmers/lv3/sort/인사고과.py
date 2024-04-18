def solution(scores):
    answer, my_a, my_b, my_sum = 1, scores[0][0], scores[0][1], sum(scores[0])
    scores.sort(key=lambda x: x[1])
    scores.sort(key=lambda x: x[0], reverse=True)
    
    max_b = -1
    for score in scores:
        a, b = score
        if a > my_a and b > my_b:
            return -1
        if b >= max_b:
            max_b = b
            if sum(score) > my_sum:
                answer += 1
    
    return answer

def solution(storey):
    answer, cypher = 0, 1
    while storey > 0:
        r = storey % pow(10, cypher)
        if r <= 5 * pow(10, cypher - 1):
            if r == 5 and (storey % pow(10, cypher + 1)) // pow(10, cypher) >= 5:
                answer += 10 - r // pow(10, cypher - 1)
                storey += pow(10, cypher) - r
            else:
                answer += r // pow(10, cypher - 1)
                storey -= r
        else:
            answer += 10 - r // pow(10, cypher - 1)
            storey += pow(10, cypher) - r
        cypher += 1
    
    return answer

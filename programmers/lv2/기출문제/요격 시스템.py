def solution(targets):
    answer = 0
    
    targets.sort(key=lambda x: (x[0], x[1]))
        
    end_point = targets[0][1]
    targets = targets[1:]
    end_point_candidate = 100000000
    for target in targets:
        end_point_candidate = min(end_point_candidate, target[1])
        if target[0] >= end_point or target[0] >= end_point_candidate:
            answer += 1
            end_point = target[1]
            end_point_candidate = 100000000
    
    return answer + 1

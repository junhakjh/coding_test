from collections import deque

def solution(queue1, queue2):
    answer = 0
    
    q_len = len(queue1)
    q1, q2 = deque(queue1), deque(queue2)
    diff = 0
    for i in range(q_len):
        diff += q1[i] - q2[i]
    
    if diff % 2 == 1:
        return -1
    
    while not diff == 0:
        if diff > 0:
            item = q1.popleft()
            q2.append(item)
            diff -= item * 2
        else:
            item = q2.popleft()
            q1.append(item)
            diff += item * 2
        answer += 1
        if answer >= q_len * 4:
            answer = -1
            break
    
    return answer

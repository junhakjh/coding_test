import heapq

def solution(plans):
    answer = []
    
    hq = []
    for plan in plans:
        t, m = map(int, plan[1].split(':'))
        hq.append((60 * t + m, int(plan[2]), plan[0]))
        
    heapq.heapify(hq)
    todo_stack, prev_todo = [], ()
    while hq:
        start_t, t, todo = heapq.heappop(hq)
        if not prev_todo:
            prev_todo = (start_t, t, todo)
            continue
        if prev_todo[0] + prev_todo[1] > start_t:
            todo_stack.append((prev_todo[0] + prev_todo[1] - start_t, prev_todo[2]))
        else:
            answer.append(prev_todo[2])
            cur_t = prev_todo[0] + prev_todo[1]
            while todo_stack:
                td_t, td = todo_stack.pop()
                if cur_t + td_t > start_t:
                    todo_stack.append((cur_t + td_t - start_t, td))
                    break
                else:
                    answer.append(td)
                    cur_t += td_t
        prev_todo = (start_t, t, todo)
    
    answer.append(prev_todo[2])
    while todo_stack:
        answer.append(todo_stack.pop()[1])
    
    return answer

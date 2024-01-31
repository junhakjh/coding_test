from collections import defaultdict

def solution(tickets):
    answer, ticket_dict = ['ICN'], defaultdict(list)
    for ticket in tickets:
        ticket_dict[ticket[0]].append(ticket[1])
    for key in ticket_dict:
        ticket_dict[key] = sorted(ticket_dict[key])
    
    check = False
    def dfs(prev):
        nonlocal ticket_dict, answer, check
        if check:
            return
        if len(ticket_dict[prev]) == 0:
            if len(answer) == len(tickets) + 1:
                check = True
            return
        for i, arrival in enumerate(ticket_dict[prev]):
            temp = ticket_dict[prev].pop(i)
            answer.append(temp)
            dfs(temp)
            if check:
                return
            answer.pop()
            ticket_dict[prev].insert(i, temp)
    
    dfs('ICN')
    
    return answer

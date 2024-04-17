from collections import defaultdict, deque

def solution(n, results):
    answer, winner_dict, loser_dict = 0, defaultdict(set), defaultdict(set)
    
    for winner, loser in results:
        winner_dict[winner].add(loser)
        loser_dict[loser].add(winner)
        
    for winner in list(winner_dict):
        losers = winner_dict[winner]
        q = deque(list(losers))
        more_losers, visited = [], set()
        while q:
            loser = q.popleft()
            if loser in visited:
                continue
            visited.add(loser)
            for more_loser in winner_dict[loser]:
                more_losers.append(more_loser)
                q.append(more_loser)
        winner_dict[winner].update(more_losers)
    for loser in list(loser_dict):
        winners = loser_dict[loser]
        q = deque(list(winners))
        more_winners, visited = [], set()
        while q:
            winner = q.popleft()
            if winner in visited:
                continue
            visited.add(winner)
            for more_winner in loser_dict[winner]:
                more_winners.append(more_winner)
                q.append(more_winner)
        loser_dict[loser].update(more_winners)
            
    for winner in winner_dict:
        if len(winner_dict[winner]) + len(loser_dict[winner]) == n - 1:
            answer += 1
    
    return answer

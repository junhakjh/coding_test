from collections import defaultdict

def solution(participant, completion):
    participants = defaultdict(int)
    
    for p in participant:
        participants[p] += 1
    for c in completion:
        participants[c] -= 1
        if participants[c] == 0:
            del participants[c]

    return list(participants.keys())[0]

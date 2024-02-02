from collections import defaultdict
import heapq

def solution(genres, plays):
    answer, genre_dict, num_dict = [], defaultdict(list), defaultdict(int)
    
    for i in range(len(genres)):
        genre_dict[genres[i]].append((i, plays[i]))
        num_dict[genres[i]] += plays[i]
    
    h = []
    for key in num_dict:
        h.append(((-1) * num_dict[key], key))
    heapq.heapify(h)
    while h:
        _, genre = heapq.heappop(h)
        genre_dict[genre].sort(key=lambda x:x[0])
        genre_dict[genre].sort(key=lambda x:x[1], reverse=True)
        answer.append(genre_dict[genre][0][0])
        if len(genre_dict[genre]) > 1:
            answer.append(genre_dict[genre][1][0])
    
    return answer

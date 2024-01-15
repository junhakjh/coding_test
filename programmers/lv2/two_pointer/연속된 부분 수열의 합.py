def solution(sequence, k):
    answer, l, r = [(-1) * float('inf'), float('inf')], 0, 0
    k_sum = sequence[0]
    
    while l <= r and r < len(sequence):
        if k_sum < k:
            r += 1
            if not r == len(sequence):
                k_sum += sequence[r]
        elif k_sum > k:
            k_sum -= sequence[l]
            l += 1
        else:
            if r - l < answer[1] - answer[0]:
                answer = [l, r]
            k_sum -= sequence[l]
            l += 1
    
    return answer

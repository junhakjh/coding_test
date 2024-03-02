from collections import deque

def solution(numbers):
    answer = []
    
    for index, number in enumerate(numbers):
        bin_n = bin(number)[2:]
        for i in range(55):
            if pow(2, i) > len(bin_n):
                bin_n = bin_n.rjust(pow(2, i) - 1, '0')
                break
        q = deque([bin_n])
        while q:
            n = q.popleft()
            mid = len(n) // 2
                    
            front, back = n[:mid], n[mid + 1:]
            
            if n[mid] == '0':
                if '1' in front or '1' in back:
                    answer.append(0)
                    break                  
                    
            if len(front) > 1:            
                q.append(front)
            
            if len(back) > 1:
                q.append(back)
        
        if len(answer) == index:
            answer.append(1)
    
    return answer

def solution(sizes):
    x = y = 0
    
    for size in sizes:
        x = max(size[0], size[1], x)
        y = max(min(size[0], size[1]), y)
    
    return x * y

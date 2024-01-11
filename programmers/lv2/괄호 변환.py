from collections import deque

def solution(p):
    return dfs(p)

def dfs(string):
    if is_right(string):
        return string
    u, v = division(string)
    if is_right(u):
        return u + dfs(v)
    else:
        new_str = '(' + dfs(v) + ')'
        for char in u[1:-1]:
            if char == '(':
                new_str += ')'
            else:
                new_str += '('
        return new_str
        
def is_right(string):
    queue = deque([])
    for char in string:
        if char == '(':
            queue.append('(')
        else:
            if len(queue) == 0:
                return False
            queue.pop()
    if len(queue) == 0:
        return True
    else:
        return False

def division(string):
    u, v = '', ''
    l, r, check = 0, 0, False    
    for char in string:
        if l > 0 and r > 0 and l == r and not check:
            check = True
        if not check:
            u += char
        else:
            v += char
        if char == '(':
            l += 1
        else:
            r += 1
    return (u, v)

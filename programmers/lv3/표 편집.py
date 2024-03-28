def solution(n, k, cmd):
    answer, n_dict = ['O'] * n, {}
    
    for i in range(n):
        n_dict[i] = [i - 1, i + 1]
    n_dict[0][0], n_dict[n - 1][1] = None, None
    
    d_stack = []
    
    for c in cmd:
        c = c.split()
        if c[0] == 'C':
            p, n = n_dict[k]
            d_stack.append([k, p, n])
            if p != None:
                n_dict[p][1] = n
            if n != None:
                n_dict[n][0] = p
                k = n
            else:
                k = p
        elif c[0] == 'Z':
            i, p, n = d_stack.pop()
            if p != None:
                n_dict[p][1] = i
            if n != None:
                n_dict[n][0] = i
        elif c[0] == 'U':
            for _ in range(int(c[1])):
                k = n_dict[k][0]
        else:
            for _ in range(int(c[1])):
                k = n_dict[k][1]

    while d_stack:
        answer[d_stack.pop()[0]] = 'X'
    
    return ''.join(answer)

n = int(input())
answer = []
for _ in range(n):
    a, b = input().split()
    i = a.find('x')
    if i == -1:
        i = a.find('X')
    answer.append(b[i].upper())

print(''.join(answer))

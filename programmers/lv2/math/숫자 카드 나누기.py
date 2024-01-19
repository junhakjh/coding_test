def solution(arrayA, arrayB):
    answer = 0
    
    if len(arrayA) == 1:
        return 0 if arrayA[0] == arrayB[0] else max(arrayA[0], arrayB[0])
    
    a_gcd, b_gcd = gcd(arrayA[0], arrayA[1]), gcd(arrayB[0], arrayB[1])
    for num in arrayA:
        a_gcd = gcd(a_gcd, num)
    for num in arrayB:
        b_gcd = gcd(b_gcd, num)
    check = False
    for num in arrayB:
        if num % a_gcd == 0:
            check = True
    if not check:
        answer = max(answer, a_gcd)
    check = False
    for num in arrayA:
        if num % b_gcd == 0:
            check = True
    if not check:
        answer = max(answer, b_gcd)
    
    return answer

def gcd(a, b):
    r = a % b
    while r > 0:
        a = b
        b = r
        r = a % b
    return b

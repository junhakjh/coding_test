T = int(input())
 
for test_case in range(1, T + 1):
    days = int(input())
    prices = list(map(int, input().split()))
    max_price = answer = 0
 
    for price in prices[::-1]:
        max_price = max(max_price, price)
        answer += max_price - price
 
    print("#{} {}".format(test_case, str(answer)))

from itertools import product

def solution(users, emoticons):
    answer = []
    
    elements = [40, 30, 20, 10]
    r = len(emoticons)
    result = list(product(elements, repeat=r))
    
    for percentages in result:
        buyer, sales = 0, 0
        for user in users:
            disc_th, price_th = user[0], user[1]
            price_sum = 0
            for i, emoticon in enumerate(emoticons):
                percentage = percentages[i]
                if percentage >= disc_th:
                    price_sum += emoticon * (100 - percentage) / 100
                if price_sum >= price_th:
                    buyer += 1
                    price_sum = 0
                    break
            sales += price_sum
        answer.append([buyer, sales])
    
    answer.sort(key=lambda x:x[1], reverse=True)
    answer.sort(key=lambda x:x[0], reverse=True)
    
    answer = answer[0]
    
    return answer

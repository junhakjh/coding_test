import copy
from itertools import permutations

def solution(input_expression):
    answer = 0
    operators = ['+', '-', '*']
    origin_expression = []
    number = ''
    for char in input_expression:
        if char in operators:
            origin_expression.append(int(number))
            origin_expression.append(char)
            number = ''
        else:
            number += char
    origin_expression.append(int(number))
    
    for permutation in permutations([0, 1, 2]):
        expression = copy.deepcopy(origin_expression)
        for index in permutation:
            operator = operators[index]
            i = 0
            ready = False
            num = 0
            while i < len(expression):
                item = expression[i]
                if item in operators:
                    if item == operator:
                        ready = True
                    i += 1
                    continue
                if not ready:
                    num = item
                    i += 1
                else:
                    if operator == '+':
                        num += item
                    elif operator == '-':
                        num -= item
                    else:
                        num *= item
                    if i == len(expression) - 1:
                        expression = expression[:i - 2] + [num]
                    else:
                        expression = expression[:i - 2] + [num] + expression[i + 1:]
                    i -= 2
                    ready = False
        answer = max(answer, abs(expression[0]))
    
    return answer

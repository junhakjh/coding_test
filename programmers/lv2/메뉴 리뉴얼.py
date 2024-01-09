from itertools import combinations

def solution(orders, courses):
    answer = []
    course_dict = {}
    
    for i, order in enumerate(orders):
        orders[i] = sorted(list(order))
        
    for index, order in enumerate(orders[:-1]):
        for course_len in courses:
            if len(order) < course_len:
                continue
            for comb in combinations([num for num in range(len(order))], course_len):
                temp = []
                for i in comb:
                    temp.append(order[i])
                num = 1
                for word in orders[index + 1:]:
                    length = 0
                    for char in temp:
                        if char in word:
                            length += 1
                    if length == len(temp):
                        num += 1
                if course_len in course_dict:
                    if course_dict[course_len]['num'] < num:
                        course_dict[course_len] = {'num': num, 'courses': [''.join(temp)]}
                    elif course_dict[course_len]['num'] == num:
                        course_dict[course_len]['courses'].append(''.join(temp))
                else:
                    course_dict[course_len] = {'num': num, 'courses': [''.join(temp)]}
    
    for item in course_dict:
        if course_dict[item]['num'] > 1:
            for course in course_dict[item]['courses']:
                answer.append(course)
    
    return sorted(answer)

from collections import defaultdict

def solution(commands):
    answer, table = [], [['EMPTY' for _ in range(51)] for _ in range(51)]
    value_dict, merge_list = defaultdict(set), []
    
    for command in commands:
        command = command.split()
        if command[0] == 'UPDATE':
            if len(command) == 4:
                r, c, value = int(command[1]), int(command[2]), command[3]
                update_i = -1
                for i, arr in enumerate(merge_list):
                    if (r, c) in arr:
                        update_i = i
                        break
                if update_i == -1:
                    if table[r - 1][c - 1] != 'EMPTY':
                        value_dict[table[r - 1][c - 1]].remove((r, c))
                    value_dict[value].add((r, c))
                    table[r - 1][c - 1] = value
                else:
                    for r, c in merge_list[update_i]:
                        if table[r - 1][c - 1] != 'EMPTY':
                            value_dict[table[r - 1][c - 1]].remove((r, c))
                        value_dict[value].add((r, c))
                        table[r - 1][c - 1] = value
                
            else:
                value1, value2 = command[1:]
                if value1 == value2:
                    continue
                for r, c in value_dict[value1]:
                    table[r - 1][c - 1] = value2
                    value_dict[value2].add((r, c))
                value_dict[value1] = set()
                
        elif command[0] == 'MERGE':
            r1, c1, r2, c2 = int(command[1]), int(command[2]), int(command[3]), int(command[4])
            if r1 == r2 and c1 == c2: continue
            
            i1, i2 = -1, -1
            for i, arr in enumerate(merge_list):
                if (r1, c1) in arr:
                    i1 = i
                if (r2, c2) in arr:
                    i2 = i
                    
            if i1 == i2 and i1 != -1:
                continue
            
            check = False
            if table[r1 - 1][c1 - 1] == 'EMPTY' and table[r2 - 1][c2 - 1] != 'EMPTY':
                check = True
                value = table[r2 - 1][c2 - 1]
            else:
                value = table[r1 - 1][c1 - 1]
            
            # 바뀔놈이 merge되어있는 cell
            if i2 != -1:
                # 바뀔놈들 바꾸기
                if check:
                    if i1 == -1:
                        if table[r1 - 1][c1 - 1] != 'EMPTY':
                            value_dict[table[r1 - 1][c1 - 1]].remove((r1, c1))
                        value_dict[value].add((r1, c1))
                        table[r1 - 1][c1 - 1] = value
                    else:
                        for r, c in merge_list[i1]:
                            if table[r - 1][c - 1] != 'EMPTY':
                                value_dict[table[r - 1][c - 1]].remove((r, c))
                            value_dict[value].add((r, c))
                            table[r - 1][c - 1] = value
                else:
                    for r, c in merge_list[i2]:
                        if table[r - 1][c - 1] != 'EMPTY':
                            value_dict[table[r - 1][c - 1]].remove((r, c))
                        value_dict[value].add((r, c))
                        table[r - 1][c - 1] = value
                # merge_list 업데이트
                if i1 == -1:
                    merge_list[i2].add((r1, c1))
                else:
                    for item in merge_list[i2]:
                        merge_list[i1].add(item)
                    del merge_list[i2]
            
            # 둘 다 개별 cell
            elif i1 == -1:
                if check:
                    if table[r1 - 1][c1 - 1] != 'EMPTY':
                        value_dict[table[r1 - 1][c1 - 1]].remove((r1, c1))
                    value_dict[value].add((r1, c1))
                    table[r1 - 1][c1 - 1] = value
                else:
                    if table[r2 - 1][c2 - 1] != 'EMPTY':
                        value_dict[table[r2 - 1][c2 - 1]].remove((r2, c2))
                    value_dict[value].add((r2, c2))
                    table[r2 - 1][c2 - 1] = value
                merge_list.append(set([(r1, c1), (r2, c2)]))
                
            # 바뀔놈은 개별 cell, 원래놈은 merge되어있는 cell
            else:
                # merge_list 업데이트
                if check:
                    for r, c in merge_list[i1]:
                        if table[r - 1][c - 1] != 'EMPTY':
                            value_dict[table[r - 1][c - 1]].remove((r, c))
                        value_dict[value].add((r, c))
                        table[r - 1][c - 1] = value
                else:
                    if table[r2 - 1][c2 - 1] != 'EMPTY':
                        value_dict[table[r2 - 1][c2 - 1]].remove((r2, c2))
                    value_dict[value].add((r2, c2))
                    table[r2 - 1][c2 - 1] = value
                merge_list[i1].add((r2, c2))
            
        elif command[0] == 'UNMERGE':
            r, c = int(command[1]), int(command[2])
            del_i = -1
            for i, arr in enumerate(merge_list):
                if (r, c) in arr:
                    del_i = i
                    break
                    
            if del_i != -1:
                for del_r, del_c in merge_list[del_i]:
                    if del_r == r and del_c == c:
                        continue
                    if table[del_r - 1][del_c - 1] != 'EMPTY':
                        value_dict[table[del_r - 1][del_c - 1]].remove((del_r, del_c))
                    table[del_r - 1][del_c - 1] = 'EMPTY'
                del merge_list[del_i]
            
        else:
            r, c = int(command[1]), int(command[2])
            answer.append(table[r - 1][c - 1])
    
    return answer

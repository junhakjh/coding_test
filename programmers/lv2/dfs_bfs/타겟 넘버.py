answer = 0

def solution(numbers, target):
    global answer
    dfs(0, numbers, 0, target)
    return answer

def dfs(num_sum, numbers, i, target):
    global answer
    if i == len(numbers):
        if num_sum == target:
            answer += 1
        return
    dfs(num_sum + numbers[i], numbers, i + 1, target)
    dfs(num_sum - numbers[i], numbers, i + 1, target)
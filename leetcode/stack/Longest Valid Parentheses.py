class Solution:
    def longestValidParentheses(self, s: str) -> int:
        answer = num = 0
        dp, stack = [False for _ in range(len(s))], []

        for i, char in enumerate(s):
            if char == ')':
                if stack:
                    stack_i = stack.pop()
                    dp[stack_i] = True
                    dp[i] = True
            else:
                stack.append(i)
        
        for item in dp:
            if item == False:
                answer = max(answer, num)
                num = 0
            else:
                num += 1

        return max(answer, num)

from collections import deque

class Solution:
    def isValid(self, s: str) -> bool:
        q, d = deque([]), {'(' : ')', '{': '}', '[': ']'}
        for char in s:
            if char in d:
                q.append(char)
            else:
                if len(q) == 0:
                    return False
                if not d[q.pop()] == char:
                    return False
        return True if len(q) == 0 else False

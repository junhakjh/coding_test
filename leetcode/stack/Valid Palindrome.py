from collections import deque

class Solution:
    def isPalindrome(self, s: str) -> bool:
        q, reverse_q = deque([]), deque([])
        for char in s:
            if not char.isalpha() and not char.isnumeric(): continue
            char = char.lower()
            q.append(char)
            reverse_q.appendleft(char)
        while len(q) > 0:
            if not q.pop() == reverse_q.pop():
                return False
        return True

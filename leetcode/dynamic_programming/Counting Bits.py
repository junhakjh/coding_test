class Solution:
    def countBits(self, n: int) -> List[int]:
        if n == 0:
            return [0]
        elif n == 1:
            return [0, 1]
        answer = [0, 1]
        i = 0
        while len(answer) <= n:
            if i == len(answer) / 2:
                i = 0
            answer.append(answer[i] + 1)
            i += 1
        return answer

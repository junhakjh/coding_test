import heapq

class Solution:
    def candy(self, ratings: List[int]) -> int:
        '''solution'''
        # n = len(ratings)
        # candies = [1] * n

        # for i in range(1, n):
        #     if ratings[i] > ratings[i - 1]:
        #         candies[i] = candies[i - 1] + 1
        
        # for i in range(n - 2, -1, -1):
        #     if ratings[i] > ratings[i + 1]:
        #         candies[i] = max(candies[i], candies[i + 1] + 1)
        
        # return sum(candies)

        if len(ratings) == 1: return 1
        answer, arr, heap = 0, [1] * len(ratings), []
        heapq.heapify(heap)

        for i, rating in enumerate(ratings):
            heapq.heappush(heap, [rating, i])
        
        while heap:
            rating, i = heapq.heappop(heap)
            if i == 0:
                if rating <= ratings[i + 1]:
                    continue
                else:
                    arr[i] = arr[i + 1] + 1
            elif i == len(ratings) - 1:
                if rating <= ratings[i - 1]:
                    continue
                else:
                    arr[i] = arr[i - 1] + 1
            else:
                if rating <= ratings[i - 1] and rating <= ratings[i + 1]:
                    continue
                elif rating > ratings[i - 1] and rating > ratings[i + 1]:
                    arr[i] = max(arr[i - 1], arr[i + 1]) + 1
                elif rating > ratings[i - 1] and rating <= ratings[i + 1]:
                    arr[i] = arr[i - 1] + 1
                else:
                    arr[i] = arr[i + 1] + 1

        answer = sum(arr)

        return answer

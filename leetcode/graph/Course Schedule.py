from collections import defaultdict, deque

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        course_dict, course_set, q = defaultdict(set), set(), deque([])

        for item in prerequisites:
            course_dict[item[0]].add(item[1])

        for num in range(numCourses):
            if not num in course_dict:
                q.append(num)

        while q:
            val = q.popleft()
            if val in course_dict:
                del course_dict[val]
            for key in course_dict:
                if val in course_dict[key]:
                    course_dict[key].remove(val)
                    if len(course_dict[key]) == 0:
                        q.append(key)

        return True if len(course_dict) == 0 else False

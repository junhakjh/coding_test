# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

from collections import deque

class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root: return []
        answer, cur_level, cur_arr, q = [], 1, [], deque([(root, 1)])

        while q:
            node, level = q.popleft()
            if level == cur_level:
                cur_arr.append(node.val)
            else:
                cur_level += 1
                answer.append(cur_arr)
                cur_arr = [node.val]
            if node.left:
                q.append((node.left, cur_level + 1))
            if node.right:
                q.append((node.right, cur_level + 1))
        
        answer.append(cur_arr)
        return answer

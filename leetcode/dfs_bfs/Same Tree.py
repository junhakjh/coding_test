# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

from collections import deque

class Solution:
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        if not p or not q:
            if not p and not q: return True
            else: return False
        pq, qq = deque([p]), deque([q])
        while pq and qq:
            p_node, q_node = pq.popleft(), qq.popleft()
            if not p_node.val == q_node.val: return False
            if (not p_node.left and q_node.left) or (p_node.left and not q_node.left): return False
            if p_node.left and q_node.left:
                pq.append(p_node.left)
                qq.append(q_node.left)
            if (not p_node.right and q_node.right) or (p_node.right and not q_node.right): return False
            if p_node.right and q_node.right:
                pq.append(p_node.right)
                qq.append(q_node.right)
        if pq or qq: return False
        else: return True

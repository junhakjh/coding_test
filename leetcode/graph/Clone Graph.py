"""
# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""

from typing import Optional
from collections import deque
class Solution:
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        if not node: return node

        q, clone = deque([node]), {node.val: Node(node.val, [])}
        while q:
            cur = q.popleft()
            
            for cur_neighbor in cur.neighbors:
                if not cur_neighbor.val in clone:
                    clone[cur_neighbor.val] = Node(cur_neighbor.val, [])
                    q.append(cur_neighbor)
                clone[cur.val].neighbors.append(clone[cur_neighbor.val])
        return clone[node.val]

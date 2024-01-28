# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

from collections import deque

class Codec:

    def serialize(self, root):
        """Encodes a tree to a single string.
        
        :type root: TreeNode
        :rtype: str
        """
        string, q = '', deque([])
        if root:
            string = str(root.val)
            q.append(root)
        while q:
            node = q.popleft()
            if node.left:
                string += ',' + str(node.left.val)
                q.append(node.left)
            else:
                string += ',null'
            if node.right:
                string += ',' + str(node.right.val)
                q.append(node.right)
            else:
                string += ',null'
        return string

    def deserialize(self, data):
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: TreeNode
        """
        if not data: return None
        arr = data.split(',')
        root = TreeNode(arr[0])
        q, i = deque([root]), 1
        while i < len(arr):
            node = q.popleft()
            if not arr[i] == 'null':
                node.left = TreeNode(int(arr[i]))
                q.append(node.left)
            if not arr[i + 1] == 'null':
                node.right = TreeNode(int(arr[i + 1]))
                q.append(node.right)
            i += 2

        return root

# Your Codec object will be instantiated and called as such:
# ser = Codec()
# deser = Codec()
# ans = deser.deserialize(ser.serialize(root))

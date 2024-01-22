# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

import heapq

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        h = []
        for i, item in enumerate(lists):
            if item:
                heapq.heappush(h, (item.val, i, item))
        head = tail = ListNode()
        while h:
            node = heapq.heappop(h)[2]
            tail.next = node
            tail = tail.next
            if node.next:
                i += 1
                heapq.heappush(h, (node.next.val, i, node.next))
        return head.next

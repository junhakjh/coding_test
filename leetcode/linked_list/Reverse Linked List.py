# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head: return head
        prev, answer = None, ListNode()
        while head:
            answer.val = head.val
            answer.next = prev
            prev = ListNode(head.val, prev)
            head = head.next
        return answer

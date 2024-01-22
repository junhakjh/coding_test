# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        tail, num = head, 0
        while tail:
            tail = tail.next
            num += 1
        if num == n:
            return head.next
        i = 0
        answer = head
        while i < num:
            if num - i == n + 1:
                if head.next.next:
                    head.next = head.next.next
                else:
                    head.next = None
                break
            head = head.next
            i += 1
        return answer

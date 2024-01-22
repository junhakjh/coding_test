# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        if not list1 and not list2:
            return list1
        head = ListNode()
        answer = head
        while list1 or list2:
            if not list1:
                head.next = list2
                break
            if not list2:
                head.next = list1
                break
            if list1.val < list2.val:
                head.next = ListNode(list1.val, None)
                list1 = list1.next
            else:
                head.next = ListNode(list2.val, None)
                list2 = list2.next
            head = head.next
        return answer.next

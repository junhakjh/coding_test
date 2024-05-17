T = int(input())

for test_case in range(1, T + 1):
    string = input()
    answer = "NO"

    def is_palindrome(s):
        length, mid = len(s), len(s) // 2
        if length % 2 == 0:
            if s[:mid] == s[-1:mid - 1:-1]:
                return True
        else:
            if s[:mid] == s[-1:mid:-1]:
                return True
        return False

    if is_palindrome(string):
        length, mid = len(string), len(string) // 2
        if length % 2 == 0:
            if is_palindrome(string[:mid]) and is_palindrome(string[-1:mid - 1:-1]):
                answer = "YES"
        else:
            if is_palindrome(string[:mid]) and is_palindrome(string[-1:mid:-1]):
                answer = "YES"

    print("#{} {}".format(test_case, answer))

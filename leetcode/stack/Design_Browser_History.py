from collections import deque

class BrowserHistory(object):
    back_arr, forward_arr = deque([]), deque([])
    cur_url = ''

    def __init__(self, homepage):        
        while len(self.back_arr) > 0:
            self.back_arr.pop()
        while len(self.forward_arr) > 0:
            self.forward_arr.pop()
        self.cur_url = homepage
        
    def visit(self, url):
        self.back_arr.append(self.cur_url)
        self.cur_url = url
        while len(self.forward_arr) > 0:
            self.forward_arr.pop()

    def back(self, steps):
        while len(self.back_arr) > 0 and steps > 0:
            self.forward_arr.append(self.cur_url)
            self.cur_url = self.back_arr.pop()
            steps -= 1
        return self.cur_url

    def forward(self, steps):
        while len(self.forward_arr) > 0 and steps > 0:
            self.back_arr.append(self.cur_url)
            self.cur_url = self.forward_arr.pop()
            steps -= 1
        return self.cur_url

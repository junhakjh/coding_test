class PriorityQueue {
  constructor(comparator) {
    this.heap = [];
    this.comparator = comparator;
  }

  offer(value) {
    this.heap.push(value);
    this.heapifyUp();
  }

  poll() {
    if (this.isEmpty()) {
      return null;
    }
    const top = this.heap[0];
    const end = this.heap.pop();
    if (this.size() > 0) {
      this.heap[0] = end;
      this.heapifyDown();
    }
    return top;
  }

  heapifyUp() {
    var idx = this.heap.length - 1;
    while (idx > 0) {
      const parent = (idx - 1) >> 1;
      // console.log(this.heap[parent], this.heap[idx], this.comparator(this.heap[parent], this.heap[idx]));
      if (this.comparator(this.heap[parent], this.heap[idx]) >= 0) return;
      [this.heap[parent], this.heap[idx]] = [this.heap[idx], this.heap[parent]];
      idx = parent;
    }
  }

  heapifyDown() {
    var idx = 0;
    while (idx < this.heap.length) {
      const left = (idx << 1) + 1;
      const right = (idx << 1) + 2;
      var target = idx;
      if (this.heap[left] != null && this.comparator(this.heap[target], this.heap[left]) < 0) {
        target = left;
      }
      if (this.heap[right] != null && this.comparator(this.heap[target], this.heap[right]) < 0) {
        target = right;
      }
      if (target === idx) {
        return;
      }
      [this.heap[idx], this.heap[target]] = [this.heap[target], this.heap[idx]];
      idx = target;
    }
  }

  isEmpty() {
    return this.heap.length === 0;
  }

  peek() {
    return this.isEmpty() ? null : this.heap[0];
  }

  size() {
    return this.heap.length;
  }
}

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);
const arr = Array.from({ length: N }, () => Array.from({ length: 2 }, () => 0));
for (var i = 1; i < 1 + N; i++) {
  arr[i - 1] = input[i].split(' ').map(Number);
}
arr.sort((a, b) => {
  if (a[0] === b[0]) {
    return a[1] < b[1] ? 1 : a[1] === b[1] ? 0 : -1;
  }
  return a[0] > b[0] ? 1 : a[0] === b[0] ? 0 : -1;
});

const pq = new PriorityQueue((a, b) => (a > b ? -1 : a === b ? 0 : 1));
// pq.offer(3);
// pq.offer(1);
// pq.offer(2);
// console.log(pq.heap);
for (const [day, score] of arr) {
  // if (day === 2) {
  //   console.log(pq.peek(), score);
  // }
  if (pq.size() < day) {
    pq.offer(score);
  } else if (pq.peek() < score) {
    // console.log(day);
    pq.poll();
    pq.offer(score);
  }
}

var answer = 0;

while (!pq.isEmpty()) {
  answer += pq.poll();
}

console.log(answer);

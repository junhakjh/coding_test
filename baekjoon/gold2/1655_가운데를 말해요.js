const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

class PriorityQueue {
  constructor(comparator) {
    this.heap = [];
    this.comparator = comparator;
  }

  offer(value) {
    this.heap.push(value);
    this.heapifyUp(this.heap.length - 1);
  }

  poll() {
    const top = this.heap[0];
    const end = this.heap.pop();
    if (!this.isEmpty()) {
      this.heap[0] = end;
      this.heapifyDown(0);
    }

    return top;
  }

  heapifyUp(idx) {
    while (idx > 0) {
      const parent = (idx - 1) >> 1;
      if (this.comparator(this.heap[parent], this.heap[idx]) <= 0) break;
      [this.heap[parent], this.heap[idx]] = [this.heap[idx], this.heap[parent]];
      idx = parent;
    }
  }

  heapifyDown(idx) {
    while (idx < this.heap.length) {
      const left = (idx << 1) + 1,
        right = (idx << 1) + 2;
      var target = idx;
      if (this.heap[left] != null && this.comparator(this.heap[target], this.heap[left]) > 0) target = left;
      if (this.heap[right] != null && this.comparator(this.heap[target], this.heap[right]) > 0) target = right;
      if (target === idx) break;
      [this.heap[target], this.heap[idx]] = [this.heap[idx], this.heap[target]];
      idx = target;
    }
  }

  peek() {
    return this.isEmpty() ? null : this.heap[0];
  }

  isEmpty() {
    return this.heap.length === 0;
  }
}

const N = parseInt(input[0], 10);

const answer = [];

const bigPq = new PriorityQueue((a, b) => (a > b ? -1 : 1));
const smallPq = new PriorityQueue((a, b) => (a > b ? 1 : -1));

for (var i = 1; i < 1 + N; i++) {
  const num = parseInt(input[i], 10);
  if (i % 2 === 1) {
    bigPq.offer(num);
    smallPq.offer(bigPq.poll());
    bigPq.offer(smallPq.poll());
  } else {
    smallPq.offer(num);
    bigPq.offer(smallPq.poll());
    smallPq.offer(bigPq.poll());
  }
  answer.push(bigPq.peek());
}

console.log(answer.join('\n'));

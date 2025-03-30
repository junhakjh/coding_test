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

  insert(value) {
    this.heap.push(value);
    this.heapifyUp(this.heap.length - 1);
  }

  remove() {
    const min = this.heap[0];
    const end = this.heap.pop();
    if (this.heap.length > 0) {
      this.heap[0] = end;
      this.heapifyDown(0);
    }

    return min;
  }

  heapifyUp(index) {
    while (index > 0) {
      const parent = (index - 1) >> 1;
      if (this.heap[parent] && this.comparator(this.heap[parent], this.heap[index]) <= 0) return;
      [this.heap[parent], this.heap[index]] = [this.heap[index], this.heap[parent]];
      index = parent;
    }
  }

  heapifyDown(index) {
    while (index < this.heap.length) {
      const left = (index << 1) + 1;
      const right = (index << 1) + 2;
      var target = index;
      if (this.heap[left] && this.comparator(this.heap[target], this.heap[left]) > 0) {
        target = left;
      }
      if (this.heap[right] && this.comparator(this.heap[target], this.heap[right]) > 0) {
        target = right;
      }
      if (target === index) break;
      [this.heap[index], this.heap[target]] = [this.heap[target], this.heap[index]];
      index = target;
    }
  }

  pick() {
    return this.isEmpty() ? null : this.heap[0];
  }

  isEmpty() {
    return this.heap.length === 0;
  }
}

const answer = [];

const [tc, ...rest] = input;
const T = parseInt(tc, 10);

const testCase = [];
var M = 0;
var arr = [];
rest.forEach((item, index) => {
  const numbers = item.split(' ').map(Number);
  if (M === 0) {
    M = numbers[0];
    if (index !== 0) {
      testCase.push([...arr]);
      arr = [];
    }
  } else {
    M -= numbers.length;
    arr.push(...numbers);
  }
});

testCase.push([...arr]);

testCase.forEach((thisCase) => {
  M = thisCase.length;
  const arr = [(M + 1) >> 1];

  const minPq = new PriorityQueue((a, b) => (a > b ? 1 : a === b ? 0 : -1));
  const maxPq = new PriorityQueue((a, b) => (a < b ? 1 : a === b ? 0 : -1));

  const mids = [];
  thisCase.forEach((num, index) => {
    if (index % 2 === 0) {
      maxPq.insert(num);
      const a = maxPq.remove();
      minPq.insert(a);
      const b = minPq.remove();
      maxPq.insert(b);
      if (index % 20 === 0) {
        mids.push([]);
      }
      mids[Math.floor(index / 20)].push(maxPq.pick());
    } else {
      minPq.insert(num);
      maxPq.insert(minPq.remove());
      minPq.insert(maxPq.remove());
    }
  });

  arr.push(mids.map((item) => item.join(' ')).join('\n'));
  answer.push(arr.join('\n'));
});

console.log(answer.join('\n'));

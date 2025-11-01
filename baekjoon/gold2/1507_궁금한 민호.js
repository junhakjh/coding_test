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
    if (!this.isEmpty()) {
      this.heap[0] = end;
      this.heapifyDown();
    }
    return top;
  }

  heapifyUp() {
    var idx = this.size() - 1;
    while (idx > 0) {
      const parent = (idx - 1) >> 1;
      if (this.comparator(this.heap[parent], this.heap[idx]) >= 0) {
        return;
      }
      [this.heap[parent], this.heap[idx]] = [this.heap[idx], this.heap[parent]];
      idx = parent;
    }
  }

  heapifyDown() {
    var parent = 0;
    while (parent < this.size()) {
      const left = (parent << 1) + 1;
      const right = left + 1;
      var target = parent;
      if (this.heap[left] != null && this.comparator(this.heap[target], this.heap[left]) < 0) {
        target = left;
      }
      if (this.heap[right] != null && this.comparator(this.heap[target], this.heap[right]) < 0) {
        target = right;
      }
      if (target === parent) {
        return;
      }
      [this.heap[parent], this.heap[target]] = [this.heap[target], this.heap[parent]];
      parent = target;
    }
  }

  size() {
    return this.heap.length;
  }

  isEmpty() {
    return this.size() === 0;
  }

  peek() {
    return this.isEmpty() ? null : this.heap[0];
  }
}

class Edge {
  constructor(a, b, c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
}

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);
const pq = new PriorityQueue((a, b) => (a.c > b.c ? -1 : a.c === b.c ? 0 : 1));
for (var i = 2; i < 1 + N; i++) {
  const costs = input[i].split(' ').map(Number);
  for (var j = 0; j < i - 1; j++) {
    pq.offer(new Edge(i, j + 1, costs[j]));
  }
}

const costs = Array.from({ length: N + 1 }, () => Array.from({ length: N + 1 }, () => Number.MAX_SAFE_INTEGER));
for (var i = 1; i <= N; i++) {
  costs[i][i] = 0;
}
var answer = 0;
var isImpossible = false;
while (!pq.isEmpty()) {
  const { a, b, c } = pq.poll();
  if (costs[a][b] < c) {
    isImpossible = true;
    break;
  }
  if (costs[a][b] === c) {
    continue;
  }
  answer += c;
  costs[a][b] = c;
  costs[b][a] = c;
  for (var mid = 1; mid <= N; mid++) {
    for (var i = 1; i <= N; i++) {
      for (var j = 1; j <= N; j++) {
        costs[i][j] = Math.min(costs[i][j], costs[i][mid] + costs[mid][j]);
      }
    }
  }
}

console.log(isImpossible ? -1 : answer);

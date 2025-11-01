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

class Union {
  constructor(n) {
    this.parents = Array.from({ length: n + 1 }, () => -1);
  }

  findRoot(idx) {
    if (this.parents[idx] < 0) {
      return idx;
    }
    return (this.parents[idx] = this.findRoot(this.parents[idx]));
  }

  union(a, b) {
    const aRoot = this.findRoot(a);
    const bRoot = this.findRoot(b);
    if (aRoot === bRoot) {
      return false;
    }
    this.parents[aRoot] += this.parents[bRoot];
    this.parents[bRoot] = aRoot;
    return true;
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
const M = parseInt(input[1]);
const pq = new PriorityQueue((a, b) => (a.c > b.c ? -1 : a.c === b.c ? 0 : 1));
for (var i = 2; i < 2 + M; i++) {
  const [a, b, c] = input[i].split(' ').map(Number);
  if (a === b) {
    continue;
  }
  pq.offer(new Edge(a, b, c));
}

var answer = 0;
const union = new Union(N);
while (!pq.isEmpty()) {
  const edge = pq.poll();
  if (union.union(edge.a, edge.b)) {
    answer += edge.c;
  }
}

console.log(answer);

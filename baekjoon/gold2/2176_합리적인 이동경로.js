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
      const parentIdx = (idx - 1) >> 1;
      if (this.heap[parentIdx] !== undefined && this.comparator(this.heap[parentIdx], this.heap[idx]) <= 0) return;
      [this.heap[parentIdx], this.heap[idx]] = [this.heap[idx], this.heap[parentIdx]];
      idx = parentIdx;
    }
  }

  heapifyDown(idx) {
    while (idx < this.heap.length) {
      const left = (idx << 1) + 1;
      const right = (idx << 1) + 2;
      var target = idx;
      if (this.heap[left] !== undefined && this.comparator(this.heap[target], this.heap[left]) > 0) target = left;
      if (this.heap[right] !== undefined && this.comparator(this.heap[target], this.heap[right]) > 0) target = right;
      if (target === idx) return;
      [this.heap[target], this.heap[idx]] = [this.heap[idx], this.heap[target]];
      idx = target;
    }
  }

  pick() {
    return this.isEmpty() ? null : this.heap[0];
  }

  isEmpty() {
    return this.heap.length === 0;
  }
}

const [numbers, ...infos] = input;
const [N, M] = numbers.split(' ').map(Number);

const distFromT = Array.from({ length: N }, () => Infinity);
distFromT[1] = 0;

const edges = Array.from({ length: N }, (_, start) => Array.from({ length: N }, (_, end) => (start === end ? 0 : -1)));

infos.forEach((row) => {
  const [A, B, C] = row.split(' ').map(Number);
  edges[A - 1][B - 1] = C;
  edges[B - 1][A - 1] = C;
});

const pq = new PriorityQueue((a, b) => (a[1] > b[1] ? 1 : -1));
pq.insert([1, 0]);

while (!pq.isEmpty()) {
  const [node] = pq.remove();

  edges[node].forEach((nextDist, nextNode) => {
    if (nextDist === -1 || node === nextNode) return;
    if (distFromT[node] + nextDist < distFromT[nextNode]) {
      distFromT[nextNode] = distFromT[node] + nextDist;
      pq.insert([nextNode, nextDist]);
    }
  });
}

const numToT = Array.from({ length: N }, () => 0);

const dfs = (node) => {
  if (node === 1) {
    return 1;
  }

  edges[node].forEach((dist, nextNode) => {
    if (dist === -1) return;
    if (distFromT[node] > distFromT[nextNode]) {
      if (numToT[nextNode] !== 0) {
        numToT[node] += numToT[nextNode];
      } else {
        numToT[node] += dfs(nextNode);
      }
    }
  });

  return numToT[node];
};

dfs(0);

console.log(numToT[0]);

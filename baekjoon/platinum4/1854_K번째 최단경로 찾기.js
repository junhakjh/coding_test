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
  constructor(num, cost) {
    this.num = num;
    this.cost = cost;
  }
}

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M, K] = input[0].split(' ').map(Number);
const edges = new Map();
for (var i = 1; i <= N; i++) {
  edges.set(i, []);
}
for (var i = 1; i < 1 + M; i++) {
  const [a, b, c] = input[i].split(' ').map(Number);
  edges.get(a).push(new Edge(b, c));
}

var answer = '';
const pq = new PriorityQueue((a, b) => (a.cost > b.cost ? -1 : a.cost === b.cost ? 0 : 1));
const pqList = Array.from({ length: N + 1 }, () => new PriorityQueue((a, b) => (a < b ? -1 : a === b ? 0 : 1)));
pqList[1].offer(0);
pq.offer(new Edge(1, 0));
while (!pq.isEmpty()) {
  const edge = pq.poll();
  for (const nextEdge of edges.get(edge.num)) {
    const costSum = edge.cost + nextEdge.cost;
    if (pqList[nextEdge.num].size() < K) {
      pqList[nextEdge.num].offer(costSum);
      pq.offer(new Edge(nextEdge.num, costSum));
    } else if (pqList[nextEdge.num].peek() > costSum) {
      pqList[nextEdge.num].poll();
      pqList[nextEdge.num].offer(costSum);
      pq.offer(new Edge(nextEdge.num, costSum));
    }
  }
}

for (var i = 1; i <= N; i++) {
  if (pqList[i].size() < K) {
    answer += '-1\n';
  } else {
    answer += pqList[i].peek() + '\n';
  }
}
console.log(answer);

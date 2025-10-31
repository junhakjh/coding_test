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

const [N, E] = input[0].split(' ').map(Number);
const map = new Map();
for (var i = 1; i <= N; i++) {
  map.set(i, []);
}
for (var i = 1; i < 1 + E; i++) {
  const [a, b, c] = input[i].split(' ').map(Number);
  map.get(a).push(new Edge(b, c));
  map.get(b).push(new Edge(a, c));
}
const [v1, v2] = input[1 + E].split(' ').map(Number);

const sToV1 = Array.from({ length: N + 1 }, () => Number.MAX_SAFE_INTEGER);
const sToV2 = Array.from({ length: N + 1 }, () => Number.MAX_SAFE_INTEGER);
const v1ToE = Array.from({ length: N + 1 }, () => Number.MAX_SAFE_INTEGER);
const v2ToE = Array.from({ length: N + 1 }, () => Number.MAX_SAFE_INTEGER);
const v1ToV2 = Array.from({ length: N + 1 }, () => Number.MAX_SAFE_INTEGER);

var answer = Number.MAX_SAFE_INTEGER;

var pq = new PriorityQueue((a, b) => (a.cost > b.cost ? -1 : a.cost === b.cost ? 0 : 1));
pq.offer(new Edge(v1, 0));
while (!pq.isEmpty()) {
  const edge = pq.poll();
  if (edge.num === v2) {
    v1ToV2[edge.num] = edge.cost;
    break;
  }
  if (v1ToV2[edge.num] <= edge.cost) {
    continue;
  }
  v1ToV2[edge.num] = edge.cost;
  for (const nextEdge of map.get(edge.num)) {
    if (edge.cost + nextEdge.cost < v1ToV2[nextEdge.num]) {
      pq.offer(new Edge(nextEdge.num, edge.cost + nextEdge.cost));
    }
  }
}

if (v1ToV2[v2] === Number.MAX_SAFE_INTEGER) {
  console.log(-1);
} else {
  var answer = Number.MAX_SAFE_INTEGER;
  var pq = new PriorityQueue((a, b) => (a.cost > b.cost ? -1 : a.cost === b.cost ? 0 : 1));
  pq.offer(new Edge(1, 0));
  while (!pq.isEmpty()) {
    const edge = pq.poll();
    if (edge.num === v1) {
      sToV1[edge.num] = edge.cost;
      break;
    }
    if (sToV1[edge.num] <= edge.cost) {
      continue;
    }
    sToV1[edge.num] = edge.cost;
    for (const nextEdge of map.get(edge.num)) {
      if (edge.cost + nextEdge.cost < sToV1[nextEdge.num]) {
        pq.offer(new Edge(nextEdge.num, edge.cost + nextEdge.cost));
      }
    }
  }
  if (sToV1[v1] !== Number.MAX_SAFE_INTEGER) {
    var pq = new PriorityQueue((a, b) => (a.cost > b.cost ? -1 : a.cost === b.cost ? 0 : 1));
    pq.offer(new Edge(v2, sToV1[v1] + v1ToV2[v2]));
    while (!pq.isEmpty()) {
      const edge = pq.poll();
      if (edge.num === N) {
        v2ToE[edge.num] = edge.cost;
        break;
      }
      if (v2ToE[edge.num] <= edge.cost) {
        continue;
      }
      v2ToE[edge.num] = edge.cost;
      for (const nextEdge of map.get(edge.num)) {
        if (edge.cost + nextEdge.cost < v2ToE[nextEdge.num]) {
          pq.offer(new Edge(nextEdge.num, edge.cost + nextEdge.cost));
        }
      }
    }
  }
  var pq = new PriorityQueue((a, b) => (a.cost > b.cost ? -1 : a.cost === b.cost ? 0 : 1));
  pq.offer(new Edge(1, 0));
  while (!pq.isEmpty()) {
    const edge = pq.poll();
    if (edge.num === v2) {
      sToV2[edge.num] = edge.cost;
      break;
    }
    if (sToV2[edge.num] <= edge.cost) {
      continue;
    }
    sToV2[edge.num] = edge.cost;
    for (const nextEdge of map.get(edge.num)) {
      if (edge.cost + nextEdge.cost < sToV2[nextEdge.num]) {
        pq.offer(new Edge(nextEdge.num, edge.cost + nextEdge.cost));
      }
    }
  }
  if (sToV2[v2] !== Number.MAX_SAFE_INTEGER) {
    var pq = new PriorityQueue((a, b) => (a.cost > b.cost ? -1 : a.cost === b.cost ? 0 : 1));
    pq.offer(new Edge(v1, sToV2[v2] + v1ToV2[v2]));
    while (!pq.isEmpty()) {
      const edge = pq.poll();
      if (edge.num === N) {
        v1ToE[edge.num] = edge.cost;
        break;
      }
      if (v1ToE[edge.num] <= edge.cost) {
        continue;
      }
      v1ToE[edge.num] = edge.cost;
      for (const nextEdge of map.get(edge.num)) {
        if (edge.cost + nextEdge.cost < v1ToE[nextEdge.num]) {
          pq.offer(new Edge(nextEdge.num, edge.cost + nextEdge.cost));
        }
      }
    }
  }
  answer = Math.min(answer, v1ToE[N], v2ToE[N]);

  console.log(answer === Number.MAX_SAFE_INTEGER ? -1 : answer);
}

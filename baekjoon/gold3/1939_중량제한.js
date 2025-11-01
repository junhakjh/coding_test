class Queue {
  constructor() {
    this.q = new Map();
    this.front = 0;
    this.end = 0;
  }

  offer(value) {
    this.q.set(this.end++, value);
  }

  poll() {
    if (this.isEmpty()) {
      return null;
    }
    const result = this.q.get(this.front);
    this.q.delete(this.front++);

    return result;
  }

  size() {
    return this.end - this.front;
  }

  isEmpty() {
    return this.size() === 0;
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

const [N, M] = input[0].split(' ').map(Number);

const edges = new Map();
for (var i = 1; i <= N; i++) {
  edges.set(i, []);
}
for (var i = 1; i < 1 + M; i++) {
  const [a, b, c] = input[i].split(' ').map(Number);
  edges.get(a).push(new Edge(b, c));
  edges.get(b).push(new Edge(a, c));
}

const [start, end] = input[1 + M].split(' ').map(Number);

var l = 1,
  r = 1000000000;
while (l < r) {
  const maxCost = Math.ceil((l + r) / 2);
  if (check(maxCost)) {
    l = maxCost;
  } else {
    r = maxCost - 1;
  }
}

console.log(l);

function check(k) {
  const q = new Queue();
  q.offer(start);
  const visited = Array.from({ length: N }, () => false);
  visited[start] = true;
  while (!q.isEmpty()) {
    const num = q.poll();
    for (const edge of edges.get(num)) {
      if (!visited[edge.num] && k <= edge.cost) {
        if (edge.num === end) {
          return true;
        }
        visited[edge.num] = true;
        q.offer(edge.num);
      }
    }
  }

  return false;
}

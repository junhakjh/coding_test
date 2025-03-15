const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
  }
}

class Queue {
  constructor() {
    this.front = null;
    this.rear = null;
  }

  isEmpty() {
    return this.front === null && this.rear === null;
  }

  push(num) {
    const node = new Node(num);
    if (this.isEmpty()) {
      this.front = node;
    } else {
      this.rear.next = node;
    }

    this.rear = node;
  }

  pop() {
    if (this.isEmpty()) return null;

    const result = this.front.val;
    this.front = this.front.next;
    if (this.front === null) {
      this.rear = null;
    }

    return result;
  }
}

const dr = [0, 1, 0, -1],
  dc = [1, 0, -1, 0];

var [M, N] = input[0].split(' ').map(Number);
var map = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));

var q = new Queue();
var zeros = 0;

for (var r = 0; r < N; r++) {
  var row = input[r + 1].split(' ').map(Number);
  row.forEach((item, c) => {
    map[r][c] = item;
    if (item === 1) {
      q.push([r, c, 0]);
    } else if (item === 0) {
      zeros++;
    }
  });
}

const isIn = (r, c) => {
  return r >= 0 && r < N && c >= 0 && c < M;
};

var maxDay = 0;

while (!q.isEmpty()) {
  const [r, c, day] = q.pop();
  for (var i = 0; i < 4; i++) {
    var nr = r + dr[i],
      nc = c + dc[i];
    if (isIn(nr, nc) && map[nr][nc] === 0) {
      zeros--;
      map[nr][nc] = 1;
      q.push([nr, nc, day + 1]);
      maxDay = Math.max(maxDay, day + 1);
    }
  }
}

console.log(zeros > 0 ? -1 : maxDay);

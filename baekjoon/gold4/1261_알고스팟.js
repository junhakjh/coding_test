const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

class Queue {
  constructor() {
    this.q = new Map();
    this.front = 0;
    this.rear = 0;
  }

  offer(value) {
    this.q.set(this.rear++, value);
  }

  poll() {
    return this.q.get(this.front++);
  }

  isEmpty() {
    return this.front === this.rear;
  }
}

const dr = [-1, 1, 0, 0],
  dc = [0, 0, -1, 1];

const [size, ...mapInfo] = input;
const [C, R] = size.split(' ').map(Number);
const map = [];
mapInfo.forEach((row) => map.push(row.split('')));

const minMap = Array.from({ length: R }, () => Array.from({ length: C }, () => 10000));
minMap[0][0] = 0;

const isIn = (r, c) => {
  return r >= 0 && r < R && c >= 0 && c < C;
};

const q = new Queue();
q.offer([0, 0]);
while (!q.isEmpty()) {
  const [r, c] = q.poll();
  const curNum = minMap[r][c];
  for (var i = 0; i < 4; i++) {
    const nr = r + dr[i],
      nc = c + dc[i];
    if (isIn(nr, nc)) {
      if (map[nr][nc] === '0' && minMap[nr][nc] > curNum) {
        minMap[nr][nc] = curNum;
        q.offer([nr, nc]);
      } else if (map[nr][nc] === '1' && minMap[nr][nc] > curNum + 1) {
        minMap[nr][nc] = curNum + 1;
        q.offer([nr, nc]);
      }
    }
  }
}

console.log(minMap[R - 1][C - 1]);

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
    const value = this.q.get(this.front);
    this.q.delete(this.front++);

    return value;
  }

  isEmpty() {
    return this.front === this.rear;
  }
}

const dr = [-1, 1, 0, 0],
  dc = [0, 0, -1, 1];

const [N, M] = input[0].split(' ').map(Number);
const map = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));
const canGoMap = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));
const canGoMapper = new Map();

for (var r = 0; r < N; r++) {
  for (var c = 0; c < M; c++) {
    map[r][c] = parseInt(input[r + 1].charAt(c), 10);
  }
}

const isIn = (r, c) => {
  return r >= 0 && r < N && c >= 0 && c < M;
};

var canGoNum = 1;
map.forEach((row, r) => {
  row.forEach((item, c) => {
    if (canGoMap[r][c] === 0 && item === 0) {
      const q = new Queue();
      q.offer([r, c]);
      var num = 0;
      canGoMap[r][c] = canGoNum;
      while (!q.isEmpty()) {
        const [r, c] = q.poll();
        num++;
        for (var i = 0; i < 4; i++) {
          const nr = r + dr[i],
            nc = c + dc[i];
          if (isIn(nr, nc) && canGoMap[nr][nc] === 0 && map[nr][nc] === 0) {
            canGoMap[nr][nc] = canGoNum;
            q.offer([nr, nc]);
          }
        }
      }
      canGoMapper.set(canGoNum++, num);
    }
  });
});

map.forEach((row, r) => {
  row.forEach((item, c) => {
    if (item === 1) {
      const set = new Set();
      for (var i = 0; i < 4; i++) {
        const nr = r + dr[i],
          nc = c + dc[i];
        if (isIn(nr, nc) && canGoMap[nr][nc] !== 0) {
          set.add(canGoMap[nr][nc]);
        }
      }
      set.forEach((num) => {
        map[r][c] += canGoMapper.get(num);
      });
      map[r][c] %= 10;
    }
  });
});

console.log(map.map((row) => row.join('')).join('\n'));

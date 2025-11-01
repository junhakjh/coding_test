class Queue {
  constructor() {
    this.map = new Map();
    this.front = 0;
    this.end = 0;
  }

  offer(value) {
    this.map.set(this.end++, value);
  }

  poll() {
    if (this.isEmpty()) {
      return null;
    }
    const result = this.map.get(this.front);
    this.map.delete(this.front++);
    return result;
  }

  size() {
    return this.end - this.front;
  }

  isEmpty() {
    return this.size() === 0;
  }
}

const dr = [-1, 1, 0, 0],
  dc = [0, 0, -1, 1];

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M] = input[0].split(' ').map(Number);
const map = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));
const viruses = [];
for (var r = 0; r < N; r++) {
  const row = input[r + 1].split(' ').map(Number);
  for (var c = 0; c < M; c++) {
    map[r][c] = row[c];
    if (map[r][c] === 2) {
      viruses.push([r, c]);
    }
  }
}

var answer = 0;
for (var i = 0; i < N * M - 2; i++) {
  if (map[Math.floor(i / M)][i % M] !== 0) {
    continue;
  }
  for (var j = i + 1; j < N * M - 1; j++) {
    if (map[Math.floor(j / M)][j % M] !== 0) {
      continue;
    }
    for (var k = j + 1; k < N * M; k++) {
      if (map[Math.floor(k / M)][k % M] !== 0) {
        continue;
      }
      const curMap = initMap(i, j, k);
      spread(curMap);
      answer = Math.max(answer, cntSafety(curMap));
    }
  }
}

console.log(answer);

function isIn(r, c) {
  return r >= 0 && r < N && c >= 0 && c < M;
}

function initMap(i, j, k) {
  const newMap = Array.from({ length: N }, () => []);
  for (var r = 0; r < N; r++) {
    newMap[r] = [...map[r]];
  }
  newMap[Math.floor(i / M)][i % M] = 1;
  newMap[Math.floor(j / M)][j % M] = 1;
  newMap[Math.floor(k / M)][k % M] = 1;

  return newMap;
}

function spread(curMap) {
  const q = new Queue();
  for (const virus of viruses) {
    q.offer(virus);
  }
  while (!q.isEmpty()) {
    const [r, c] = q.poll();
    for (var i = 0; i < 4; i++) {
      const nr = r + dr[i],
        nc = c + dc[i];
      if (isIn(nr, nc) && curMap[nr][nc] === 0) {
        curMap[nr][nc] = 2;
        q.offer([nr, nc]);
      }
    }
  }
}

function cntSafety(curMap) {
  var result = 0;
  for (const row of curMap) {
    for (const cell of row) {
      if (cell === 0) {
        result++;
      }
    }
  }

  return result;
}

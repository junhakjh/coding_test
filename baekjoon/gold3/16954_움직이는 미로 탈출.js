const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const dr = [-1, -1, 0, 1, 1, 1, 0, -1, 0],
  dc = [0, 1, 1, 1, 0, -1, -1, -1, 0];

const map = Array.from({ length: 16 }, () => Array.from({ length: 8 }, () => '.'));
const visited = Array.from({ length: 16 }, () => Array.from({ length: 8 }, () => false));
input.forEach((row, r) => (map[r + 8] = row.split('')));

const C = 8;
var answer = 0;

const isIn = (r, c, R) => r >= 0 && r < R && c >= 0 && c < C;

const dfs = (r, c, R) => {
  if (r === 0 && c === C - 1) {
    answer = 1;
  }
  if (answer === 1) {
    return;
  }

  for (var i = 0; i < 9; i++) {
    const nr = r + dr[i],
      nc = c + dc[i];
    if (isIn(nr, nc, R) && !visited[nr][nc] && map[nr][nc] === '.') {
      if (nr > 0 && map[nr - 1][nc] === '#') {
        continue;
      }
      visited[nr][nc] = true;
      dfs(Math.max(0, nr - 1), nc, Math.max(8, R - 1));
      visited[nr][nc] = false;
    }
  }
};

dfs(15, 0, 16);

console.log(answer);

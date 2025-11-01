const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, L] = input[0].split(' ').map(Number);
const map = Array.from({ length: N }, () => Array.from({ length: N }, () => 0));
for (var r = 0; r < N; r++) {
  map[r] = input[r + 1].split(' ').map(Number);
}

var answer = 0;

for (var r = 0; r < N; r++) {
  const visited = Array.from({ length: N }, () => false);
  var flag = true;
  label: for (var c = 0; c < N; c++) {
    if (c < N - 1 && Math.abs(map[r][c] - map[r][c + 1]) > 1) {
      flag = false;
      break;
    }
    if (c > 0 && map[r][c - 1] < map[r][c]) {
      for (var i = c - L; i < c; i++) {
        if (i < 0 || map[r][i] !== map[r][c - 1] || visited[i]) {
          flag = false;
          break label;
        }
        visited[i] = true;
      }
    }
    if (c < N - 1 && map[r][c + 1] < map[r][c]) {
      for (var i = c + L; i > c; i--) {
        if (i >= N || map[r][i] !== map[r][c + 1] || visited[i]) {
          flag = false;
          break label;
        }
        visited[i] = true;
      }
    }
  }
  if (flag) {
    answer++;
  }
}

for (var c = 0; c < N; c++) {
  const visited = Array.from({ length: N }, () => false);
  var flag = true;
  label: for (var r = 0; r < N; r++) {
    if (r < N - 1 && Math.abs(map[r][c] - map[r + 1][c]) > 1) {
      flag = false;
      break;
    }
    if (r > 0 && map[r - 1][c] < map[r][c]) {
      for (var i = r - L; i < r; i++) {
        if (i < 0 || map[i][c] !== map[r - 1][c] || visited[i]) {
          flag = false;
          break label;
        }
        visited[i] = true;
      }
    }
    if (r < N - 1 && map[r + 1][c] < map[r][c]) {
      for (var i = r + L; i > r; i--) {
        if (i >= N || map[i][c] !== map[r + 1][c] || visited[i]) {
          flag = false;
          break label;
        }
        visited[i] = true;
      }
    }
  }
  if (flag) {
    answer++;
  }
}

console.log(answer);

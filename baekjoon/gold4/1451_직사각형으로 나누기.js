const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M] = input[0].split(' ').map(Number);
const map = Array.from({ length: N + 1 }, () => new Array(M + 1).fill(0));
for (var r = 1; r < 1 + N; r++) {
  map[r] = [0, ...input[r].split('').map(Number)];
}
for (var r = 1; r <= N; r++) {
  for (var c = 1; c <= M; c++) {
    map[r][c] += map[r - 1][c] + map[r][c - 1] - map[r - 1][c - 1];
  }
}

var answer = 0;

// 가로 3개 직사각형
for (var i = 1; i < N - 1; i++) {
  for (var j = i + 1; j < N; j++) {
    answer = Math.max(answer, map[i][M] * (map[j][M] - map[i][M]) * (map[N][M] - map[j][M]));
  }
}
// 세로 3개 직사각형
for (var i = 1; i < M - 1; i++) {
  for (var j = i + 1; j < M; j++) {
    answer = Math.max(answer, map[N][i] * (map[N][j] - map[N][i]) * (map[N][M] - map[N][j]));
  }
}
for (var r = 1; r < N; r++) {
  for (var c = 1; c < M; c++) {
    // 위 가로, 아래 세로세로
    answer = Math.max(answer, map[r][M] * (map[N][c] - map[r][c]) * (map[N][M] - map[N][c] - map[r][M] + map[r][c]));
    // 위 세로세로, 아래 가로
    answer = Math.max(answer, map[r][c] * (map[r][M] - map[r][c]) * (map[N][M] - map[r][M]));
  }
}
for (var c = 1; c < M; c++) {
  for (var r = 1; r < N; r++) {
    // 왼 세로, 오 가로가로
    answer = Math.max(answer, map[N][c] * (map[r][M] - map[r][c]) * (map[N][M] - map[N][c] - map[r][M] + map[r][c]));
    // 왼 가로가로, 오 세로
    answer = Math.max(answer, map[r][c] * (map[N][c] - map[r][c]) * (map[N][M] - map[N][c]));
  }
}

console.log(answer);

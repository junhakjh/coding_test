const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M] = input[0].split(' ').map(Number);
const paper = Array.from({ length: N }, () => Array.from({ length: M }, () => 0));
for (var r = 0; r < N; r++) {
  const row = input[r + 1].split('').map(Number);
  row.forEach((item, c) => (paper[r][c] = item));
}

var maxSum = 0;

for (var bits = 0; bits < Math.pow(2, N * M); bits++) {
  var visited = Array.from({ length: N }, () => Array.from({ length: M }, () => false));
  var sum = 0;
  for (var r = 0; r < N; r++) {
    for (var c = 0; c < M; c++) {
      var num = 0;
      var nr = r,
        nc = c;
      while (nr < N && nc < M) {
        if (visited[nr][nc]) {
          break;
        }
        if (
          (bits & Math.pow(2, r * M + c)) / Math.pow(2, r * M + c) !==
          (bits & Math.pow(2, nr * M + nc)) / Math.pow(2, nr * M + nc)
        ) {
          break;
        }
        num *= 10;
        visited[nr][nc] = true;
        num += paper[nr][nc];
        if ((bits & Math.pow(2, nr * M + nc)) === 0) {
          nc++;
        } else {
          nr++;
        }
      }
      sum += num;
    }
  }
  maxSum = Math.max(maxSum, sum);
}

console.log(maxSum);

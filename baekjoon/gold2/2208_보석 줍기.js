const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M] = input[0].split(' ').map(Number);
const arr = new Array(N + 1).fill(0);
const prefixSum = new Array(N + 1).fill(0);

for (var i = 1; i < 1 + N; i++) {
  arr[i] = parseInt(input[i]);
  prefixSum[i] = prefixSum[i - 1] + arr[i];
}

var answer = 0;
const dp = new Array(N + 1).fill(0);
dp[M] = prefixSum[M];
for (var i = 1 + M; i <= N; i++) {
  dp[i] = Math.max(dp[i - 1] + arr[i], prefixSum[i] - prefixSum[i - M]);
  answer = Math.max(answer, dp[i]);
}

console.log(answer);

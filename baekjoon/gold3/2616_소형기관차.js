const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);
const arr = input[1].split(' ').map(Number);
const K = parseInt(input[2]);

const prefixSum = [0, ...arr];
for (var i = 1; i <= N; i++) {
  prefixSum[i] += prefixSum[i - 1];
}

const dp1 = Array.from({ length: N }, () => 0);
const dp2 = Array.from({ length: N }, () => 0);
const dp3 = Array.from({ length: N }, () => 0);

for (var i = K - 1; i < N - 2 * K; i++) {
  dp1[i] = Math.max(dp1[i - 1], prefixSum[i + 1] - prefixSum[i + 1 - K]);
}
for (var i = 2 * K - 1; i < N - K; i++) {
  dp2[i] = Math.max(dp2[i - 1], prefixSum[i + 1] - prefixSum[i + 1 - K] + dp1[i - K]);
}
for (var i = 3 * K - 1; i < N; i++) {
  dp3[i] = Math.max(dp3[i - 1], prefixSum[i + 1] - prefixSum[i + 1 - K] + dp2[i - K]);
}

console.log(dp3[N - 1]);

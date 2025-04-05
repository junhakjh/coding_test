const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n')
  .map(Number);

const MOD = 1_000_000_003;
const [N, K] = input;

const dp = Array.from({ length: N + 1 }, (_, i) =>
  Array.from({ length: K + 1 }, (_, j) => (j === 0 ? 1 : j === 1 ? i : 0))
);

for (var n = 2; n < N; n++) {
  for (var k = 2; k <= Math.min(n, K); k++) {
    dp[n][k] = (dp[n - 2][k - 1] + dp[n - 1][k]) % MOD;
  }
}

dp[N][K] = (dp[N - 3][K - 1] + dp[N - 1][K]) % MOD;

console.log(dp[N][K]);

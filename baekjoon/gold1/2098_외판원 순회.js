const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);
const costMap = Array.from({ length: N }, () => Array.from({ length: N }, () => 0));
for (var r = 0; r < N; r++) {
  costMap[r] = input[r + 1].split(' ').map(Number);
}

const MAX = 1000000000;

const dp = Array.from({ length: N }, () => Array.from({ length: 1 << N }, () => MAX));
const dfs = (start, visited) => {
  if (visited === (1 << N) - 1) {
    dp[start][visited] = costMap[start][0] === 0 ? -1 : costMap[start][0];
    return dp[start][visited];
  }

  if (dp[start][visited] !== MAX) {
    return dp[start][visited];
  }

  for (var i = 1; i < N; i++) {
    if ((visited & (1 << i)) !== 0 || costMap[start][i] === 0) {
      continue;
    }
    const nextCost = dfs(i, visited | (1 << i));
    if (nextCost < 0) {
      continue;
    }
    dp[start][visited] = Math.min(dp[start][visited], nextCost + costMap[start][i]);
  }

  return (dp[start][visited] = dp[start][visited] === MAX ? -1 : dp[start][visited]);
};

const answer = dfs(0, 1);

console.log(answer);

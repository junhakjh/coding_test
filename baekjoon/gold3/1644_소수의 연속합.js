const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);

const primes = [];
const visited = Array.from({ length: 4000000 }, () => false);
for (var i = 2; i <= 4000000; i++) {
  if (!visited[i]) {
    primes.push(i);
    for (j = i; j <= 4000000; j += i) {
      visited[j] = true;
    }
  }
}
const prefixSum = [0, ...primes];
for (var i = 1; i <= primes.length; i++) {
  prefixSum[i] += prefixSum[i - 1];
}

var l = 1,
  r = 1,
  answer = 0;
while (l <= r) {
  const sum = prefixSum[r] - prefixSum[l - 1];
  if (sum === N) {
    answer++;
    l++;
    r++;
  } else if (sum < N) {
    r++;
  } else {
    l++;
  }
}

console.log(answer);

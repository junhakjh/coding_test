const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0], 10);

const answer = [];

const hanoi = (from, via, to, n) => {
  if (n === N) {
    return;
  }

  hanoi(from, to, via, n + 1);
  answer.push(`${from} ${to}`);
  hanoi(via, from, to, n + 1);
};

answer.push(2n ** BigInt(N) - 1n);
if (N <= 20) {
  hanoi(1, 2, 3, 0);
}

console.log(answer.join('\n'));

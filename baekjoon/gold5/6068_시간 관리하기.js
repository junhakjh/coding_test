const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0], 10);
var answer = 0;
const times = Array.from({ length: N }, () => []);
for (var i = 1; i < 1 + N; i++) {
  times[i - 1] = input[i].split(' ').map(Number);
  answer = Math.max(answer, times[i - 1][1]);
}
times.sort((a, b) => (a[1] > b[1] ? -1 : 1));

for (var i = 0; i < N; i++) {
  const time = times[i];
  answer = Math.min(time[1] - time[0], answer - time[0]);
  if (answer < 0) {
    break;
  }
}

console.log(answer >= 0 ? answer : -1);

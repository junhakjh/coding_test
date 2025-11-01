const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);
const M = parseInt(input[1]);

const lowerMap = new Map();
const upperMap = new Map();
for (var i = 1; i <= N; i++) {
  lowerMap.set(i, []);
  upperMap.set(i, []);
}
for (var i = 2; i < 2 + M; i++) {
  const [a, b] = input[i].split(' ').map(Number);
  lowerMap.get(a).push(b);
  upperMap.get(b).push(a);
}

var answer = '';
for (var i = 1; i <= N; i++) {
  var sum = 0;
  const lowerSet = new Set();
  const upperSet = new Set();
  sum += lowerDfs(i, lowerSet);
  sum += upperDfs(i, upperSet);
  answer += N - 1 - (lowerSet.size + upperSet.size) + '\n';
}

console.log(answer);

function lowerDfs(num, set) {
  for (const next of lowerMap.get(num)) {
    if (set.has(next)) {
      continue;
    }
    set.add(next);
    lowerDfs(next, set);
  }
}

function upperDfs(num, set) {
  for (const next of upperMap.get(num)) {
    if (set.has(next)) {
      continue;
    }
    set.add(next);
    upperDfs(next, set);
  }
}

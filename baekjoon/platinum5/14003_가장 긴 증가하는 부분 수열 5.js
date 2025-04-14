const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);
const arr = input[1].split(' ').map(Number);

const memo = [arr[0]];
const idxArr = [[0]];

for (var i = 1; i < N; i++) {
  const num = arr[i];

  var l = 0;
  var r = memo.length - 1;

  if (num > memo[r]) {
    memo.push(num);
    idxArr.push([i]);
    continue;
  }

  while (l < r) {
    const mid = Math.floor((l + r) / 2);
    if (num > memo[mid]) {
      l = mid + 1;
    } else {
      r = mid;
    }
  }

  if (memo[r] !== num) {
    idxArr[r].push(i);
  }
  memo[r] = num;
}

const answerArr = Array.from({ length: memo.length }, () => 0);
var prev = Number.MAX_SAFE_INTEGER;
for (var i = memo.length - 1; i >= 0; i--) {
  const cand = idxArr[i];
  for (var j = cand.length - 1; j >= 0; j--) {
    if (cand[j] < prev) {
      answerArr[i] = arr[cand[j]];
      prev = cand[j];
      break;
    }
  }
}
const answer = [answerArr.length, answerArr.join(' ')];
console.log(answer.join('\n'));

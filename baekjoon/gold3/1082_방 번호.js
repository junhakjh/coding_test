const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0], 10);
const prices = Array.from({ length: N }, () => 0);
input[1]
  .split(' ')
  .map(Number)
  .forEach((price, idx) => {
    prices[idx] = [idx, price];
  });
prices.sort((a, b) => a[1] - b[1]);
const M = parseInt(input[2], 10);
const arr = Array.from({ length: M + 1 }, () => []);

var answer = '0';

for (var idx = 1; idx <= M; idx++) {
  maxNumStr = '0';
  prices.forEach(([num, price]) => {
    if (idx - price < 0) return;
    const curArr = [...arr[idx - price], num].sort((a, b) => b - a);
    const curNumStr = curArr.join('');
    if (curNumStr.length > maxNumStr.length || (curNumStr.length === maxNumStr.length && curNumStr > maxNumStr)) {
      arr[idx] = curArr;
      maxNumStr = curNumStr;
    }
  });
  if (maxNumStr.length > answer.length || (maxNumStr.length === answer.length && maxNumStr > answer)) {
    answer = maxNumStr;
  }
}

console.log(answer);

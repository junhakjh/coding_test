const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [info, ...infos] = input;
const [N, K] = info.split(' ').map(Number);
var [l, r] = [0, 0];
const nums = infos.map((num) => {
  r = Math.max(r, num);
  return Number(num);
});

while (l < r) {
  const mid = Math.ceil((l + r) / 2);
  var sum = 0;
  for (const num of nums) {
    sum += Math.floor(num / mid);
    if (sum >= K) {
      break;
    }
  }
  if (sum >= K) {
    l = mid;
  } else {
    r = mid - 1;
  }
}

console.log(l);

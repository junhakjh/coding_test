const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

let [N, ...infos] = input;
N = parseInt(N);

let buildings = 0;
const stack = [0];
infos.forEach((arr) => {
  const num = parseInt(arr.split(' ')[1]);
  while (stack[stack.length - 1] > num) {
    stack.pop();
  }
  if (stack[stack.length - 1] !== num) {
    stack.push(num);
    buildings++;
  }
});

console.log(buildings);

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const N = parseInt(input[0]);

var flag = false;
var answer = '';
function dfs(arr, idx) {
  if (idx === N) {
    flag = true;
    answer = arr.join('');
    return;
  }

  for (var i = 1; i <= 3; i++) {
    arr[idx] = i;
    if (check(arr, idx)) {
      dfs(arr, idx + 1);
      if (flag) {
        return;
      }
    }
  }
}

function check(arr, idx) {
  label: for (var i = 1; i <= Math.ceil(idx / 2); i++) {
    for (var j = idx; j > idx - i; j--) {
      if (arr[j] !== arr[j - i]) {
        continue label;
      }
    }
    return false;
  }
  return true;
}

if (N === 1) {
  console.log(1);
} else {
  dfs([1, 2], 2);

  console.log(answer);
}

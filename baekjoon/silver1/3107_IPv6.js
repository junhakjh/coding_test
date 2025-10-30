const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const arr = input[0].split(':');
var answer = '';
var onlyZeroNum = 8 - arr.length + 1;

arr.forEach((str, idx) => {
  if (str.length == 0) {
    if (idx != arr.length - 1 && arr[idx + 1].length == 0) {
      answer += '0000';
    } else {
      for (var i = 0; i < onlyZeroNum; i++) {
        answer += '0000';
        if (i != onlyZeroNum - 1) {
          answer += ':';
        }
      }
    }
  } else {
    for (var i = 0; i < 4 - str.length; i++) {
      answer += '0';
    }
    answer += str;
  }
  if (idx != arr.length - 1 && answer[answer.length - 1] !== ':' && answer.length != 39) {
    answer += ':';
  }
});

console.log(answer);

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const MOD = 1_000_000;

const answer = [1];

if (input[0].charAt(0) === '0') {
  console.log(0);
} else {
  var isOk = true;
  if (input[0].length > 1) {
    if (input[0].charAt(1) === '0' && parseInt(input[0].charAt(0), 10) > 2) {
      isOk = false;
    } else {
      if (input[0].charAt(0) === '1' || input[0].charAt(0) === '2') {
        if (input[0].charAt(1) === '0') {
          answer.push(1);
        } else if (input[0].charAt(0) === '2' && parseInt(input[0].charAt(1), 10) > 6) {
          answer.push(1);
        } else {
          answer.push(2);
        }
      } else {
        answer.push(1);
      }
      for (var i = 2; i < input[0].length; i++) {
        if (input[0].charAt(i) === '0') {
          if (parseInt(input[0].charAt(i - 1), 10) > 2 || input[0].charAt(i - 1) === '0') {
            isOk = false;
            break;
          }
          if (input[0].charAt(i - 1) === '1' || input[0].charAt(i - 1) === '2') {
            answer.push(answer[i - 2]);
            continue;
          }
        }
        if (input[0].charAt(i - 1) === '1' || input[0].charAt(i - 1) === '2') {
          if (input[0].charAt(i - 1) === '2') {
            if (parseInt(input[0].charAt(i), 0) > 6) {
              answer.push(answer[i - 1]);
              continue;
            }
          }
          answer.push((answer[i - 2] + answer[i - 1]) % MOD);
        } else {
          answer.push(answer[i - 1]);
        }
      }
    }
  }

  console.log(isOk ? answer[answer.length - 1] % MOD : 0);
}

const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const str = input[0];
const len = str.length;

const answer = [];
const set = new Set();
set.add(str);

const dfs = (arr, i, info) => {
  if (i === len) {
    const arrStr = arr.join('');
    if (!set.has(arrStr)) {
      set.add(arrStr);
      answer.push(arrStr);
    }
    return;
  }

  const noAdd = [...arr];
  const add = [...arr, str[i]];

  if (str[i] === '(') {
    dfs(noAdd, i + 1, [...info, false]);
    dfs(add, i + 1, [...info, true]);
  } else if (str[i] === ')') {
    if (info[info.length - 1]) {
      dfs(add, i + 1, info.slice(0, info.length - 1));
    } else {
      dfs(noAdd, i + 1, info.slice(0, info.length - 1));
    }
  } else {
    dfs(add, i + 1, info);
  }
};

dfs([], 0, []);

console.log(answer.sort().join('\n'));

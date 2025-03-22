const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const resultMap = new Map();

const addResultMap = (curMap) => {
  const string = curMap.map((row) => row.join('')).join('');
  resultMap.set(string, '');
};

const isFinished = (curMap, curR, curC, turn) => {
  var answer;
  // 가로
  answer = true;
  for (var c = 0; c < 3; c++) {
    if (curMap[curR][c] !== turn) {
      answer = false;
      break;
    }
  }
  if (answer) {
    return true;
  }
  // 세로
  answer = true;
  for (var r = 0; r < 3; r++) {
    if (curMap[r][curC] !== turn) {
      answer = false;
      break;
    }
  }
  if (answer) {
    return true;
  }
  // 우하향 대각선
  if (curR === curC) {
    answer = true;
    for (var rc = 0; rc < 3; rc++) {
      if (curMap[rc][rc] !== turn) {
        answer = false;
        break;
      }
    }
    if (answer) {
      return true;
    }
  }
  // 우상향 대각선
  if (curR + curC === 2) {
    answer = true;
    for (var c = 0; c < 3; c++) {
      if (curMap[2 - c][c] !== turn) {
        answer = false;
      }
    }
    if (answer) {
      return true;
    }
  }
};

const dfs = (curMap, turn, depth) => {
  if (depth === 9) {
    addResultMap(curMap);
    return;
  }

  for (var r = 0; r < 3; r++) {
    for (var c = 0; c < 3; c++) {
      if (curMap[r][c] !== '.') {
        continue;
      }
      curMap[r][c] = turn;
      if (isFinished(curMap, r, c, turn)) {
        addResultMap(curMap);
        curMap[r][c] = '.';
        continue;
      }
      dfs(curMap, turn === 'X' ? 'O' : 'X', depth + 1);
      curMap[r][c] = '.';
    }
  }
};

dfs(
  Array.from({ length: 3 }, () => Array.from({ length: 3 }, () => '.')),
  'X',
  0
);

const answer = Array.from({ length: input.length - 1 }, () => '');
input.forEach((string, index) => {
  if (string.startsWith('e')) {
    return;
  }

  answer[index] = resultMap.has(string) ? 'valid\n' : 'invalid\n';
});

console.log(answer.join(''));

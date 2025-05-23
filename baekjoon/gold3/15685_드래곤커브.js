const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const dr = [0, -1, 0, 1],
  dc = [1, 0, -1, 0];

let [N, ...infos] = input;
N = parseInt(N);

const isIn = (r, c) => {
  return r >= 0 && r < 100 && c >= 0 && c < 100;
};

const map = Array.from({ length: 101 }, () => Array.from({ length: 101 }, () => false));

infos.forEach((info) => {
  let [c, r, i, g] = info.split(' ').map(Number);
  map[r][c] = true;
  (r += dr[i]), (c += dc[i]);

  map[r][c] = true;
  const stack = [i];
  for (let step = 0; step < g; step++) {
    for (let idx = stack.length - 1; idx >= 0; idx--) {
      i = (stack[idx] + 1) % 4;
      (r += dr[i]), (c += dc[i]);
      map[r][c] = true;
      stack.push(i);
    }
  }
});

let answer = 0;

for (let r = 0; r < 100; r++) {
  for (let c = 0; c < 100; c++) {
    if (map[r][c] && map[r + 1][c] && map[r][c + 1] && map[r + 1][c + 1]) {
      answer++;
    }
  }
}

console.log(answer);

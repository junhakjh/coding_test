const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const dr = [-1, 1, 0, 0],
  dc = [0, 0, 1, -1];
const UP = 0,
  DOWN = 1,
  RIGHT = 2,
  LEFT = 3;

class Shark {
  constructor(r, c, speed, direction, size) {
    this.r = r;
    this.c = c;
    this.speed = speed;
    this.direction = direction;
    this.size = size;
  }
}

const [info, ...sharks] = input;
const [R, C, M] = info.split(' ');

var map = Array.from({ length: R }, () => Array.from({ length: C }, () => null));

sharks.forEach((_shark) => {
  const [r, c, speed, direction, size] = _shark.split(' ').map(Number);
  // 속력은 가로, 세로를 왕복하는 숫자로 나눈 나머지. 어차피 왕복하면 제자리이기 때문
  const shark = new Shark(r - 1, c - 1, speed % (2 * (direction > 2 ? C - 1 : R - 1)), direction - 1, size);
  map[r - 1][c - 1] = shark;
});

var answer = 0;

const catchShark = (c) => {
  for (var r = 0; r < R; r++) {
    const shark = map[r][c];
    if (shark) {
      answer += shark.size;
      map[r][c] = null;
      break;
    }
  }
};

const moveShark = () => {
  const newMap = Array.from({ length: R }, () => Array.from({ length: C }, () => null));
  for (var r = 0; r < R; r++) {
    for (var c = 0; c < C; c++) {
      const shark = map[r][c];
      if (!shark) {
        continue;
      }
      shark.r += dr[shark.direction] * shark.speed;
      shark.c += dc[shark.direction] * shark.speed;
      if (shark.r < 0) {
        shark.r = Math.abs(shark.r);
        shark.direction = DOWN;
      }
      if (shark.r >= R) {
        shark.r = R - 1 - (shark.r - (R - 1));
        shark.direction = UP;
      }
      if (shark.r < 0) {
        shark.r = Math.abs(shark.r);
        shark.direction = DOWN;
      }
      if (shark.r >= R) {
        shark.r = R - 1 - (shark.r - (R - 1));
        shark.direction = UP;
      }
      if (shark.c < 0) {
        shark.c = Math.abs(shark.c);
        shark.direction = RIGHT;
      }
      if (shark.c >= C) {
        shark.c = C - 1 - (shark.c - (C - 1));
        shark.direction = LEFT;
      }
      if (shark.c < 0) {
        shark.c = Math.abs(shark.c);
        shark.direction = RIGHT;
      }
      if (shark.c >= C) {
        shark.c = C - 1 - (shark.c - (C - 1));
        shark.direction = LEFT;
      }
      const metShark = newMap[shark.r][shark.c];
      if (!metShark || metShark.size < shark.size) {
        newMap[shark.r][shark.c] = shark;
      }
    }
  }
  map = newMap;
};

for (var i = 0; i < C; i++) {
  catchShark(i);
  moveShark();
}

console.log(answer);

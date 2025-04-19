const input = require('fs')
  .readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
  .toString()
  .trim()
  .split('\n');

const [N, M, K] = input[0].split(' ').map(Number);
const buildingNeeds = new Map();
const buildingTo = new Map();

for (var i = 1; i <= N; i++) {
  buildingTo.set(i, []);
}

for (var i = 1; i < 1 + M; i++) {
  const [a, b] = input[i].split(' ').map(Number);
  if (!buildingNeeds.has(b)) {
    buildingNeeds.set(b, new Set());
  }
  buildingNeeds.get(b).add(a);
  buildingTo.get(a).push(b);
}

const buildingNums = Array.from({ length: N + 1 }, () => 0);

var isLie = false;
for (var turn = 1 + M; turn < 1 + M + K; turn++) {
  const [action, building] = input[turn].split(' ').map(Number);
  if (action === 1) {
    if (buildingNeeds.has(building)) {
      isLie = true;
      break;
    }
    if (buildingNums[building] === 0) {
      buildingTo.get(building).forEach((num) => {
        if (buildingNeeds.has(num)) {
          buildingNeeds.get(num).delete(building);
          if (buildingNeeds.get(num).size === 0) {
            buildingNeeds.delete(num);
          }
        }
      });
    }
    buildingNums[building] += 1;
  } else {
    if (buildingNums[building] === 0) {
      isLie = true;
      break;
    }
    buildingNums[building] -= 1;
    if (buildingNums[building] === 0) {
      buildingTo.get(building).forEach((num) => {
        if (!buildingNeeds.has(num)) {
          buildingNeeds.set(num, new Set());
        }
        buildingNeeds.get(num).add(building);
      });
    }
  }
}

console.log(isLie ? 'Lier!' : 'King-God-Emperor');

const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let [n, k] = input[0].split(' ').map(item => Number(item));
let scores = input[1].split(' ').map(item => Number(item));

for(let i = 0; i < k; i++) {
    let [l, r] = input[i + 2].split(' ').map(item => Number(item));
    let sum = 0;
    for(let j = l - 1; j < r; j++) {
        sum += scores[j]
    }
    console.log((sum / (r - l + 1)).toFixed(2))
}

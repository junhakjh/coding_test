const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const n = Number(input[0]);
let matrix = [];
for(let i = 0; i < n; i++) {
    matrix.push(input[i + 1].split(' ').map(item => Number(item)));
}

function solution() {
    let answer = 0;

    let dp = [];
    for(let i = 0; i < n + 1; i++) {
        dp.push(new Array(n + 1).fill(0));
    }
    
    for(let y = 1; y < n + 1; y++) {
        for(let x = 1; x < n + 1; x++) {
            dp[y][x] = Math.max(dp[y - 1][x], dp[y][x - 1]) + matrix[y - 1][x - 1];
        }
    }

    let rdp = [];
    for(let i = 0; i < n + 2; i++) {
        rdp.push(new Array(n + 2).fill(0));
    }
    for(let y = n; y > 0; y--) {
        for(let x = n; x > 0; x--) {
            rdp[y][x] = Math.max(rdp[y + 1][x], rdp[y][x + 1]) + matrix[y - 1][x - 1];
        }
    }

    for(let y = 1; y < n + 1; y++) {
        for(let x = 1; x < n + 1; x++) {
            answer = Math.max(answer, dp[y][x] + rdp[y][x])
        }
    }

    return answer;
}

console.log(solution());

const fs = require('fs');
const input = fs.readFileSync("/dev/stdin").toString().trim().split('\n');

let [n, arr] = input;
arr = arr.split(' ').map(item => Number(item));

let nums = new Array(101).fill(0);

arr.forEach(num => {
        for(let i = 2; i <= 100; i++) {
            if(i > num) break;
            if(num % i === 0) {
                nums[i]++;
            }
        }
    }
);

console.log(Math.max(...nums))

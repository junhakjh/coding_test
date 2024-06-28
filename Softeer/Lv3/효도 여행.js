const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const [n, m] = input[0].split(' ').map(item => Number(item));
const string = input[1];
const nodeDict = {};
for(let i = 0; i < n - 1; i++) {
    const arr = input[i + 2].split(' ');
    const a = Number(arr[0]);
    const b = Number(arr[1]);
    const char = arr[2];
    if(a in nodeDict) {
        nodeDict[a].push([b, char]);
    } else {
        nodeDict[a] = [[b, char]];
    }
    if(b in nodeDict) {
        nodeDict[b].push([a, char]);
    } else {
        nodeDict[b] = [[a, char]];
    }
}

function solution() {
    let answer = 0;

    let visited = new Set([1])
    let q = [[1, new Array(m + 1).fill(0)]];
    while(q.length > 0) {
        let [node, dp] = q.shift();
        answer = Math.max(answer, dp[dp.length - 1])
        for(let i = 0; i < nodeDict[node].length; i++) {
            const [nNode, nChar] = nodeDict[node][i];
            if(visited.has(nNode)) {
                continue;
            }
            visited.add(nNode);
            let newDp = new Array(m + 1).fill(0);
            for(let j = 0; j < m; j++) {
                if(string[j] === nChar) {
                    newDp[j + 1] = dp[j] + 1;
                }
                else {
                    newDp[j + 1] = Math.max(dp[j + 1], newDp[j]);
                }
            }
            q.push([nNode, newDp])
        }
    }
    
    return answer;
}

console.log(solution());

const fs = require('fs')
const input = fs.readFileSync('/dev/stdin').toString().split('\n')
let n = Number(input[0])
let matrix = []
for(let i = 0; i < n; i++) {
    matrix.push(input[i + 1].split(' ').map(item => Number(item)))
}

const dx = [1, 0, -1, 0]
const dy = [0, 1, 0, -1]

function solution() {
    let answer = 0

    if(n === 2) {
        return matrix.reduce((init, arr) => init + arr.reduce((a, b) => a + b, 0), 0)
    }

    function dfs(sum, visited, depth) {
        if(depth == 4) {
            return
        }

        for(let y = 0; y < n; y++) {
            for(let x = 0; x < n; x++) {
                if(visited[y][x]) {
                    continue
                }
                for(let i = 0; i < 4; i++) {
                    let nx = x + dx[i]
                    let ny = y + dy[i]
                    if(nx < 0 || nx >= n || ny <0 || ny >= n) {
                        continue
                    }
                    if(visited[ny][nx]) {
                        continue
                    }
                    let newSum = sum + matrix[y][x] + matrix[ny][nx]
                    answer = Math.max(answer, newSum)
                    visited[y][x] = true
                    visited[ny][nx] = true
                    dfs(newSum, visited, depth + 1)
                    visited[y][x] = false
                    visited[ny][nx] = false
                }
            }
        }        
    }

    let visited = []
    for(let i = 0; i < n; i++) {
        visited.push((new Array(n).fill(false)))
    }
    dfs(0, visited, 0)

    return answer
}

console.log(solution())

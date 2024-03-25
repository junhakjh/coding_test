function solution(i_board) {
    var answer = -1;
    var start = [0, 0];
    var board = i_board.map(str => [...str])
    var w = board[0].length
    var h = board.length
    
    board.forEach((str, idx) => {
        var temp = str.indexOf('R')
        if(temp !== -1) {
            start = [idx, temp]
        }
    })
    
    var dx = [0, 1, 0, -1];
    var dy = [1, 0, -1, 0];
    var q = [[...start, 0]];
    while (q.length !== 0) {
        var cur = q.shift()
        if (board[cur[0]][cur[1]] === 'G') {
            answer = cur[2];
            break;
        }
        board[cur[0]][cur[1]] = 'X';
        
        for (var i = 0; i < 4; i++) {
            var nx = cur[1] + dx[i]
            var ny = cur[0] + dy[i]
            if (nx >= 0 && nx < w && ny >= 0 && ny < h) {
                switch(i) {
                    case 0:
                        while (ny < h) {
                            if (board[ny][nx] === 'D') break;
                            ny += 1;
                        }
                        ny -= 1
                        break;
                    case 1:
                        while (nx < w) {
                            if (board[ny][nx] === 'D') break;
                            nx += 1;
                        }
                        nx -= 1
                        break;
                    case 2:
                        while (ny >= 0) {
                            if (board[ny][nx] === 'D') break;
                            ny -= 1;
                        }
                        ny += 1
                        break;
                    case 3:
                        while (nx >= 0) {
                            if (board[ny][nx] === 'D') break;
                            nx -= 1;
                        }
                        nx += 1
                        break;
                }
                if (board[ny][nx] !== 'D' && board[ny][nx] !== 'X') {
                    q.push([ny, nx, cur[2] + 1])
                }
            }
        }
    }
    
    return answer;
}

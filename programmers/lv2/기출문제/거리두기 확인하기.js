const PERSON = 0, EMPTY = 1, PARTITION = 2;
const mapper = {
    'P': PERSON,
    'O': EMPTY,
    'X': PARTITION,
}

const dr = [-1, 1, 0, 0], dc = [0, 0, -1, 1];
const dr2 = [-2, -1, 0, 1, 2, 1, 0, -1], dc2 = [0, 1, 2, 1, 0, -1, -2, -1];

const isIn = (r, c) => {
    return r >= 0 && r < 5 && c >= 0 && c < 5;
}

const check = (map, r, c) => {
    for(var i = 0; i < 4; i++) {
        const nr = r + dr[i], nc = c + dc[i];
        if(isIn(nr, nc) && map[nr][nc] === PERSON) {
            return false;
        }
    }
    
    for(var i = 0; i < 8; i++) {
        const nnr = r + dr2[i], nnc = c + dc2[i];
        if(isIn(nnr, nnc) && map[nnr][nnc] === PERSON) {
            if(i % 2 === 0) {
                const nr = r + (dr2[i] / 2), nc = c + (dc2[i] / 2);
                if(map[nr][nc] !== PARTITION) {
                    return false;
                }
            } else {
                if(!(map[nnr][c] === PARTITION && map[r][nnc] === PARTITION)) {
                    return false;
                }
            }
        }
    }
        
    return true;
}

function solution(places) {
    var answer = [];
    
    places.forEach(place => {
        const map = Array.from({length: 5}, () => Array.from({length: 5}, () => 0));
        place.forEach((row, r) => {
            for(var c = 0; c < 5; c++) {
                map[r][c] = mapper[row.charAt(c)];
            }
        })
        
        for(var r = 0; r < 5; r++) {
            for(var c = 0; c < 5; c++) {
                if(map[r][c] === PERSON && !check(map, r, c)) {
                    answer.push(0);
                    return;
                }
            }
        }
        
        answer.push(1);
    })
    
    return answer;
}

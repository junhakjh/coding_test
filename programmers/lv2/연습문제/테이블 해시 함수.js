function solution(data, col, row_begin, row_end) {
    var answer = 0;
    
    data.sort((a, b) => {
        if(a[col - 1] === b[col - 1]) {
            return b[0] - a[0];
        } else {
            return a[col -1] - b[col - 1]
        }
    })
    
    for(var i = row_begin - 1; i < row_end; i++) {
        var modSum = data[i].reduce((cur, item) => {
            return cur + item % (i + 1);
        }, 0)
        answer = answer ^ modSum;
    }
    
    return answer;
}

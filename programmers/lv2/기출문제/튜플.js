function solution(s) {
    var answer = [];
    
    var num = 0;
    const map = new Map();
    for(var c = 0; c < s.length; c++) {
        const char = s.charAt(c);
        if(!isNaN(char)) {
            num = num * 10 + parseInt(char);
            continue;
        }
        if(char === '}' || char === ',') {
            if(num !== 0) {
                if(map.has(num)) {
                    map.set(num, map.get(num) + 1);
                } else {
                    map.set(num, 1);
                }
            }
        }
        num = 0;
    }
    
    const arr = [];
    for(const [key, value] of map.entries()) {
        arr.push([key, value]);
    }
    
    arr.sort((a, b) => b[1] - a[1]);
    
    answer = arr.map(item => item[0]);
    
    return answer;
}

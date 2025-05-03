function solution(relation) {    
    const len = relation[0].length;
    const candidates = [];
    
    const comb = (num, curNum, arr, index) => {
        if(curNum === num) {
            var bit = 0;
            arr.forEach((item, idx) => {
                bit += (item << idx);
            })
            var flag = false;
            candidates.forEach(cand => {
                if((cand & bit) === cand) {
                    flag = true;
                    return;
                }
            })
            if(flag) {
                return;
            }
            const set = new Set();
            relation.forEach(rel => {
                var strArr = [];
                arr.forEach((item, idx) => {
                    if(item === 1) {
                        strArr.push(rel[idx]);
                    }
                })
                set.add(strArr.join(''));
            })
            if(set.size === relation.length) {
                candidates.push(bit);
            }
            
            return;
        }
        if(index === arr.length) {
            return;
        }
        
        arr[index] = 1;
        comb(num, curNum + 1, arr, index + 1);
        arr[index] = 0;
        comb(num, curNum, arr, index + 1);
    }
    
    for(var i = 1; i <= len; i++) {
        comb(i, 0, Array.from({length: len}, () => 0), 0);
    }
    
    return candidates.length;
}

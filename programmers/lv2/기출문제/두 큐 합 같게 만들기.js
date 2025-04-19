function solution(queue1, queue2) {
    var answer = 0;
    
    const len = queue1.length * 2;
    var idx1 = 0, idx2 = 0;
    var sum1 = queue1.reduce((acc, item) => acc + item, 0),
        sum2 = queue2.reduce((acc, item) => acc + item, 0);
    while (sum1 !== sum2) {
        if (sum1 > sum2) {
            if (idx1 === len) {
                return -1;
            }
            const num = queue1[idx1++];
            sum1 -= num;
            sum2 += num;
            queue2.push(num);
        } else {
            if (idx2 === len) {
                return -1;
            }
            const num = queue2[idx2++];
            sum2 -= num;
            sum1 += num;
            queue1.push(num);
        }
        answer += 1;
    }
    
    return answer;
}

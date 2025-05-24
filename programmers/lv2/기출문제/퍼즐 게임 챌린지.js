function solution(diffs, times, limit) {
    var answer = 0;
    
    var left = Number.MAX_SAFE_INTEGER, right = 0;
    diffs.forEach(diff => {
        left = Math.min(left, diff);
        right = Math.max(right, diff);
    });
    
    const len = diffs.length;
    label: while(left < right) {
        const level = Math.floor((left + right) / 2);
        var prevTime = 0, totalTime = 0;
        for(var i = 0; i < len; i++) {
            const diff = diffs[i];
            const curTime = times[i];
            if(level < diff) {
                totalTime += (prevTime * (diff - level)) + (curTime * (diff - level + 1));
            } else {
                totalTime += curTime;
            }
            prevTime = curTime;
            if(totalTime > limit) {
                left = level + 1;
                continue label;
            }
        }
        right = level;
    }
    
    return left;
}

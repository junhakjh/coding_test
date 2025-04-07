const getSec = (time) => {
    return time.split(":").map(Number).reduce((acc, cur, idx) => {
        if(idx === 0) {
            return acc + cur * 60 * 60;
        } else if(idx === 1) {
            return acc + cur * 60;
        } else {
            return acc + cur;
        }
    }, 0)
}

function solution(play_time, adv_time, logs) {
    var answer = Number.MAX_SAFE_INTEGER;
    
    const playSec = getSec(play_time), advSec = getSec(adv_time);
    
    const imosMap = new Map();
    logs.forEach(log => {
        const [startTime, endTime] = log.split("-");
        const startSec = getSec(startTime), endSec = getSec(endTime);
        if(imosMap.has(startSec)) {
            imosMap.set(startSec, imosMap.get(startSec) + 1);
        } else {
            imosMap.set(startSec, 1);
        }
        if(imosMap.has(endSec)) {
            imosMap.set(endSec, imosMap.get(endSec) - 1);
        } else {
            imosMap.set(endSec, -1);
        }
    });
    
    const secSumMap = new Map();
    secSumMap.set(0, 0);
    var sortedSec = Array.from(imosMap.keys());
    sortedSec.sort((a, b) => a > b ? 1 : -1);
    var num = 0, prevSec = 0, secSum = 0;
    sortedSec.forEach((sec, idx) => {
        if(idx === 0) {
            secSumMap.set(sec, 0);
        } else {
            secSum += (sec - prevSec) * num
            secSumMap.set(sec, secSum);
        }
        imosMap.set(sec, imosMap.get(sec) + num);
        num = imosMap.get(sec);
        prevSec = sec;
    })
    
    const findIdx = (sec) => {
        var l = 0, r = sortedSec.length - 1, mid = Math.floor((l + r) / 2);
        while(l < r) {
            mid = Math.floor((l + r) / 2);
            if(sortedSec[mid] >= sec) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }
    
    var maxSec = 0;
    sortedSec = [0, ...sortedSec];
    sortedSec.forEach((sec, idx) => {
        if(idx < sortedSec.length - 1) {
            const endSec = sec + advSec;
            const endIdx = findIdx(endSec);
            const startSecSum = secSumMap.get(sec), endSecSum = secSumMap.get(sortedSec[endIdx]);
            var secSum = endSecSum - startSecSum;
            if(endSec < sortedSec[endIdx]) {
                secSum -= (endSecSum - secSumMap.get(sortedSec[endIdx - 1])) * ((sortedSec[endIdx] - endSec) / (sortedSec[endIdx] - sortedSec[endIdx - 1]))
            }
            if(maxSec < secSum || (maxSec === secSum && Math.min(sec, playSec - advSec) < answer)) {
                maxSec = secSum;
                answer = Math.min(sec, playSec - advSec);
            }
        }
        
        if(idx > 0) {
            const startSec = Math.max(sec - advSec, 0);
            const startIdx = findIdx(startSec);
            const startSecSum = secSumMap.get(sortedSec[startIdx]), endSecSum = secSumMap.get(sec);
            var secSum = endSecSum - startSecSum;
            if(startSec < sortedSec[startIdx]) {
                secSum += (startSecSum - secSumMap.get(sortedSec[startIdx - 1])) * ((sortedSec[startIdx] - startSec) / (sortedSec[startIdx] - sortedSec[startIdx - 1]))
            }
            if(maxSec < secSum || (maxSec === secSum && startSec < answer)) {
                maxSec = secSum;
                answer = startSec;
            }
        }
    });
    
    const hour = Math.floor(answer / (60 * 60));
    const minute = Math.floor((answer % (60 * 60)) / 60);
    const second = answer % 60;
    return (hour < 10 ? "0" : "") + hour + ":" + (minute < 10 ? "0" : "") + minute + ":" + (second < 10 ? "0": "") + second;
}

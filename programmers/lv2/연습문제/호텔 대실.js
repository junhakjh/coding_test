function convertMinute(time) {
    const [hh, mm] = time.split(":").map(Number);
    return hh * 60 + mm;
}

function solution(bookTime) {    
    const minutes = [];
    for(const timeArr of bookTime) {
        minutes.push([convertMinute(timeArr[0]), convertMinute(timeArr[1])]);
    }
    minutes.sort((a, b) => {
        if(a[0] === b[0]) {
            return a[1] - b[1];
        }
        return a[0] - b[0];
    })
    
    const endTimes = [];
    for(const [start, end] of minutes) {
        var flag = false;
        for(var i = 0; i < endTimes.length; i++) {
            if(endTimes[i] + 10 <= start) {
                flag = true;
                endTimes[i] = end;
                break;
            }
        }
        if(!flag) {
            endTimes.push(end);
        }
    }
    
    return endTimes.length;
}

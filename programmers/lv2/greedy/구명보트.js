function solution(people, limit) {
    var answer = 0;
    
    people.sort((a, b) => a - b);
    var l = 0, r = people.length - 1;
    
    while(l <= r) {
        if(people[l] + people[r] <= limit) {
            l++;
        }
        r--;
        
        answer++;
    }
    
    return answer;
}

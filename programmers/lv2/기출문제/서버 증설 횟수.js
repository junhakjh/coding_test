function solution(players, m, k) {
    var answer = 0;
    
    var curServer = 0;
    var imos = Array.from({length: 24}, () => 0);
    for(var time = 0; time < 24; time++) {
        curServer += imos[time];
        const needs = Math.floor(players[time] / m);
        if(needs > curServer) {
            imos[time + k] -= needs - curServer;
            answer += needs - curServer;
            curServer = needs;
        }
    }
    
    return answer;
}

const DONUT = 1, STICK = 2, EIGHT = 3;

function solution(edges) {
    var answer = [0, 0, 0, 0];
    
    const nodeStartMap = new Map(), nodeEndMap = new Map();
    edges.forEach(edge => {
        const [start, end] = edge;
        if(!nodeStartMap.has(start)) {
            nodeStartMap.set(start, 0);
        }
        if(!nodeStartMap.has(end)) {
            nodeStartMap.set(end, 0);
        }
        if(!nodeEndMap.has(start)) {
            nodeEndMap.set(start, 0);
        }
        if(!nodeEndMap.has(end)) {
            nodeEndMap.set(end, 0);
        }
        nodeStartMap.set(start, nodeStartMap.get(start) + 1);
        nodeEndMap.set(end, nodeEndMap.get(end) + 1);

    })
    
    var startNode = -1;
    for(const node of nodeStartMap.keys()) {
        if(node <= 0) {
            break;
        }
        if(nodeStartMap.get(node) > 1 && nodeEndMap.get(node) === 0) {
            startNode = node;
            answer[0] = startNode;
            break;
        }
    }
    
    var graphNum = nodeStartMap.get(startNode);
    for(const [key, value] of nodeStartMap.entries()) {
        if(key === startNode) {
            continue;
        }
        if(value === 2) {
            answer[EIGHT]++;
            graphNum--;
        } else if(value === 0) {
            answer[STICK]++;
            graphNum--;
        }
    }
    answer[DONUT] += graphNum;
    
    return answer;
}

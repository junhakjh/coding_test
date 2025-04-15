const dr = [-1, 1, 0, 0], dc = [0, 0, -1, 1];

class Robot {
    constructor(r, c, route) {
        this.r = r;
        this.c = c;
        this.target = 1;
        this.route = route;
    }
}

class Queue {
    constructor() {
        this.map = new Map();
        this.front = 0;
        this.rear = 0;
    }
    
    size() {
        return this.map.size;
    }
    
    isEmpty() {
        return this.size() === 0;
    }
    
    offer(value) {
        this.map.set(this.rear++, value);
    }
    
    poll() {
        const value = this.map.get(this.front);
        this.map.delete(this.front++);
        return value;
    }
}

const isIn = (r, c) => {
    return r >= 0 && r < 100 && c >= 0 && c < 100;
}

function solution(points, routes) {  
    var answer = 0;
    
    const q = new Queue();
    routes.forEach(route => {
        q.offer(new Robot(points[route[0] - 1][0] - 1, points[route[0] - 1][1] - 1, route))
    })
    
    while(!q.isEmpty()) {
        const set = new Set();
        const dupSet = new Set();
        
        const qSize = q.size();
        for(var _ = 0; _ < qSize; _++) {
            const robot = q.poll();
            const id = robot.r * 100 + robot.c;
            if(set.has(id)) {
                dupSet.add(id);
            } else {
                set.add(id);
            }
            if(robot.r === points[robot.route[robot.target] - 1][0] - 1 && robot.c === points[robot.route[robot.target] - 1][1] - 1) {
                if(robot.target === robot.route.length - 1) {
                    continue;
                }
                robot.target++;
            }
            const targetR = points[robot.route[robot.target] - 1][0] - 1, targetC = points[robot.route[robot.target] - 1][1] - 1;
            const dist = Math.abs(targetR - robot.r) + Math.abs(targetC - robot.c);
            for(var i = 0; i < 4; i++) {
                const nr = robot.r + dr[i], nc = robot.c + dc[i];
                if(isIn(nr, nc) && Math.abs(targetR - nr) + Math.abs(targetC - nc) < dist) {
                    robot.r = nr;
                    robot.c = nc;
                    break;
                }
            }
            q.offer(robot);
        }
        
        answer += dupSet.size;
    }
    
    return answer;
}

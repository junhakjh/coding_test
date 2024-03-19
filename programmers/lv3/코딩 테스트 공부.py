def solution(alp, cop, problems):
    target_alp = target_cop = 0
    
    for problem in problems:
        target_alp = max(target_alp, problem[0] - alp)
        target_cop = max(target_cop, problem[1] - cop)
    
    dp = [[(float('inf')) for _ in range(target_cop + 1)] for _ in range(target_alp + 1)]
    dp[0][0] = 0
    
    for cur_alp in range(len(dp)):
        for cur_cop in range(len(dp[0])):
            if cur_alp < target_alp:
                dp[cur_alp + 1][cur_cop] = min(dp[cur_alp][cur_cop] + 1, dp[cur_alp + 1][cur_cop])
            if cur_cop < target_cop:
                dp[cur_alp][cur_cop + 1] = min(dp[cur_alp][cur_cop] + 1, dp[cur_alp][cur_cop + 1])
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if (alp + cur_alp) >= alp_req and (cop + cur_cop) >= cop_req:
                    new_alp = min(cur_alp + alp_rwd, target_alp)
                    new_cop = min(cur_cop + cop_rwd, target_cop)
                    dp[new_alp][new_cop] = min(dp[new_alp][new_cop], dp[cur_alp][cur_cop] + cost)
    
    return dp[target_alp][target_cop]

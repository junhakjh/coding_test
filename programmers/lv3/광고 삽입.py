def solution(play_time, adv_time, logs):
    answer = ''
    pt_arr, at_arr = list(map(int, play_time.split(':'))), list(map(int, adv_time.split(':')))
    pt, at = pt_arr[0] * 60 * 60 + pt_arr[1] * 60 + pt_arr[2], at_arr[0] * 60 * 60 + at_arr[1] * 60 + at_arr[2]
    
    dp = [0] * (pt + 2)
    
    for log in logs:
        start, end = log.split('-')
        st_arr, et_arr = list(map(int, start.split(':'))), list(map(int, end.split(':')))
        st, et = st_arr[0] * 60 * 60 + st_arr[1] * 60 + st_arr[2], et_arr[0] * 60 * 60 + et_arr[1] * 60 + et_arr[2]
        dp[st] += 1
        dp[et] -= 1
    
    max_sum = cur_sum = dp[0]
    answer_et = 0
    
    for i in range(1, pt + 1):
        dp[i] += dp[i - 1]
        cur_sum += dp[i]
        if i >= at:
            cur_sum -= dp[i - at]
        if cur_sum > max_sum:
            max_sum = cur_sum
            answer_et = i - at + 1
    
    if answer_et < 0:
        answer = '00:00:00'
    else:
        answer = str(answer_et // 3600).zfill(2) + ':' + str((answer_et % 3600) // 60).zfill(2) + ':' + str((answer_et % 3600) % 60).zfill(2)
    
    return answer

import heapq, math

def solution(fees, records):
    answer = []
    
    basic_m, basic_fee, unit_m, unit_fee = fees
    parking_info = {}
    heap = []
    heapq.heapify(heap)
    
    for record in records:
        time, car, method = record.split(' ')
        t, m = time.split(':')
        t, m = int(t), int(m)
        if method == "IN":
            if car in parking_info:
                parking_info[car] = {'t': t, 'm': m, 'm_sum': parking_info[car]['m_sum'], 'fee': parking_info[car]['fee'], 'isIN': True}
            else:
                parking_info[car] = {'t': t, 'm': m, 'm_sum': 0, 'fee': 0, 'isIN': True}
        else:
            m_sum = (t - parking_info[car]['t']) * 60 + (m - parking_info[car]['m']) + parking_info[car]['m_sum']
            if m_sum <= basic_m:
                parking_info[car]['fee'] = basic_fee
            else:
                parking_info[car]['fee'] = basic_fee + math.ceil((m_sum - basic_m) / unit_m) * unit_fee
            parking_info[car]['isIN'] = False
            parking_info[car]['m_sum'] = m_sum

    for car in parking_info:
        if not parking_info[car]['isIN']:
            continue
        m_sum = (23 - parking_info[car]['t']) * 60 + (59 - parking_info[car]['m']) + parking_info[car]['m_sum']
        if m_sum <= basic_m:
            parking_info[car]["fee"] = basic_fee
        else:
            parking_info[car]["fee"] = basic_fee + math.ceil((m_sum - basic_m) / unit_m) * unit_fee
                
    for car in parking_info:
        heapq.heappush(heap, [car, parking_info[car]['fee']])
    while len(heap) > 0:
        answer.append(heapq.heappop(heap)[1])
    
    return answer

def solution(coin, cards):
    card_len = len(cards)
    answer, i = 0, card_len // 3
    target, base_cards, added_cards = card_len + 1, set(), set()
    for card in cards[:card_len // 3]:
        base_cards.add(card)
    
    while i <= card_len:
        answer += 1
        if i == card_len: break
        
        added_cards.add(cards[i])
        added_cards.add(cards[i + 1])
        i += 2
        
        check = False
        for card in base_cards:
            if target - card in base_cards:
                base_cards.remove(card)
                base_cards.remove(target - card)
                check = True
                break
        if check: continue
        
        if coin > 0:
            for card in base_cards:
                if target - card in added_cards:
                    check = True
                    base_cards.remove(card)
                    added_cards.remove(target - card)
                    coin -= 1
                    break
            if not check and coin > 1:
                for card in added_cards:
                    if target - card in added_cards:
                        check = True
                        added_cards.remove(card)
                        added_cards.remove(target - card)
                        coin -= 2
                        break
                        
        if not check: break
    
    return answer

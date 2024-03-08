from collections import defaultdict

def solution(participant, completion):
    player = defaultdict(int)
    for p in participant:
        player[p] += 1
    
    for c in completion:
        player[c] -= 1
    
    for i in participant:
        if player[i] > 0:
            return i
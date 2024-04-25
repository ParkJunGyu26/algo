def solution(i):
    cnt = 0

    for num in range(1, i+1):
        target = 0

        for s in str(num):
            target += int(s)
        
        if num % target == 0:
            cnt += 1
    
    return cnt

import sys
input = sys.stdin.readline

n = int(input())
print(solution(n))
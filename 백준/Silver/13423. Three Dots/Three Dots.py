import sys
from collections import *

input = sys.stdin.readline

t = int(input())
for _ in range(t):

    n = int(input())
    li = sorted(list(map(int, input().split())))
    spotList = defaultdict(int)
    for i in range(n):
        spotList[li[i]] = 1

    ans = 0
    for i in range(n-1):
        for j in range(i+1, n):
            first = li[i]
            second = li[j]
            third = li[j] + (li[j] - li[i])
            if spotList[third] == 1:
                ans += 1
    
    print(ans)
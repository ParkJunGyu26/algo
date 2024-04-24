from heapq import heappop, heappush
import sys
input = sys.stdin.readline

n = int(input())
hq = []
for i in range(n):
    heappush(hq, int(input()))

ans = 0

if len(hq) == 1:
    print(0)
else:
    while len(hq) > 1:
        # print("start")
        # print("hq : ", hq)
        # print("ans : ", ans)
        num1 = heappop(hq)
        num2 = heappop(hq)
        ans += (num1 + num2)
        heappush(hq, num1+num2)
        # print("hq : ", hq)
        # print("ans : ", ans)
        # print("end")
        
    print(ans)
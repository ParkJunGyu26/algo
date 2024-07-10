import sys
input = sys.stdin.readline

# 에라토스테네스의 체
sosu = [True] * 10001
sosu[0], sosu[1] = False, False

for i in range(2, 10001):
    for j in range(i*2, 10001, i):
        if sosu[j]: sosu[j] = False

t = int(input())
for i in range(t):
    n = int(input())
    ans = []
    for i in range(n+1):
        if sosu[i] and sosu[n-i]:
            ans.append((i, n-i))
    
    diff = 10000
    ans1, ans2 = 0, 0
    for a, b in ans:
        if diff > abs(a-b):
            ans1 = a
            ans2 = b
            diff = abs(a-b)
    print(ans1, ans2)
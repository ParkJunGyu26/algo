import sys

input = sys.stdin.readline

t = int(input())
ans = 0

# mCn 구하기
for _ in range(t):
    n, m = map(int, input().split())
    num1 = 1
    num2 = 1
    num3 = 1
    num4 = 1

    # m!
    for i in range(1, m+1):
        num1 *= i
    
    # (m-n)!
    for i in range(1, m-n+1):
        num2 *= i
    
    # mPn
    num3 = num1 // num2

    # n!
    for i in range(1, n+1):
        num4 *= i
    
    # mCn
    ans = num3 // num4
    
    print(ans)
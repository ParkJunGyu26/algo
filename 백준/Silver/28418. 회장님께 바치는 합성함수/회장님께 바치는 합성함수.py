import sys
input = sys.stdin.readline

f = list(map(int, input().split()))
g = list(map(int, input().split()))

p = [0] * 3
q = [0] * 3

# p 구하기
p[0] = f[0] * (g[0] * g[0])
p[1] = f[0] * (g[0] * g[1] * 2) + f[1] * g[0]
p[2] = f[0] * (g[1] * g[1]) + f[1] * g[1] + f[2]

# q 구하기
q[0] = g[0] * f[0]
q[1] = g[0] * f[1]
q[2] = g[0] * f[2] + g[1]

ans = [p[0]-q[0], p[1]-q[1], p[2]-q[2]]

if ans[0] == 0:
    if ans[1] == 0:
        if ans[2] == 0:
            print("Nice")
        else:
            print("Head on")
    else:
        print("Remember my character")
else:
    D = ans[1] * ans[1] - 4 * ans[0] * ans[2]
    if D > 0:
        print("Go ahead")
    elif D == 0:
        print("Remember my character")
    else:
        print("Head on")
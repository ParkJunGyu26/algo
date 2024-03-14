import sys
input = sys.stdin.readline

n = int(input())
ans = 1

def mul(num):
    global ans

    ans *= num

    if num == n:
        return ans
    else:
        mul(num+1)


if n == 0:
    print(1)
else:
    mul(1)
    print(ans)
import sys
input = sys.stdin.readline

def eratos(n, sosu):
    for i in range(2, 2*n+1):
        for j in range(i*2, 2*n+1, i):
            if sosu[j]:
                sosu[j] = False


def solution(n):
    sosu = [True] * (2*n+1)
    sosu[0], sosu[1] = False, False

    eratos(n, sosu)
    ans = 0

    for i in range(n+1, 2*n+1):
        if sosu[i]:
            ans += 1
    
    return ans

while True:
    n = int(input())
    if n == 0:
        break

    ans = solution(n)
    print(ans)
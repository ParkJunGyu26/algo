def solution(n):
    tmp = 0
    tmp += n

    while n:
        tmp += n % 10
        n //= 10
    
    return tmp

ans = [0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0] + [1] * 20000

for i in range(1, 10001):
    check = solution(i)
    ans[check] = 0

for i in range(1, 10001):
    if ans[i]:
        print(i)
# 해당 숫자를 이진수로 표현하자. 그리고 그 이진수의 글자 수만큼 2의 제곱수 해주면 될듯?
# 이진수의 글자 수만큼이 아니라 이진수 길이에서 1이 되는 수만 곱해주면 됨!!!
# n 을 이진수로 표현하는 것이 아니라.. 2^n-1 이니깐, n-1을 이진수로 표현해야되네
n = int(input())

if n <=1:
    print(1)
else:
    n -= 1
    bin = []
    while n > 0:
        bin.append(n % 2)
        n //= 2

    ans = 1
    base = 2
    MOD = 1000000007
    for i in range(len(bin)):
        if bin[i] == 1:
            ans = (ans * base) % MOD
        base = (base * base) % MOD
    print(ans)
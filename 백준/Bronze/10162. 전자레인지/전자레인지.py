T = int(input())

A = 300
B = 60
C = 10

# 10초 단위로만 맞출 수 있으므로, 10으로 나누어 떨어지지 않으면 -1 출력
if T % 10 != 0:
    print(-1)
else:
    a = T // A
    T %= A
    b = T // B
    T %= B
    c = T // C
    print(a, b, c)

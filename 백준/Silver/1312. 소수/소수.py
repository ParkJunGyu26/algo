A, B, N = map(int, input().split())

for _ in range(N):
    A = A % B
    A *= 10 
    ans = A // B

print(ans)
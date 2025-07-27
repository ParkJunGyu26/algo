# 입력 받기
a, b, c = map(int, input().split())

# 세 숫자가 모두 같은 경우
if a == b == c:
    reward = 10000 + a * 1000

# 두 숫자만 같은 경우
elif a == b or a == c:
    reward = 1000 + a * 100
elif b == c:
    reward = 1000 + b * 100

# 모두 다른 경우
else:
    reward = max(a, b, c) * 100

# 결과 출력
print(reward)

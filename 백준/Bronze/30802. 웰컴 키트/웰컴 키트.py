import math

# 입력
N = int(input())
sizes = list(map(int, input().split()))
T, P = map(int, input().split())

# 티셔츠 묶음 수 계산
shirt_bundles = 0
for count in sizes:
    # 각 사이즈별로 필요한 묶음 수 = ceil(count / T)
    shirt_bundles += (count + T - 1) // T  # 올림 대신 이 방법 사용

# 펜 묶음 및 낱개 수 계산
pen_bundles = N // P
pen_singles = N % P

# 출력
print(shirt_bundles)
print(f"{pen_bundles} {pen_singles}")

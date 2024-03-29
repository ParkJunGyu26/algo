import sys
input = sys.stdin.readline

prime_num = []

# 에라토스테네스의 체(2, 3, ... 등 해당 값의 소수들은 모두 없애주기)
# 만약 0일 경우에는 이미 처리된 것이므로 2중 for문 안해도 괜찮음(백트래킹..?)
check = [0 for _ in range(4000001)]
check[0:2] = [1, 1]
for i in range(2, int(4000001**0.5)+1):
    if check[i] == 0:
        for j in range(i*i, 4000001, i):
            check[j] = 1

# 소수인 값들만 저장
prime_num = [idx for idx, val in enumerate(check) if val == 0]

n = int(input())

left, right = 0, 0
sumNum = prime_num[0]

cnt = 0

while left <= right:
    if sumNum == n:
        cnt += 1
        sumNum -= prime_num[left]
        left += 1
    elif sumNum < n:
        # right 가 끝까지 도달 못했을 경우
        if right + 1 < len(prime_num):
            right += 1
            sumNum += prime_num[right]
        # right 가 배열의 끝에 도달했을 경우
        else:
            sumNum -= prime_num[left]
            left += 1
    else:
        sumNum -= prime_num[left]
        left += 1

print(cnt)
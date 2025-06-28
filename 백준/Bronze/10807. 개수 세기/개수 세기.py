# 첫째 줄: 정수의 개수 N
N = int(input())

# 둘째 줄: N개의 정수를 리스트로 입력받음
numbers = list(map(int, input().split()))

# 셋째 줄: 찾으려는 정수 v
v = int(input())

# v의 개수를 세어서 출력
print(numbers.count(v))

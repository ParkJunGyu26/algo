import sys

t = int(sys.stdin.readline())

for i in range(t):

    result = 0

    str = list(sys.stdin.readline().rstrip())

    left_idx = 0
    right_idx = len(str)-1

    while (left_idx < right_idx):

        # 왼쪽 포인터 값과 오른쪽 포인터 값이 동일할 경우
        if (str[left_idx] == str[right_idx]):
            left_idx += 1
            right_idx -= 1

        # 왼쪽 포인터 값과 오른쪽 포인터 값이 동일하지 않을 경우
        else:
            # 왼쪽 포인터를 오른쪽으로 이동했을 때, 양쪽 포인터 내부 문자열이 회문이 되는지 확인
            if (str[left_idx+1:right_idx+1] == str[left_idx+1:right_idx+1][::-1]):
                result = 1
                break

            # 오른쪽 포인터를 왼쪽으로 이동했을 때, 양쪽 포인터 내부 문자열이 회문이 되는지 확인
            elif (str[left_idx:right_idx] == str[left_idx:right_idx][::-1]):
                result = 1
                break

            # 회문이나 유사회문도 아닌 일반 문자열인 경우
            else:
                result = 2
                break

    print(result)
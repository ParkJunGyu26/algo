import sys
input = sys.stdin.readline

def make_res(target):
    for i in range(5):
        for j in range(5):
            if graph[i][j] == target:
                res[i][j] = 1
                return

def bingo_check():
    cnt = 0
    cnt += row_bingo() + column_bingo() + cross_bingo1() + cross_bingo2()

    if cnt >= 3:
        return True
    return False

def row_bingo(): # 세로 검사
    cnt = 0

    for i in range(5):
        check = 0
        for j in range(5):
            if res[j][i] == 1:
                check += 1
        if check == 5:
            cnt += 1
    
    return cnt

def column_bingo(): # 가로 검사
    cnt = 0

    for i in range(5):
        check = 0
        for j in range(5):
            if res[i][j] == 1:
                check += 1
        if check == 5:
            cnt += 1
    
    return cnt

def cross_bingo1(): # 왼쪽에서 오른쪽 대각선
    check = 0

    for i in range(5):
        if res[i][i] == 1:
            check += 1
    
    if check == 5:
        return 1
    return 0

def cross_bingo2(): # 오른쪽에서 왼쪽 대각선
    check = 0

    for i in range(5):
        if res[i][4-i] == 1:
            check += 1
    
    if check == 5:
        return 1
    return 0

graph = [list(map(int, input().split())) for _ in range(5)]
number = [list(map(int, input().split())) for _ in range(5)]
res = [[0] * 5 for _ in range(5)]

ans = 0
tmp = False
for i in range(5):
    if tmp:
        break
    for j in range(5):
        ans += 1
        make_res(number[i][j])
        if bingo_check():
            tmp = True
            break

print(ans)
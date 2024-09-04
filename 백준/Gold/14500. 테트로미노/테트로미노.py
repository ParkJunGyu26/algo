import sys
input = sys.stdin.readline

def make_ans(x, y):
    ans = max(first(x, y), second(x, y), third(x, y), fourth(x, y), fifth(x, y), six(x, y), seven(x, y), eight(x, y), nine(x, y), ten(x, y), eleven(x, y), twelve(x, y), thirteen(x, y), fourteen(x, y), fifteen(x, y), sixteen(x, y), seventeen(x, y), eighteen(x, y), nineteen(x, y))
    return ans

def first(x, y): # 가로로 4칸
    cnt = 0

    if x + 3 < column:
        cnt = sum(graph[y][x:x+4])
    
    return cnt

def second(x, y): # 세로로 4칸
    cnt = 0

    if y + 3 < row:
        for i in range(y, y+4):
            cnt += graph[i][x]
    
    return cnt

def third(x, y): # 가로 2칸, 세로 2칸
    cnt = 0

    if x + 1 < column and y + 1 < row:
        for i in range(y, y+2):
            for j in range(x, x+2):
                cnt += graph[i][j]
    
    return cnt

def fourth(x, y): # 가로 3칸, 세로 1칸
    cnt = 0

    if x + 2 < column and y + 1 < row:
        cnt = sum(graph[y][x:x+3])
        cnt += graph[y+1][x+1]
    
    return cnt

def fifth(x, y): # 가로 1칸, 세로 3칸
    cnt = 0

    if x + 1 < column and y + 2 < row:
        for i in range(y, y+3):
            cnt += graph[i][x]
        cnt += graph[y+2][x+1]
    
    return cnt

def six(x, y):
    cnt = 0
    
    if -1 < x - 1 and y + 2 < row:
        for i in range(y, y+3):
            cnt += graph[i][x]
        cnt += graph[y+2][x-1]
    
    return cnt

def seven(x, y):
    cnt = 0

    if -1 < x - 1 and y + 2 < row:
        for i in range(y, y+3):
            cnt += graph[i][x]
        cnt += graph[y+1][x-1]
    
    return cnt

def eight(x, y):
    cnt = 0

    if x + 2 < column and -1 < y - 1:
        cnt = graph[y-1][x+1]
        cnt += sum(graph[y][x:x+3])
    
    return cnt

def nine(x, y):
    cnt = 0

    if x + 1 < column and y + 2 < row:
        for i in range(y, y+3):
            cnt += graph[i][x]
        cnt += graph[y+1][x+1]
    
    return cnt

def ten(x, y):
    cnt = 0

    if x + 2 < column and y + 1 < row:
        cnt = sum(graph[y][x:x+3])
        cnt += graph[y+1][x]
    
    return cnt

def eleven(x, y):
    cnt = 0

    if x + 2 < column and y + 1 < row:
        cnt = sum(graph[y][x:x+3])
        cnt += graph[y+1][x+2]
    
    return cnt

def twelve(x, y):
    cnt = 0

    if x + 1 < column and y + 2 < row:
        cnt = graph[y][x]
        for i in range(y, y+3):
            cnt += graph[i][x+1]
        
    return cnt

def thirteen(x, y):
    cnt = 0

    if x + 2 < column and -1 < y - 1:
        cnt = sum(graph[y][x:x+3])
        cnt += graph[y-1][x+2]
    
    return cnt

def fourteen(x, y):
    cnt = 0

    if x + 2 < column and y + 1 < row:
        cnt = graph[y][x]
        cnt += sum(graph[y+1][x:x+3])
    
    return cnt

def fifteen(x, y):
    cnt = 0

    if x + 1 < column and y + 2 < row:
        cnt = graph[y][x+1]
        for i in range(y, y+3):
            cnt += graph[i][x]
    
    return cnt

def sixteen(x, y):
    cnt = 0

    if x + 1 < column and y + 2 < row:
        cnt = graph[y][x] + graph[y+1][x] + graph[y+1][x+1] + graph[y+2][x+1]

    return cnt

def seventeen(x, y):
    cnt = 0

    if x + 2 < column and -1 < y-1:
        cnt = graph[y][x] + graph[y][x+1] + graph[y-1][x+1] + graph[y-1][x+2]

    return cnt

def eighteen(x, y):
    cnt = 0

    if x + 1 < column and -1 < y-2:
        cnt = graph[y][x] + graph[y-1][x] + graph[y-1][x+1] + graph[y-2][x+1]

    return cnt

def nineteen(x, y):
    cnt = 0

    if x + 2 < column and y+1 < row:
        cnt = graph[y][x] + graph[y][x+1] + graph[y+1][x+1] + graph[y+1][x+2]

    return cnt

row, column = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(row)]

ans = 0
for i in range(row):
    for j in range(column):
        ans = max(ans, make_ans(j, i))

print(ans)
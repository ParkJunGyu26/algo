n = int(input())
num = [i for i in range(1, n+1)]

while len(num) > 1:
    # print("num1 : ", num)
    tmp = []
    for i in range(len(num)):
        if i % 2 != 0:
            tmp.append(num[i])
    
    num = tmp
    # print("num2 : ", num)
    # print("-----")

print(num[0])
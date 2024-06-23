n = int(input())
ans = [666, 1666, 2666, 3666, 4666, 5666]

if n < 7:
    print(ans[n-1])
else:
    n = n - 6
    answer = ans[5]
    while (n > 0):
        # print("n : ", n)
        # print("answer : ", answer)
        answer += 1
        cnt = 0

        for i in str(answer):
            if i == '6':
                cnt += 1
            else:
                cnt = 0
            
            if cnt == 3:
                break
        
        if cnt == 3:
            n -= 1
        
    print(answer)
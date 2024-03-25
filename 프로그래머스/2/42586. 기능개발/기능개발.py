def solution(progresses, speeds):
    answer = []
    i = 0
    day = 0
    cnt = 0
    while(i < len(progresses)):
        if (progresses[i] + day * speeds[i] >= 100):
            cnt += 1
            i += 1
            if i == len(progresses):
                answer.append(cnt)
        else:
            if (cnt > 0):
                answer.append(cnt)
                cnt = 0
            day += 1
    return answer
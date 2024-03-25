def solution(priorities, location):
    answer = 0
    nums = sorted(priorities)
    i = 0
    length = len(priorities)
    cnt = 1
    while (True):
        if (priorities[i] == max(nums)):
            if i == location:
                answer = cnt
                break
            else:
                priorities[i] = 0
                cnt += 1
                nums.pop()
        
        if i == length-1:
            i = 0
        else:
            i += 1
        
    return answer
def solution(nums):
    answer = 0
    dic = {}
    for n in nums:
        dic[n] = 0
        
    for n in nums:
        dic[n] += 1
    
    answer = min(len(dic.keys()), len(nums)//2)
    
    return answer
#include <string>
#include <vector>

using namespace std;

// 모노톤 스택
vector<int> solution(vector<int> numbers) {
    vector<int> answer; // 스택
    for (int i = 0; i < numbers.size(); i++)
        answer.push_back(-1);
    
    vector<int> stack;
    
    for (int i = 0; i < numbers.size(); i++) {
        while (!stack.empty() && numbers[stack.back()] < numbers[i]) {
            int idx = stack.back();
            stack.pop_back();
            answer[idx] = numbers[i];
        }
        stack.push_back(i);
    }
    return answer;
}